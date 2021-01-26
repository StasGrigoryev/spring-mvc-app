package org.example.springcourse;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static Connection con;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("org.postgresql://localhost:5432/people",
                    "stas", "stas");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }


}
