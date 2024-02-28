package com.classes.project.InventoryManagement.user;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.classes.project.InventoryManagement.Order.InventoryManagement;
import com.classes.project.InventoryManagement.Order.OrderManagement;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;



import java.util.ArrayList;
import java.util.List;

	 

public class Admin extends DriverPerson {
    private static Connection con;
    private static Scanner scanner = new Scanner(System.in);
    private static String adminUsername;
    private static String adminPassword;
    private static String adminAddress;
    private static String adminPhoneNumber;
    private static String adminLocation;
	private static int profileChoice;
	private static String username;

    public Admin() throws ClassNotFoundException, IOException, SQLException {
        super();
        initializeDatabaseConnection();
        verifyAccount();
    }

    private void initializeDatabaseConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void verifyAccount() throws IOException, SQLException {
        System.out.println(" ____________________________________________");
        System.out.println("|   Welcome to Inventory Management Systems  |");
        System.out.println("|--------------------------------------------|");
        System.out.println("| Type 1: Login                              |");
        System.out.println("| Type 2: Create Account                     |");
        System.out.println("|--------------------------------------------|");

        int choiceAcc = scanner.nextInt();
        scanner.nextLine();

        switch (choiceAcc) {
            case 1:
                Account.login();
                break;
            case 2:
                Account.signup();
                break;
            default:
                System.out.println("Incorrect! Choose a valid option again.");
                verifyAccount();
        }
    }

    static void displayMainMenu() throws SQLException {
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Profile Management");
            System.out.println("2. User Management");
            System.out.println("3. Supplier Management");
            System.out.println("4. Inventory Management");
            System.out.println("5. Order Management");
            System.out.println("6. Customer Management");
            System.out.println("7. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayProfileManagementMenu();
                    break;
                case 2:
                    displayUserManagementMenu();
                    break;
                case 3:
                    SupplierManagement.displaySupplierManagementMenu();
                    break;
                case 4:
                    InventoryManagement.displayItemManagementMenu();
                    break;
                case 5:
                    OrderManagement.displayOrderManagementMenu();
                    break;
                case 6:
                    Customer.handleCustomerManagement();
                	break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 7);
    }
    
