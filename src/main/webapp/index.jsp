<%@ page import="org.etu.docteur.dao.PatientDAO" %>
<%@ page import="org.etu.docteur.patient.Patient" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.etu.docteur.patient.ParametrePatient" %>
<%@ page import="org.etu.docteur.aretina.Aretina" %>
<%@ page import="org.etu.docteur.fanafody.Fanafody" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%
    PatientDAO patientDAO = new PatientDAO();
    List<Patient> patientList = new ArrayList<>();
    try {
        patientList = patientDAO.getAllPatient();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>JSP - Hello World</title>
	<link type="text/css" rel="stylesheet" href="assets/css/bootstrap.min.css" />
</head>
<body>
    <h1><%= "Hello Doctor!" %></h1>

    <br/>

<%--    <a href="hello-servlet">Hello Servlet</a>--%>

    <table class="table table-bordered">
        <thead>
            <tr class="table-secondary">
                <th scope="col" class="h4">Patient</th>
<%--                <th scope="col" class="h4">Age</th>--%>
                <th scope="col" class="h4">Paramètres</th>
                <th scope="col" class="h4">Aretina Probables</th>
                <th scope="col" class="h4">Aretina Certains</th>
                <th scope="col" class="h4">Fanafody</th>
            </tr>
        </thead>

        <tbody>
            <% for (Patient patient : patientList) { %>

            <tr>
                <td>
                    <div class="card text-dark bg-light mb-3">
                        <div class="card-header">ID: #<%= patient.getIdPatient()%></div>
                        <div class="card-body">
                            <h5 class="card-title">Nom: <%= patient.getNomPatient()%></h5>
                            <p class="card-text">Age: <%= patient.getAgePatient() + " ans"%></p>
                        </div>
                    </div>
                </td>
<%--                <td><%= patient.getNomPatient()%></td>--%>
<%--                <td><%= patient.getAgePatient()%></td>--%>
                <td>
                    <ul class="list-group">
                    <% for (ParametrePatient parametrePatient : patient.getParametrePatientList()) { %>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <%= parametrePatient.getNomParametre()%>
                            <span class="badge bg-secondary rounded-pill"><%= parametrePatient.getNiveauParametrePatient()%></span>
                        </li>
                    <% } %>
                    </ul>
                </td>

                <%
                    List<ParametrePatient> parametresPatientList = patient.getParametrePatientList();
                    List<Aretina> aretinaProbablesList = patient.trouverAretinaProbables(parametresPatientList);
                    List<Aretina> aretinaCertainsList = patient.trouverAretinaCertains(parametresPatientList, aretinaProbablesList);
                %>

                <td>
                    <ul>
                        <% for (Aretina aretinaProbable : aretinaProbablesList) { %>
                            <li><%= aretinaProbable.getNomAretina()%></li>
                        <% } %>
                    </ul>
                </td>

                <td>
                    <ul class="list-group">
                        <% for (Aretina aretinaCertain : aretinaCertainsList) { %>
                            <li class="list-group-item d-flex justify-content-between align-items-center"><%= aretinaCertain.getNomAretina()%></li>
                        <% } %>
                    </ul>
                </td>


                <td>
                    <%
                        List<Fanafody> fanafodyAvecDoublonList = new ArrayList<>();
                        for (ParametrePatient parametrePatient : parametresPatientList) {
                            int idParametre = parametrePatient.getIdParametre();
                            List<Fanafody> fanfodyParParametreList = patient.trouverFanafodyMetyParParmetre(idParametre);
                            fanafodyAvecDoublonList.addAll(fanfodyParParametreList);
                        }

                        Set<Fanafody> fanafodySansDoublonSet = new LinkedHashSet<>(fanafodyAvecDoublonList);
                        List<Fanafody> fanafodySansDoublonList = new ArrayList<>(fanafodySansDoublonSet);

                        HashMap<Fanafody, Integer> combinaisonFanafody = patient.trouverFanafody(fanafodySansDoublonList, parametresPatientList);
                    %>
                    <ul class="list-group">
<%--                        <% for (Fanafody fanafody : fanafodySansDoublonList) { %>--%>
<%--                        <li><%= fanafody.getNomFanafody()%></li>--%>
<%--                        <% } %>--%>
                        <% for (Map.Entry<Fanafody, Integer> entry : combinaisonFanafody.entrySet()) {
                            if (entry.getValue() > 0) {
                        %>
                        <li class="list-group-item d-flex justify-content-between align-items-center"><%= entry.getKey().getNomFanafody() + " : " + entry.getValue()%></li>
                        <% } } %>
    <li class="list-group-item d-flex justify-content-between align-items-center"><p class="h5">Prix total = </p><%= patient.calculerPrixTotal(combinaisonFanafody) + "Ar"%></li>
                    </ul>
                </td>

            </tr>

            <% } %>
        </tbody>
    </table>
</body>
</html>