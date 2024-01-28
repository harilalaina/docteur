package org.etu.docteur.dao;

import org.etu.docteur.aretina.Aretina;
import org.etu.docteur.aretina.ParametreAretina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AretinaDAO {

    DB db;

    public Aretina getAretinaById(int idAretina) throws SQLException {

        Aretina aretina = null;

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT nom_aretina FROM aretina WHERE id_aretina = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idAretina);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String nomAretina = resultSet.getString("nom_aretina");
                    aretina = new Aretina(idAretina, nomAretina);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return aretina;

    }

    public List<Aretina> getAllAretina() throws SQLException {

        List<Aretina> aretinaList = new ArrayList<>();

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT * FROM aretina";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int idAretina = resultSet.getInt("id_aretina");
                    String nomAretina = resultSet.getString("nom_aretina");
                    Aretina aretina = new Aretina(idAretina, nomAretina);
                    aretinaList.add(aretina);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return aretinaList;

    }

    public List<ParametreAretina> getParametreAretina(int idAretina) throws SQLException {

        List<ParametreAretina> parametreAretinaList = new ArrayList<>();

        db = new DB();
        try (Connection connection = db.getConnex()) {
            String sql = "SELECT id_param, nom_param, inf, sup FROM v_aretina_params WHERE id_aretina = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idAretina);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int idParametre = resultSet.getInt("id_param");
                    String nomParametre = resultSet.getString("nom_param");
                    int inf = resultSet.getInt("inf");
                    int sup = resultSet.getInt("sup");

                    ParametreAretina parametreAretina = new ParametreAretina(idParametre, nomParametre, inf, sup);
                    parametreAretinaList.add(parametreAretina);
                }
                resultSet.close();
            }
        }
        db.closeConnection();

        return parametreAretinaList;
    }
}