    private static void displayProfileManagementMenu() {
        int profileChoice;
        do {
            System.out.println("\nProfile Management Menu:");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
        
            System.out.println("3. Back to Main Menu");
            System.out.println("Enter your choice:");

            profileChoice = scanner.nextInt();

            switch (profileChoice) {
                case 1:
                    viewProfile(username);
                    break;
                case 2:
                    updateProfile(username);
                    break;
                
                case 3:
                    System.out.println("Returning to the Main Menu.");
                    break;
                case 4:
                	System.out.println("Exit");
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (profileChoice != 4);
    }

    private static void viewProfile(String username) {
        try {
            System.out.println("Viewing User Profile...");

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.ADMINS_LOGIN WHERE USERNAME=?";
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ps.setString(1, username);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                System.out.println("Username: " + result.getString("USERNAME"));
                System.out.println("Address: " + result.getString("USER_ADDRESS"));
                System.out.println("Phone Number: " + result.getString("PHONE_NUMBER"));
                System.out.println("Role: " + result.getString("USER_ROLE"));
            } else {
                System.out.println("User not found");
            }

            result.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateProfile(String username) {
        try {
            System.out.println("Updating User Profile...");

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String updateQuery = "UPDATE DATABASE_SYSTEM.ADMINS_LOGIN SET USER_ADDRESS=?, PHONE_NUMBER=? WHERE USERNAME=?";
            PreparedStatement ps = connection.prepareStatement(updateQuery);

            System.out.println("Enter new Address:");
            String newAddress = scanner.nextLine();
            ps.setString(1, newAddress);

            System.out.println("Enter new Phone Number:");
            String newPhoneNumber = scanner.next();
            ps.setString(2, newPhoneNumber);

            ps.setString(3, username);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User profile updated successfully!");
            } else {
                System.out.println("Failed to update user profile.");
            }

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void displayUserManagementMenu() {
        int userManagementChoice;
        do {
            System.out.println("\nUser Management Menu:");
            System.out.println("1. View Users");
            System.out.println("2. Add User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu");
            System.out.println("Enter your choice:");

            userManagementChoice = scanner.nextInt();

            switch (userManagementChoice) {
                case 1:
                    viewCustomers();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    System.out.println("Returning to the Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (userManagementChoice != 5);
    }

    private static void viewCustomers() {
        try {
            System.out.println("Viewing Customers...");

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.CUSTOMERINFO";
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                System.out.println("Customer ID: " + result.getString("CUSTOMER_ID"));
                System.out.println("Customer Phone Number: " + result.getString("CUSTOMER_PHNO"));
                System.out.println("Customer Email ID: " + result.getString("CUSTOMER_EMAILID"));
                System.out.println("Username: " + result.getString("USERNAME"));
                System.out.println("Password: " + result.getString("PASSWORD"));
                System.out.println("Location: " + result.getString("LOCATION"));
                System.out.println("Customer Name: " + result.getString("CUSTOMER_NAME"));
                System.out.println("---------------------------");
            }

            result.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addCustomer() {
        try {
            System.out.println("Adding Customer...");

            System.out.println("Enter Customer ID:");
            String customerId = scanner.next();

            System.out.println("Enter Customer Phone Number:");
            String customerPhno = scanner.next();

            System.out.println("Enter Customer Email ID:");
            String customerEmailId = scanner.next();

            System.out.println("Enter Username:");
            String username = scanner.next();

            System.out.println("Enter Password:");
            String password = scanner.next();

            System.out.println("Enter Location:");
            scanner.nextLine();
            String location = scanner.nextLine();

            System.out.println("Enter Customer Name:");
            String customerName = scanner.nextLine();

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String insertQuery = "INSERT INTO DATABASE_SYSTEM.CUSTOMERINFO (CUSTOMER_ID, CUSTOMER_PHNO, CUSTOMER_EMAILID, USERNAME, PASSWORD, LOCATION, CUSTOMER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setString(1, customerId);
            ps.setString(2, customerPhno);
            ps.setString(3, customerEmailId);
            ps.setString(4, username);
            ps.setString(5, password);
            ps.setString(6, location);
            ps.setString(7, customerName);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer added successfully!");
               
            } else {
                System.out.println("Failed to add customer.");
            }

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateCustomer() {
        try {
            System.out.println("Updating Customer...");

            System.out.println("Enter Customer ID to update:");
            String customerIdToUpdate = scanner.next();

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.CUSTOMERINFO WHERE CUSTOMER_ID = ?";
            PreparedStatement psSelect = connection.prepareStatement(selectQuery);
            psSelect.setString(1, customerIdToUpdate);
            ResultSet result = psSelect.executeQuery();

            if (result.next()) {
                System.out.println("Enter new Location:");
                scanner.nextLine();
                String newLocation = scanner.nextLine();

                System.out.println("Enter new Customer Name:");
                String newCustomerName = scanner.nextLine();

                String updateQuery = "UPDATE DATABASE_SYSTEM.CUSTOMERINFO SET LOCATION = ?, CUSTOMER_NAME = ? WHERE CUSTOMER_ID = ?";
                PreparedStatement psUpdate = connection.prepareStatement(updateQuery);
                psUpdate.setString(1, newLocation);
                psUpdate.setString(2, newCustomerName);
                psUpdate.setString(3, customerIdToUpdate);

                int rowsUpdated = psUpdate.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Customer updated successfully!");
                } else {
                    System.out.println("Failed to update customer.");
                }

                psUpdate.close();

            } else {
                System.out.println("Customer not found. Update aborted.");
            }

            result.close();
            psSelect.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteCustomer() {
        try {
            System.out.println("Deleting Customer...");

            System.out.println("Enter Customer ID to delete:");
            String customerIdToDelete = scanner.next();

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.CUSTOMERINFO WHERE CUSTOMER_ID = ?";
            PreparedStatement psSelect = connection.prepareStatement(selectQuery);
            psSelect.setString(1, customerIdToDelete);
            ResultSet result = psSelect.executeQuery();

            if (result.next()) {

                String deleteQuery = "DELETE FROM DATABASE_SYSTEM.CUSTOMERINFO WHERE CUSTOMER_ID = ?";
                PreparedStatement psDelete = connection.prepareStatement(deleteQuery);
                psDelete.setString(1, customerIdToDelete);

                int rowsDeleted = psDelete.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Customer deleted successfully!");
                } else {
                    System.out.println("Failed to delete customer.");
                }

                psDelete.close();

            } else {
                System.out.println("Customer not found. Deletion aborted.");
            }

            result.close();
            psSelect.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
			new Admin();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}