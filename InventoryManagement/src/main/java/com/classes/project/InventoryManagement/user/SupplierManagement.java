package com.classes.project.InventoryManagement.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


    import java.util.Scanner;

    public class SupplierManagement extends DriverPerson {
    	 private static Scanner scanner = new Scanner(System.in);
    	    private static String providerId; // Corrected variable name
    	    private static String providerName; // Added variable declaration
    	    private static String providerEmail; // Added variable declaration
    	    private static String providerPhoneNumber; // Added variable declaration
    	    private static String providerLocation;

        public static void displaySupplierManagementMenu() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nSupplier Management Menu:");
                System.out.println("1. View Suppliers");
                System.out.println("2. Add Supplier");
                System.out.println("3. Update Supplier");
                System.out.println("4. Delete Supplier");
                System.out.println("5. Back to Previous Menu");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewSuppliers();
                        break;
                    case 2:
                        addSupplier();
                        break;
                    case 3:
                        updateSupplier();
                        break;
                    case 4:
                        deleteSupplier();
                        break;
                    case 5:
                        return; // Exit the method to go back to the previous menu
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }

        public String getProviderId() {
         
			return providerId;
        }

        public void setProviderId(String providerId) {
            this.providerId = providerId;
        }

        // Getter and Setter for providerName
        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }

        // Getter and Setter for providerEmail
        public String getProviderEmail() {
            return providerEmail;
        }

        public void setProviderEmail(String providerEmail) {
            this.providerEmail = providerEmail;
        }

        // Getter and Setter for providerPhoneNo
        public String getProviderPhoneNumber() {
            return providerPhoneNumber;
        }

        public void setProviderPhoneNumber(String providerPhoneNo) {
            this.providerPhoneNumber= providerPhoneNo;
        }

        // Getter and Setter for providerLocation
        public String getProviderLocation() {
            return providerLocation;
        }

        public void setProviderLocation(String providerLocation) {
            this.providerLocation = providerLocation;
        }
        public static void viewSuppliers() {
            try {
                System.out.println("Viewing Suppliers...");

                Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

                String selectQuery = "SELECT * FROM DATABASE_SYSTEM.SUPPLIERS_INFO";
                PreparedStatement ps = connection.prepareStatement(selectQuery);
                ResultSet result = ps.executeQuery();

                // Print table header
                System.out.println("--------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-15s | %-30s | %-30s | %-20s | %-20s |\n", "Supplier ID", "Supplier Name", "Supplier Email", "Supplier Phone Number", "Supplier Location");
                System.out.println("--------------------------------------------------------------------------------------------------------------");

                // Print table data
                while (result.next()) {
                    System.out.printf("| %-15s | %-30s | %-30s | %-20s | %-20s |\n",
                            result.getString("provider_id"),
                            result.getString("provider_name"),
                            result.getString("provider_emailid"),
                            result.getString("provider_phoneno"),
                            result.getString("provider_location"));
                }

                // Print table footer
                System.out.println("--------------------------------------------------------------------------------------------------------------");

                result.close();
                ps.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {
            String selectSupplierQuery = "SELECT * FROM  DATABASE_SYSTEM.SUPPLIERS_INFO WHERE PROVIDER_ID = ?";
            
            try (PreparedStatement psSelect = connection.prepareStatement(selectSupplierQuery)) {
                psSelect.setString(1, providerId);

                try (ResultSet result = psSelect.executeQuery()) {
                    if (result.next()) {
                        String providerName = result.getString("PROVIDER_NAME");
                        String providerPhone = result.getString("PROVIDER_PHONENO");
                        String providerEmail = result.getString("PROVIDER_EMAILID");
                        String providerLocation = result.getString("PROVIDER_LOCATION");

                        System.out.println("Supplier Details:");
                        System.out.println("Provider ID: " + providerId);
                        System.out.println("Provider Name: " + providerName);
                        System.out.println("Phone Number: " + providerPhone);
                        System.out.println("Email ID: " + providerEmail);
                        System.out.println("Location: " + providerLocation);
                        
                        // Display full table details of the selected supplier
                        System.out.println("\nFull Table Details:");
                        System.out.println("-----------------------------------------------------");
                        System.out.printf("| %-20s | %-30s |\n", "Attribute", "Value");
                        System.out.println("-----------------------------------------------------");
                        result.beforeFirst(); // Move result set cursor back to first row
                        while (result.next()) {
                            System.out.printf("| %-20s | %-30s |\n", result.getMetaData().getColumnName(1), result.getString(1));
                            System.out.printf("| %-20s | %-30s |\n", result.getMetaData().getColumnName(2), result.getString(2));
                            System.out.printf("| %-20s | %-30s |\n", result.getMetaData().getColumnName(3), result.getString(3));
                            System.out.printf("| %-20s | %-30s |\n", result.getMetaData().getColumnName(4), result.getString(4));
                            System.out.printf("| %-20s | %-30s |\n", result.getMetaData().getColumnName(5), result.getString(5));
                            System.out.println("-----------------------------------------------------");
                        }
                    } else {
                        System.out.println("Supplier with ID " + providerId + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addSupplier() {
        try {
            System.out.println("Adding Supplier...");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Supplier Name:");
            String supplierName = scanner.nextLine();

            System.out.println("Enter Supplier Email:");
            String supplierEmail = scanner.nextLine();

            System.out.println("Enter Supplier Phone Number:");
            String supplierPhoneNumber = scanner.nextLine();

            System.out.println("Enter Supplier Location:");
            String supplierLocation = scanner.nextLine();

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String insertQuery = "INSERT INTO DATABASE_SYSTEM.SUPPLIERS_INFO ( provider_name, provider_emailid, provider_phoneno, provider_location) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertQuery);
     
            ps.setString(1, supplierName);
            ps.setString(2, supplierEmail);
            ps.setString(3, supplierPhoneNumber);
            ps.setString(4, supplierLocation);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Supplier added successfully!");
            } else {
                System.out.println("Failed to add supplier.");
            }

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
	public static void updateSupplier() {
        try {
            System.out.println("Updating Supplier...");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the ID of the supplier to update: ");
            int supplierIdToUpdate = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.SUPPLIERS_INFO WHERE provider_id = ?";
            PreparedStatement selectPs = connection.prepareStatement(selectQuery);
            selectPs.setInt(1, supplierIdToUpdate);

            ResultSet result = selectPs.executeQuery();

            if (result.next()) {
                // Supplier found, allow for updating
                System.out.println("Updating Supplier ID: " + result.getInt("provider_id"));

                System.out.println("Enter Supplier Name (leave blank to keep unchanged):");
                String supplierName = scanner.nextLine();
                if (!supplierName.isEmpty()) {
                    result.updateString("provider_name", supplierName);
                }

                System.out.println("Enter Supplier Email (leave blank to keep unchanged):");
                String supplierEmail = scanner.nextLine();
                if (!supplierEmail.isEmpty()) {
                    result.updateString("provider_emailid", supplierEmail);
                }

                System.out.println("Enter Supplier Phone Number (leave blank to keep unchanged):");
                String supplierPhoneNumber = scanner.nextLine();
                if (!supplierPhoneNumber.isEmpty()) {
                    result.updateString("provider_phoneno", supplierPhoneNumber);
                }

                System.out.println("Enter Supplier Location (leave blank to keep unchanged):");
                String supplierLocation = scanner.nextLine();
                if (!supplierLocation.isEmpty()) {
                    result.updateString("provider_location", supplierLocation);
                }

                result.updateRow();
                System.out.println("Supplier updated successfully!");
            } else {
                System.out.println("Supplier not found with ID: " + supplierIdToUpdate);
            }

            result.close();
            selectPs.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	  
    public static void deleteSupplier() {
        try {
            System.out.println("Deleting Supplier...");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the ID of the supplier to delete: ");
            String supplierIdToDelete = scanner.next();
            scanner.nextLine(); // Consume the newline character

            Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

            String deleteQuery = "DELETE FROM DATABASE_SYSTEM.SUPPLIERS_INFO WHERE provider_id = ?";
            PreparedStatement deletePs = connection.prepareStatement(deleteQuery);
            deletePs.setString(1, supplierIdToDelete);

            int rowsDeleted = deletePs.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Supplier deleted successfully!");
            } else {
                System.out.println("No supplier found with ID: " + supplierIdToDelete);
            }

            deletePs.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
