package org.etu.docteur.dao;

import java.sql.*;

public class DB {
    private static final String dbDriver = "org.postgresql.Driver";
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/doc";
    private static final String dbUser = "postgres";
    private static final String dbPass = "root";

    private Connection connection;

    public DB() {
    }

    public Connection getConnex() {

        if (connection == null) {
            try {
                Class.forName(dbDriver);
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void closeConnection() {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
