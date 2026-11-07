package com.bengkelshift.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_shift2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Koneksi() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
