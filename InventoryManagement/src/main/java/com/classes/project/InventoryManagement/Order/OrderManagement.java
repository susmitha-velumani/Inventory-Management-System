package com.classes.project.InventoryManagement.Order;
import com.classes.project.InventoryManagement.user.Admin.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderManagement {

    private static Scanner scanner = new Scanner(System.in);
	private static double item_price;
	private static String orderDateStr;
	private static PreparedStatement ps;
	private static double itemQuantity;
	private static int ItemQuantity;
	static public Scanner objectScan() {
		return (new Scanner(System.in));
	}
	public static void displayOrderManagementMenu() {
	
	    int orderManagementChoice = 0;
	    int choice;
	    do {
	        System.out.println("\nOrder Management Menu:");
	        System.out.println("1. View Orders");
	        System.out.println("2. Place an Order");
//	        System.out.println("3. Add Order");
	        System.out.println("3. Update Order");
	        System.out.println("4. Delete Order");
	        System.out.println("5. Back to Main Menu");
	        System.out.println("Enter your choice:");

	        try {
	            choice = scanner.nextInt();
	        } catch (java.util.InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a number.");
	            scanner.nextLine();  // Consume the invalid input
	            continue; // Restart the loop to prompt user input again
	        } catch (java.util.NoSuchElementException e) {
	            System.out.println("Input not available. Exiting...");
	            return; // Exit the method or handle the error accordingly
	        }

	        orderManagementChoice = choice; // Assign the choice to orderManagementChoice

	        switch (orderManagementChoice) {
	            case 1:
	                viewOrders();
	                break;
	            case 2:
	                placeOrder();
	                break;
	           
	            case 3:
	                updateOrder();
	                break;
	            case 4:
	                deleteOrder();
	                break;
	            case 5:
	                System.out.println("Returning to the Main Menu.");
	                break;
	            default:
	                System.out.println("Invalid choice. Please enter a valid option.");
	        }
	    } while (orderManagementChoice != 5);
	 
	}

	public static void placeOrder() {
        try {
            // Connect to the database
            try (Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password)) {

                // Add logic to get order details from the user
                Scanner scanner = new Scanner(System.in);

                System.out.println("Enter Item ID:");
                String itemId = scanner.nextLine();
                System.out.println("Enter Order Status (e.g., Pending, Shipped, Delivered):");
                String orderStatus = scanner.nextLine();
                System.out.println("Enter Supplier ID:");
                String supplierId = scanner.next();
                scanner.nextLine();

                System.out.println("Enter Product Quantity:");
                int itemQuantity = scanner.nextInt();

                // Retrieve item price from the database based on the given item ID
                double itemPrice = getItemPriceFromDatabase(itemId);

                // Calculate the total amount based on item price and quantity
                double totalAmount = itemPrice * itemQuantity;

                // Insert order details into the ADMIN_ORDERS table
                String insertOrderQuery = "INSERT INTO DATABASE_SYSTEM.ADMIN_ORDERS (item_id, order_date, order_status, provider_id, item_quantity, total_amount, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement ps = connection.prepareStatement(insertOrderQuery)) {
                    ps.setString(1, itemId);
                    ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                    ps.setString(3, orderStatus);
                    ps.setString(4, supplierId);
                    ps.setInt(5, itemQuantity);
                    ps.setDouble(6, totalAmount);
                    ps.setInt(7, 1); // Replace with the actual user ID

                    ps.executeUpdate();
                    System.out.println("Order placed successfully!");
                }

                // Close the connection and scanner
                scanner.close();
            }

        } catch (SQLException e) {
            // Log the exception or handle it appropriately in your application
            e.printStackTrace();
        }
    }

	private static void viewOrders() {
	    try {
	        // Connect to the database
	        Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

	        // Implement logic to retrieve and display orders from the ADMIN_ORDERS table
	        String selectQuery = "SELECT * FROM DATABASE_SYSTEM.ADMIN_ORDERS ";
	        PreparedStatement ps = connection.prepareStatement(selectQuery);
	        ResultSet result = ps.executeQuery();

	        // Print table header
	        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
	        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-15s | %-15s | %-7s |\n", "Order ID", "Item ID", "Order Date", "Order Status", "Provider ID", "Item Quantity", "Total Amount", "User ID");
	        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

	        // Print table data
	        while (result.next()) {
	            System.out.printf("| %-10s | %-10s | %-10s | %-12s | %-12s | %-15s | %-15s | %-7s |\n",
	                    result.getString("ORDER_ID"),
	                    result.getString("ITEM_ID"),
	                    result.getString("ORDER_DATE"),
	                    result.getString("ORDER_STATUS"),
	                    result.getString("PROVIDER_ID"),
	                    result.getString("ITEM_QUANTITY"),
	                    result.getString("TOTAL_AMOUNT"),
	                    result.getString("USER_ID"));
	        }

	        // Print table footer
	        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

	        result.close();
	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    private static double getItemPriceFromDatabase(String itemId) {
        double itemPrice = -1; // Default value if the item is not found or an error occurs

        try (
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

            // Create a PreparedStatement to execute a SELECT query
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT item_price FROM DATABASE_SYSTEM.INVENTORY_ITEMS WHERE item_id = ?");
        ) {
            // Set the item ID parameter in the prepared statement
            preparedStatement.setString(1, itemId);

            // Execute the query and get the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if the result set has any rows
                if (resultSet.next()) {
                    // Retrieve the item price from the result set
                    itemPrice = resultSet.getDouble("item_price");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }

        return itemPrice;
    }


    private static void updateOrder() {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

         // Get order ID from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Order ID to update: ");
            String orderIdToUpdate = scanner.nextLine(); // Use nextLine() to consume the newline character

            // Check if the order exists
            if (orderExists(connection, orderIdToUpdate)) {
                // Get new order details from the user
                System.out.print("Enter new Item ID: ");
                String newItemId = scanner.nextLine(); // Use nextLine() to read the whole line

                System.out.print("Enter new Supplier ID: ");
                String newSupplierId = scanner.nextLine();

                System.out.print("Enter new Product Quantity: ");
                int newProductQuantity = Integer.parseInt(scanner.nextLine()); // Parse the input as integer

                System.out.print("Enter new Total Amount: ");
                int newTotalAmount = Integer.parseInt(scanner.nextLine()); // Parse the input as integer

                // Update order details in the ORDER_TABLE
                String updateOrderQuery = "UPDATE DATABASE_SYSTEM.ADMIN_ORDERS  SET item_id = ?, order_date = ?, order_status = ?,provider_id= ?, item_quantity = ?, total_amount = ?, user_id = ? WHERE order_id = ?";
                try (PreparedStatement ps = connection.prepareStatement(updateOrderQuery)) {
                    ps.setString(1, newItemId);
                    ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
                    ps.setString(3, "Updated"); // Set the new order status
                    ps.setString(4, newSupplierId);
                    ps.setInt(5, newProductQuantity);
                    ps.setInt(6, newTotalAmount);
                    ps.setInt(7, 1); // Replace with the actual user ID
                    ps.setString(8, orderIdToUpdate);

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Order updated successfully!");
                    } else {
                        System.out.println("Failed to update order.");
                    }
                }
            } else {
                System.out.println("Order not found. Update aborted.");
            }

            // Close the connection and scanner
            connection.close();
            scanner.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteOrder() {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.username, DatabaseConnection.password);

            // Add logic to get order details from the user and delete from the ORDER_TABLE
            // For simplicity, I'll use placeholders; replace these with your actual logic
            String orderIdToDelete = null  ; // Replace with the actual order ID

            if (orderExists(connection, orderIdToDelete)) {
                // Delete order from the ORDER_TABLE
                String deleteOrderQuery = "DELETE FROM DATABASE_SYSTEM.ADMIN_ORDERS  WHERE order_id = ?";
                try (PreparedStatement ps = connection.prepareStatement(deleteOrderQuery)) {
                    ps.setString(1, orderIdToDelete);

                    int rowsDeleted = ps.executeUpdate();
                    if (rowsDeleted > 0) {
                        System.out.println("Order deleted successfully!");
                    } else {
                        System.out.println("Failed to delete order.");
                    }
                }
            } else {
                System.out.println("Order not found. Deletion aborted.");
            }

            // Close the connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean orderExists(Connection connection,String orderIdToDelete) throws SQLException {
        // Check if the order exists in the ORDER_TABLE
        String selectQuery = "SELECT * FROM DATABASE_SYSTEM.ADMIN_ORDERS  WHERE order_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
            ps.setString(1, orderIdToDelete);
            ResultSet result = ps.executeQuery();
            return result.next();
        }
    }

    public static void main(String[] args) {
    	try {
            displayOrderManagementMenu();
        } finally {
            // Close the scanner in a finally block to ensure it is always closed
            scanner.close();
        }
    }
    }

