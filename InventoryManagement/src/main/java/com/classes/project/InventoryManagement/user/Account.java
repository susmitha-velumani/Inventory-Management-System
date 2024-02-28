package com.classes.project.InventoryManagement.user;

import com.classes.project.InventoryManagement.user.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.classes.project.InventoryManagement.user.Admin;
public class Account {

   
    private static Object customer;


	public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Inventory Management Systems");
        System.out.println("Type 1: Login");
        System.out.println("Type 2: Create Account");

        int choice = scanner.nextInt();

        if (choice == 1) {
            try {
                login();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (choice == 2) {
            signup();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }

        scanner.close();
    }



	public static void signup() throws SQLException {
	    try (Scanner scanner = new Scanner(System.in)) {
	        int customerId = 50;  // Initialize customer ID to 0

	        System.out.println("Enter your phone number:");
	        String phone_number = scanner.nextLine();
	        System.out.println("Enter mail id:");
	        String emailId = scanner.nextLine();
	        System.out.println("Enter username:");
	        String username = scanner.nextLine();
	        System.out.println("Enter password:");
	        String password = scanner.nextLine();
	        System.out.println("Enter your location:");
	        String location = scanner.nextLine();
	        System.out.println("Enter your customer name:");
	        String customer_name = scanner.nextLine();

	      
	            try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {

	                String sql = "INSERT INTO DATABASE_SYSTEM.Customerinfo(customer_id, Customer_Phno, Customer_emailid, username, password, location, customer_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                    statement.setInt(1, customerId);

	                    statement.setString(2, phone_number);
	                    statement.setString(3, emailId);
	                    statement.setString(4, username);
	                    statement.setString(5, password);
	                    statement.setString(6, location);
	                    statement.setString(7, customer_name);

	                    int rowsInserted = statement.executeUpdate();

	                    if (rowsInserted > 0) {
	                        System.out.println("User registered successfully!");

	                        // Retrieve the auto-generated primary key (customer ID)
	                        
	                    } else {
	                        System.out.println("Failed to register user.");
	                    }
	                } catch (SQLException e) {
	                    // Log the exception or handle it appropriately in your application
	                    e.printStackTrace();
	                }
	            }
	        } 
	}

//

//    private static boolean isValidUsername(String username) {
//        String regexUserName = "^[A-Za-z0-9_]+$";
//        Pattern pattern = Pattern.compile(regexUserName);
//        Matcher matcher = pattern.matcher(username);
//        return matcher.matches();
//    }
//
//    private static boolean isValidPassword(String password) {
//        // Simplified password validation: at least 8 characters
//        return password.length() >= 8;
//    }

    public static void login() throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {

            boolean loginSuccessful = false;
            while (!loginSuccessful) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (isValidCredentials(username, password)) {
                System.out.println("Login successful!");
                
               Admin.displayMainMenu();
               loginSuccessful = true; 
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
        }
    }

    private static boolean isValidCredentials(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {
//          System.out.println(DATABASE_SYSTEM.customerinfo);
        	String sql = "SELECT * FROM DATABASE_SYSTEM.ADMINS_login WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void loginFun() throws ClassNotFoundException, IOException, SQLException {
        Scanner scanner = new Scanner(System.in);

        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (isValidCustomerCredentials(username, password)) {
                System.out.println("Login successful!");
          
                Customer.handleCustomerManagement();
                loginSuccessful = true; // We don't need this anymore since we'll exit the loop after successful login
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        scanner.close();
    }


    private static boolean isValidCustomerCredentials(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {
            String sql = "SELECT * FROM DATABASE_SYSTEM.Customerinfo WHERE Username = ? AND Password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
