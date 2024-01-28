package org.etu.docteur.dao;

import org.etu.docteur.fanafody.Fanafody;
import org.etu.docteur.fanafody.ParametreFanafody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FanafodyDAO {

    DB db;

    public Fanafody getFanafodyById(int idFanafody) throws SQLException {

        Fanafody fanafody = null;

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT nom_fanafody, prix_fanafody FROM fanafody WHERE id_fanafody = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idFanafody);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String nomFanafody = resultSet.getString("nom_patient");
                    double prixFanafody = resultSet.getDouble("prix_fanafody");
                    List<ParametreFanafody> parametreFanafodyList = getParametreFanafody(idFanafody);
                    fanafody = new Fanafody(idFanafody, nomFanafody, prixFanafody, parametreFanafodyList);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return fanafody;
    }

    public List<Fanafody> getAllFanafody() throws SQLException {

        List<Fanafody> fanafodyList = new ArrayList<>();

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT * FROM fanafody";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int idFanafody = resultSet.getInt("id_fanafody");
                    String nomFanafody = resultSet.getString("nom_fanafody");
                    double prixFanafody = resultSet.getDouble("prix_fanafody");
                    List<ParametreFanafody> parametreFanafodyList = getParametreFanafody(idFanafody);
                    Fanafody fanafody = new Fanafody(idFanafody, nomFanafody, prixFanafody, parametreFanafodyList);
                    fanafodyList.add(fanafody);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return fanafodyList;

    }

    public List<ParametreFanafody> getParametreFanafody(int idFanafody) throws SQLException {

        List<ParametreFanafody> parametreFanafodyList = new ArrayList<>();

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT id_param, nom_param, eff_fanafody FROM v_fanafody_params WHERE id_fanafody = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idFanafody);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int idParametre = resultSet.getInt("id_param");
                    String nomParametre = resultSet.getString("nom_param");
                    int effetFanafody = resultSet.getInt("eff_fanafody");

                    ParametreFanafody parametreFanafody = new ParametreFanafody(idParametre, nomParametre, effetFanafody);
                    parametreFanafodyList.add(parametreFanafody);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return parametreFanafodyList;
    }
}
