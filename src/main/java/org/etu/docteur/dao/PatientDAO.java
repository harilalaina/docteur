package org.etu.docteur.dao;

import org.etu.docteur.patient.ParametrePatient;
import org.etu.docteur.patient.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    DB db;
    public Patient getPatientById(int idPatient) throws SQLException {

        Patient patient = null;

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT nom_patient, age_patient FROM patient WHERE id_patient = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idPatient);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String nomPatient = resultSet.getString("nom_patient");
                    int agePatient = resultSet.getInt("age_patient");
                    List<ParametrePatient> parametrePatientList = getParametrePatient(idPatient);
                    patient = new Patient(idPatient, nomPatient, agePatient, parametrePatientList);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return patient;

    }

    public List<Patient> getAllPatient() throws SQLException {

        List<Patient> patientList = new ArrayList<>();

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT * FROM patient";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int idPatient = resultSet.getInt("id_patient");
                    String nomPatient = resultSet.getString("nom_patient");
                    int agePatient = resultSet.getInt("age_patient");
                    List<ParametrePatient> parametrePatientList = getParametrePatient(idPatient);
                    Patient patient = new Patient(idPatient, nomPatient, agePatient, parametrePatientList);
                    patientList.add(patient);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return patientList;

    }

    public List<ParametrePatient> getParametrePatient(int idPatient) throws SQLException {

        List<ParametrePatient> parametrePatientList = new ArrayList<>();

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT id_param, nom_param, niveau_param FROM v_patient_params WHERE id_patient = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idPatient);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int idParametre = resultSet.getInt("id_param");
                    String nomParametre = resultSet.getString("nom_param");
                    int niveauParametre = resultSet.getInt("niveau_param");

                    ParametrePatient parametrePatient = new ParametrePatient(idParametre, nomParametre, niveauParametre);
                    parametrePatientList.add(parametrePatient);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return parametrePatientList;
    }
}
