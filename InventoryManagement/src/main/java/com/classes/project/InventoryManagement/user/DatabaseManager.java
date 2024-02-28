package com.classes.project.InventoryManagement.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	public static String URL = "jdbc:oracle:thin:@localhost:1521:xe";

    public static String username = "system";

    public static String password = "root";

    public static Connection getConnection(String url, String username, String password) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
	}

	


