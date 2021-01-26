package org.example.springcourse;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/people",
                    "stas", "stas");
            if (con != null) {
                System.out.println("connection ok");
            } else {
                System.out.println("couldn't establish connection");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }


}
