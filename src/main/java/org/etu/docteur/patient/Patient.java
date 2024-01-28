package org.etu.docteur.patient;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.etu.docteur.aretina.Aretina;
import org.etu.docteur.aretina.ParametreAretina;
import org.etu.docteur.dao.AretinaDAO;
import org.etu.docteur.dao.FanafodyDAO;
import org.etu.docteur.fanafody.Fanafody;
import org.etu.docteur.fanafody.ParametreFanafody;

import java.sql.SQLException;
import java.util.*;

public class Patient {
    int idPatient;
    String nomPatient;
    int agePatient;
    List<ParametrePatient> parametrePatientList;

    // Données statiques : liste ny aretina misy rehetra miaraka @ paramètre intervalle
    private static List<Aretina> aretinaAvecParametreList;

    static {

        try {
            AretinaDAO aretinaDAO = new AretinaDAO();
            List<Aretina> aretinaSansParametreList = aretinaDAO.getAllAretina();
            aretinaAvecParametreList = new ArrayList<>();

            for (Aretina aretina : aretinaSansParametreList) {
                int idAretina = aretina.getIdAretina();
                List<ParametreAretina> parametreAretinaList = aretinaDAO.getParametreAretina(idAretina);
                aretina.setParametreAretinaList(parametreAretinaList);
                aretinaAvecParametreList.add(aretina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Données statiques : liste ny fanafody misy rehetra miaraka @ paramètres
    private static List<Fanafody> fanafodyAvecParametreList;

    static {

        try {
            FanafodyDAO fanafodyDAO = new FanafodyDAO();
            fanafodyAvecParametreList = fanafodyDAO.getAllFanafody();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Patient(String nomPatient, int agePatient) {
        setNomPatient(nomPatient);
        setAgePatient(agePatient);
    }

    public Patient(int idPatient, String nomPatient, int agePatient, List<ParametrePatient> parametrePatientList) {
        setIdPatient(idPatient);
        setNomPatient(nomPatient);
        setAgePatient(agePatient);
        setParametrePatientList(parametrePatientList);
    }

    public static List<Aretina> getAretinaAvecParametreList() {
        return aretinaAvecParametreList;
    }

    public static List<Fanafody> getFanafodyAvecParametreList() {
        return fanafodyAvecParametreList;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getNomPatient() {
        return nomPatient;
    }

    public void setNomPatient(String nomPatient) {
        this.nomPatient = nomPatient;
    }

    public int getAgePatient() {
        return agePatient;
    }

    public void setAgePatient(int agePatient) {
        this.agePatient = agePatient;
    }

    public List<ParametrePatient> getParametrePatientList() {
        return parametrePatientList;
    }

    public void setParametrePatientList(List<ParametrePatient> parametrePatientList) {
        this.parametrePatientList = parametrePatientList;
    }

    public List<Aretina> trouverAretinaProbables(List<ParametrePatient> parametresPatient) {

        List<Aretina> aretinaProbablesList = new ArrayList<>();

        for (Aretina aretina : aretinaAvecParametreList) {
            int nombreParamtreAretina = aretina.getParametreAretinaList().size();
            int nombreParametreIdentique = 0;

            for (ParametreAretina parametreAretina : aretina.getParametreAretinaList()) {

                for (ParametrePatient parametrePatient : parametresPatient) {
                    int idParametreAretina = parametreAretina.getIdParametre();
                    int idParametrePatient = parametrePatient.getIdParametre();

                    if (idParametreAretina == idParametrePatient) {
                        nombreParametreIdentique++;
                    }

                }

            }

            if (nombreParamtreAretina == nombreParametreIdentique) {
                aretinaProbablesList.add(aretina);
            }
        }

        return aretinaProbablesList;
    }

    public List<Aretina> trouverAretinaCertains(List<ParametrePatient> parametresPatient, List<Aretina> aretinaProbables) {

        List<Aretina> aretinaCertainsList = new ArrayList<>();

        for (Aretina aretina : aretinaProbables) {
            int nombreParamtreAretina = aretina.getParametreAretinaList().size();
            int nombreParametreCompris = 0;

            for (ParametreAretina parametreAretina : aretina.getParametreAretinaList()) {

                for (ParametrePatient parametrePatient : parametresPatient) {
                    int idParametrePatient = parametrePatient.getIdParametre();
                    int idParametreAretina = parametreAretina.getIdParametre();

                    if (idParametrePatient == idParametreAretina) {
                        int borneInf = parametreAretina.getInf();
                        int borneSup = parametreAretina.getSup();
                        int niveauParametrePatient = parametrePatient.getNiveauParametrePatient();

                        if (niveauParametrePatient >= borneInf && niveauParametrePatient <= borneSup) {
                            nombreParametreCompris++;
                        }
                    }
                }
            }

            if (nombreParametreCompris == nombreParamtreAretina) {
                aretinaCertainsList.add(aretina);
            }
        }

        return aretinaCertainsList;
    }

    public List<Fanafody> trouverFanafodyMetyParParmetre(int idParametre) {

        List<Fanafody> fanafodyMetyList = new ArrayList<>();

        for (Fanafody fanafody : fanafodyAvecParametreList) {

            for (ParametreFanafody parametreFanafody : fanafody.getParametresFanafody()) {
                int idParametreFanafody = parametreFanafody.getIdParametre();

                if (idParametre == idParametreFanafody) {
                    fanafodyMetyList.add(fanafody);
                }
            }
        }

        return fanafodyMetyList;
    }

    public Fanafody trouverFanafody(int idParametre) {

        for (Fanafody fanafody : fanafodyAvecParametreList) {

            for (ParametreFanafody parametreFanafody : fanafody.getParametresFanafody()) {
                int idParametreFanafody = parametreFanafody.getIdParametre();

                if (idParametre == idParametreFanafody) {
                    return fanafody;
                }
            }
        }

        return null;
    }

    public HashMap<Fanafody, Integer> trouverFanafody(List<Fanafody> fanafodyList, List<ParametrePatient> parametrePatients) {
        Loader.loadNativeLibraries();

        MPSolver solver = MPSolver.createSolver("SCIP");
        if (solver == null) {
            throw new RuntimeException("Pas de solveur");
        }

        HashMap<Fanafody, MPVariable> variables = new HashMap<>();
        for (Fanafody medicament : fanafodyList) {
            variables.put(medicament, solver.makeIntVar(0, Integer.MAX_VALUE, medicament.getNomFanafody()));
        }

        for (ParametrePatient symptome : parametrePatients) {
            int symptomeId = symptome.getIdParametre();

            MPConstraint constraint = solver.makeConstraint(symptome.getNiveauParametrePatient(), Double.POSITIVE_INFINITY);

            for (Fanafody medicament : fanafodyList) {
                Integer apport = medicament.getNiveauEffetFanafody(symptomeId);
                if (apport != null) {
                    constraint.setCoefficient(variables.get(medicament), apport);
                }
            }
        }

        MPObjective objective = solver.objective();
        for (Fanafody medicament : fanafodyList) {
            objective.setCoefficient(variables.get(medicament), medicament.getPrixFanafody());
        }
        objective.setMinimization();

        MPSolver.ResultStatus resultStatus = solver.solve();

        if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
            throw new RuntimeException("Pas");
        }

        HashMap<Fanafody, Integer> result = new HashMap<>();
        for (Fanafody medicament : fanafodyList) {
            result.put(medicament, (int) variables.get(medicament).solutionValue());
        }

        return result;
    }

    public double calculerPrixTotal(HashMap<Fanafody, Integer> fanafodyList) {
        double prixTotal = 0;

        for (Map.Entry<Fanafody, Integer> entry : fanafodyList.entrySet()) {
            if (entry.getValue() > 0) {
                prixTotal = prixTotal + entry.getKey().getPrixFanafody() * entry.getValue();
            }
        }

        return prixTotal;
    }

}
