package com.classes.project.InventoryManagement.Order;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InventoryManagement {

    private static Scanner scanner = new Scanner(System.in);
    private static List<InventoryManagement> inventoryItemList = new ArrayList<>();
    private String itemId;
    private String itemName;
    private String itemDescription;
    private double itemPrice;

  
    // Constructor
    public InventoryManagement(String itemId, String itemName, String itemDescription, double itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }
    public static void displayItemManagementMenu() throws SQLException {
        int itemManagementChoice;
        do {
        	   System.out.println("============================");
               System.out.println("|    Item Management       |");
               System.out.println("============================");
               System.out.println("| 1. View Items            |");
               System.out.println("| 2. Add Item              |");
               System.out.println("| 3. Update Item           |");
               System.out.println("| 4. Delete Item           |");
               System.out.println("| 5. Back to Main Menu     |");
               System.out.println("|==========================|");
               System.out.print("Enter your choice: ");

            itemManagementChoice = scanner.nextInt();

            switch (itemManagementChoice) {
                case 1:
                    viewItems();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 5:
                    System.out.println("Returning to the Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (itemManagementChoice != 5);
    }

    private static void viewItems() {
        try {
        	System.out.println("+---------------------------+");
            System.out.printf("| %10s|%n","VIEWING ITEMS.....");
            System.out.println("+---------------------------+");
            

            Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.INVENTORY_ITEMS";
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet result = ps.executeQuery();

            // Print table header
            System.out.println("+------------+----------------------+-------------------------------+-----------------+");
            System.out.printf("| %-10s | %-20s | %-30s | %-15s |%n", "Item ID", "Item Name", "Item Description", "Item Price");
            System.out.println("+------------+----------------------+-------------------------------+-----------------+");

            while (result.next()) {
                // Print table row
                System.out.printf("| %-10s | %-20s | %-30s | %-15s |%n", 
                    result.getString("item_id"), 
                    result.getString("item_name"), 
                    result.getString("item_description"), 
                    result.getDouble("item_price"));
            }

            // Print bottom border
            System.out.println("+------------+----------------------+-------------------------------+-----------------+");

            result.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

    // Method to add an inventory item
 // Method to add an inventory item
    public static void addItem() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("+---------------------------+");
        System.out.printf("| %10s|%n","ADDING ITEMS.....");
        System.out.println("+---------------------------+");
        System.out.println("Enter the Item Details");
        // No need to enter the Item ID, let the database handle it
        System.out.println("Enter the Item Name");
        String itemName = sc.nextLine();
        System.out.println("Enter the Item Description");
        String itemDescription = sc.nextLine();
        System.out.println("Enter the Item Price");
        double itemPrice = sc.nextDouble();

        InventoryManagement newItem = new InventoryManagement(null, itemName, itemDescription, itemPrice);
        inventoryItemList.add(newItem);
        addItemToDatabase(newItem);
    }

    // Method to add an item to the database
 // Method to add an item to the database
    private static void addItemToDatabase(InventoryManagement newItem) {
        try {
            // Assuming 'con' is your database connection
            String insertQuery = "INSERT INTO DATABASE_SYSTEM.INVENTORY_ITEMS (ITEM_NAME, ITEM_DESCRIPTION, ITEM_PRICE) VALUES (?, ?, ?)";
            try (Connection con = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);
                 PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {

                preparedStatement.setString(1, newItem.getItemName());
                preparedStatement.setString(2, newItem.getItemDescription());
                preparedStatement.setDouble(3, newItem.getItemPrice());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Item added to the database successfully.");
                    viewItems();
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                } else {
                    System.out.println("Failed to add item to the database.");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



              

    // Getter methods...

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

	private static void updateItem() {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

            Scanner scanner = new Scanner(System.in);
            System.out.println("+---------------------------+");
            System.out.printf("| %10s|%n","UPDATING ITEMS.....");
            System.out.println("+---------------------------+");
            // Get item ID from the user
            System.out.println("Enter Item ID to update:");
            String itemIdToUpdate = scanner.next();

            // Check if the item exists
            if (itemExists(connection, itemIdToUpdate)) {
                // Get new item details from the user
                System.out.println("Enter new Item Name:");
                String newItemName = scanner.next();

                System.out.println("Enter new Item Description:");
                String newItemDescription = scanner.next();

                // Handle the potential InputMismatchException
             // Handle the potential InputMismatchException
              System.out.println("Enter new Item price:");
          Double newItemPrice=scanner.nextDouble();
                   
                // Update the item in the database
                String updateQuery = "UPDATE INVENTORY_ITEMS SET item_name = ?, item_description = ?, item_price = ? WHERE item_id= ?";
                try (PreparedStatement psUpdate = connection.prepareStatement(updateQuery)) {
                    psUpdate.setString(1, newItemName);
                    psUpdate.setString(2, newItemDescription);
                    psUpdate.setDouble(3, newItemPrice);
                    psUpdate.setString(4, itemIdToUpdate);

                    int rowsUpdated = psUpdate.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Item updated successfully!");
                        viewItems();
                    } else {
                        System.out.println("Failed to update item.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Item not found. Update aborted.");
            }

            // Close resources
            scanner.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean itemExists(Connection connection, String itemIdToUpdate) {
        try {
            // Query to check if the item exists
            Connection connection1 = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

            String checkItemQuery = "SELECT COUNT(*) FROM DATABASE_SYSTEM.INVENTORY_ITEMS WHERE ITEM_ID = ?";

            try (PreparedStatement psCheckItem = connection1.prepareStatement(checkItemQuery)) {
                psCheckItem.setString(1, itemIdToUpdate);
                ResultSet result = psCheckItem.executeQuery();

                // Check if there is at least one row (item) with the given itemId
                if (result.next() && result.getInt(1) > 0) {
                    return true; // Item exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Item does not exist or an error occurred
    }

    private static void deleteItem() {
        try {
        	System.out.println("+---------------------------+");
            System.out.printf("| %10s|%n","DELETING ITEMS.....");
            System.out.println("+---------------------------+");
            System.out.println("Enter Item ID to delete:");
            String itemIdToDelete = scanner.next();

            Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

            String selectQuery = "SELECT * FROM DATABASE_SYSTEM.INVENTORY_ITEMS WHERE Item_id = ?";
            PreparedStatement psSelect = connection.prepareStatement(selectQuery);
            psSelect.setString(1, itemIdToDelete);
            ResultSet result = psSelect.executeQuery();

            if (result.next()) {

                String deleteQuery = "DELETE FROM DATABASE_SYSTEM.INVENTORY_ITEMS WHERE Item_id = ?";
                PreparedStatement psDelete = connection.prepareStatement(deleteQuery);
                psDelete.setString(1, itemIdToDelete);

                int rowsDeleted = psDelete.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Item deleted successfully!");
                    System.out.println("--------------------------------------------------------------------------------------------------");
                } else {
                    System.out.println("Failed to delete item.");
                }

                psDelete.close();

            } else {
                System.out.println("Item not found. Deletion aborted.");
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
			displayItemManagementMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void displayProducts() {
		
		    if (inventoryItemList.isEmpty()) {
		        System.out.println("No products available in the inventory.");
		    } else {
		        System.out.println("Available Products:");
		        for (InventoryManagement product : inventoryItemList) {
		            System.out.println("Item ID: " + product.getItemId());
		            System.out.println("Name: " + product.getItemName());
		            System.out.println("Description: " + product.getItemDescription());
		            System.out.println("Price: " + product.getItemPrice());
		            System.out.println("-------------------------");
		        }
		    }
		}

	private static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
		
	}

