package com.ait.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseConnection {
    private static String jdbcURL = "jdbc:postgresql://ec2-34-193-232-231.compute-1.amazonaws.com:5432/d1akdss450ker9?sslmode=require";
    private static String jdbcUsername = "idtnhhrdlunhjo";
    private static String jdbcPassword = "1568cce958b3e6de96c78a25b0842e7f9cd21deed3268c5e82b40e35f7f7335b";

    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;

    }

}
