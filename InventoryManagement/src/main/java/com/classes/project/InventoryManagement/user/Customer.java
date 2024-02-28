package com.classes.project.InventoryManagement.user;
import com.classes.project.InventoryManagement.notification.Email;

import com.classes.project.InventoryManagement.user.Admin;
import com.classes.project.InventoryManagement.notification.Notification;
import com.classes.project.InventoryManagement.notification.SMS;
import com.classes.project.InventoryManagement.payment.Payment;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Calendar;
class Item {
    private String itemId;
    private String itemName;
    private String itemDescription;
    private double itemPrice;

    public Item(String itemId, String itemName, String itemDescription, double itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    // Getter methods for the Item class
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
}

class Shipment {
    private int shipmentId;
    private Date startDate;
    private Date endDate;
    private Date expectedDate;
    private String declinedBy;
    private int billingId;
    private String courierType;
    private String shipmentStatus;

    public Shipment(int shipmentId, Date startDate, Date endDate, Date expectedDate, String declinedBy,
                    int billingId, String courierType, String shipmentStatus) {
        this.shipmentId = shipmentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expectedDate = expectedDate;
        this.declinedBy = declinedBy;
        this.billingId = billingId;
        this.courierType = courierType;
        this.shipmentStatus = shipmentStatus;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public String getDeclinedBy() {
        return declinedBy;
    }

    public int getBillingId() {
        return billingId;
    }

    public String getCourierType() {
        return courierType;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }
}


public class Customer extends DriverPerson {
	 private static final String Message = null;
	private Connection con;
	private static Connection connection2;

	  

	public Customer() throws ClassNotFoundException, IOException, SQLException {
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

	    private void verifyAccount() throws IOException, SQLException, ClassNotFoundException {
	        System.out.println("----------------------------------------------");
	        System.out.println("|   Welcome to Inventory Management Systems  |");
	        System.out.println("|--------------------------------------------|");
	        System.out.println("| Type 1: Login                              |");
	        System.out.println("| Type 2: Create Account                     |");
	        System.out.println("|____________________________________________|");

	        int choiceAcc =objectScan().nextInt();
	        objectScan().nextLine();

	        switch (choiceAcc) {
	            case 1:
	                Account.loginFun();
	                break;
	            case 2:
	                Account.signup();
	                break;
	            case 3:
	            	return;
	            default:
	                System.out.println("Incorrect! Choose a valid option again.");
	                verifyAccount();
	        }
	    }



    
    static String loggedInUsername;
    static ArrayList<Item> orderItems = new ArrayList<>();
    static ArrayList<Shipment> shipments = new ArrayList<>();
	private static String username;
	private static int orderId;
	private static int itemCount;
	private static int customerId;
	private static double Amount;
	private static PreparedStatement ps;
	private static int customerOrderId;
	private static int customer_Order_Id;
	private static int itemcount1;
	private static double total_amount;
	private static Scanner scanner;
	private static int customer_Order_Id1;
	private static int customer_order_id1;
	private static String payment_method;
	private static int customer_order_id;
	private static String productName;
	private static Customer customer;
	private static boolean exit;
	private static String searchItem;
	private static int itemCount1;
	private static String query;
	private static Object catalogue;
	private static String customerEmailAddress;
	private static String notificationMessage;
	private static Connection connection;
	static public Scanner objectScan() {
		return (new Scanner(System.in));
	}

	static void handleCustomerManagement() throws SQLException {
	    boolean exitCustomerMenu = false;
	    while (!exitCustomerMenu) {
	        displayMainMenu();
	        int customerChoice = objectScan().nextInt();

	        switch (customerChoice) {
	            case 1:
	                displayInventoryItems();
	                break;
	           
	            case 2:
	                placeOrder();
	                break;
	            case 3:
	                cancelOrder();
	                break;
	            case 4:
	                updateProfile();
	                break;
	            
	            case 5:
	                ShipmentDetails();
	                break;

	            case 6:
	                OrderInvoice();
	                break;
	            case 7:
	                generateDelivery(itemcount1, customer_Order_Id);
	                break;
	            case 8:
	                makePayment(customer_order_id, payment_method);
	                break;
	            case 9:
	                exitCustomerMenu = true;
	                System.out.println("Exiting...");
	                break;
	            default:
	                System.out.println("Invalid choice. Please enter a valid option.");
	        }
	    }
	    // objectScan().close(); // Close the scanner if it's not needed anymore
	}
	    // Additional methods for each menu option

	    
	

		static void displayMainMenu() {
	        System.out.println("Enter your choice: ");
	        System.out.println("1. Display Inventory Items");
	   
	        System.out.println("2. Place order");
	        System.out.println("3. Cancel Order");
	        System.out.println("4.Update profile");
	        
	        System.out.println("5. ShipmentDetails");
	     
	        System.out.println("6. Orderinvoice");
	        System.out.println("7. GenerateDeivery");
	        System.out.println("8. Make payement");
	        System.out.println("9. Exit");
	    }

	    private static void displayOrderMenu() {
	        int orderManagementChoice = 0;

	        do {
	            // Your order management menu logic here
	            // ...
	        } while (orderManagementChoice != 0); // Adjust the exit condition as needed
	    }

	private static String performLogin() {
        // Implement your login logic here
        // Return the username if login is successful, otherwise return null
        System.out.println("Enter your username: ");
        String username = objectScan().nextLine();

        System.out.println("Enter your password: ");
        String password = objectScan().nextLine();

      
        return username;
    }

	public static void displayInventoryItems() {
	    try {
	    	System.out.println("+---------------------------+");
            System.out.printf("| %10s|%n","DISPLAYING ITEMS....");
            System.out.println("+---------------------------+");
	        String selectQuery = "SELECT * FROM DATABASE_SYSTEM.INVENTORY_ITEMS";
	        Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

	        PreparedStatement ps = connection.prepareStatement(selectQuery);
	        ResultSet result = ps.executeQuery();

	        // Print table header
	        System.out.println("+------------+---------------------+-----------------------------+-----------------+-----------------+");
	        System.out.printf("| %-10s | %-20s | %-30s | %-15s | %-15s |%n", "Item ID", "Item Name", "Item Description", "Item Price", "Stock Quantity");
	        System.out.println("+------------+---------------------+-----------------------------+-----------------+-----------------+");

	        while (result.next()) {
	        
	            String itemId = result.getString("item_id");
	            String itemName = result.getString("item_name");
	            String itemDescription = result.getString("item_description");
	            double itemPrice = result.getDouble("item_price");
	            String stockQuantity = result.getString("stock_quantity");

	            // Print table row
	            System.out.printf("| %-10s | %-20s | %-30s | %-15.2f | %-15s |%n", itemId, itemName, itemDescription, itemPrice, stockQuantity);
	            System.out.println("+------------+---------------------+-----------------------------+-----------------+-----------------+");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void updateProfile() {
		System.out.println("+---------------------------+");
        System.out.printf("| %10s|%n","UPDATING PROFILE.....");
        System.out.println("+---------------------------+");
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter your username: ");
	    String username = scanner.nextLine();

	    try {
	        String updateQuery = "UPDATE DATABASE_SYSTEM.Customerinfo SET customer_name = ?, customer_phno = ?, customer_emailid = ? WHERE username = ?";
	        Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

	        PreparedStatement ps = connection.prepareStatement(updateQuery);

	        System.out.println("Enter new customer name: ");
	        String newCustomerName = scanner.nextLine();
	        System.out.println("Enter new phone number: ");
	        String newCustomerPhno = scanner.nextLine();
	        System.out.println("Enter new email ID: ");
	        String newCustomerEmailId = scanner.nextLine();

	        ps.setString(1, newCustomerName);
	        ps.setString(2, newCustomerPhno);
	        ps.setString(3, newCustomerEmailId);
	        ps.setString(4, username);

	        int rowsUpdated = ps.executeUpdate();

	        // Print the result in table format
	        System.out.println("+----------------------+-----------------------+------------------------+-----------------------------------------------------+");
	        System.out.printf("| %-20s | %-20s | %-20s | %-20s |%n", "Username", "New Customer Name", "New Phone Number", "New Email ID");
	        System.out.println("+----------------------+-----------------------+------------------------+-----------------------------------------------------+");

	        if (rowsUpdated > 0) {
	            System.out.printf("| %-20s | %-20s | %-20s | %-20s |%n", username, newCustomerName, newCustomerPhno, newCustomerEmailId);
	            System.out.println("+----------------------+-----------------------+------------------------+--------------------------------------------------+");
	            System.out.println("Profile updated successfully!");
	        } else {
	            System.out.println("| No rows updated.    |                        |                         |                          |");
	            System.out.println("+----------------------+-----------------------+------------------------+--------------------------------------------------+");
	            System.out.println("Failed to update profile. Profile not found for username: " + username);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	

	 private static void placeOrder() {
		 System.out.println("+---------------------------+");
         System.out.printf("| %10s|%n","PLACING ITEM BY THE CUSTOMER.....");
         System.out.println("+---------------------------+");
		    try {
		        Scanner scanner = new Scanner(System.in); 
		        System.out.println("Enter product name: ");
		        String itemName = scanner.nextLine();

		        if (isProductAvailable(itemName)) {
		            // Product is available, proceed to place the order
		            System.out.println("Enter user ID: ");
		            int customerId = scanner.nextInt();
		            System.out.println("Enter order ID: ");
		            int orderId = scanner.nextInt();
		            System.out.println("Enter ITEM id");
		            String itemId = scanner.next();
		            System.out.println("Enter quantity: ");
		            String quantity = scanner.next();
		            Date orderDate = new Date(System.currentTimeMillis());
		            String status = "Pending"; // Set the initial status
		            System.out.println("Enter customer name");
		            String customername = scanner.next();

		            // Insert the order into the customerorders table
		            String insertOrderQuery = "INSERT INTO DATABASE_SYSTEM.CustomerOrder(CUSTOMER_ORDER_ID, CUSTOMER_ID, ITEM_ID, ITEM_NAME, ITEM_QUANTITY,ORDER_DATE, ORDER_STATUS,CUSTOMER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		            try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
		                 PreparedStatement ps = connection.prepareStatement(insertOrderQuery)) {
		                ps.setInt(1, orderId);
		                ps.setInt(2, customerId);
		                ps.setString(3, itemId); 
		                ps.setString(4, itemName);
		                ps.setString(5, quantity);
		                ps.setDate(6, new java.sql.Date(orderDate.getTime()));
		                ps.setString(7, status);
		                ps.setString(8, customername);

		                connection.setAutoCommit(false); // Disable auto-commit to handle transactions
		                int rowsAffected = ps.executeUpdate();

		                if (rowsAffected > 0) {
		                    System.out.println("Order placed successfully!");
		                    updateStockQuantity(itemName, quantity);
		                    connection.commit(); // Commit the transaction if successful
		                    
		                    // Send notifications
		                    SMS sendMessage = new SMS();
		                    String message = "Your order has been placed successfully!";
		                    sendMessage.sendNotification(message);
		                    
		                    Email sendEmail = new Email();
		                    sendEmail.sendNotification(message);
		                } else {
		                    System.out.println("Failed to place the order.");
		                    connection.rollback(); // Rollback the transaction if failed
		                }

		            } catch (SQLException e) {
		                e.printStackTrace();
		            }

		        } else {
		            System.out.println("Product not available.");
		        }

		    } catch (InputMismatchException e) {
		        System.out.println("Invalid input. Please enter a valid number.");
		    }
		

	
		    
	 try {
		 System.out.println("+---------------------------+");
         System.out.printf("| %10s|%n","CUSTOMER ORDERS.....");
         System.out.println("+---------------------------+");
		    String selectQuery = "SELECT * FROM DATABASE_SYSTEM.CustomerOrder";
		    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
		         PreparedStatement ps = connection.prepareStatement(selectQuery);
		         ResultSet result = ps.executeQuery()) {
		        
		        // Print table header
		        System.out.println("Customer Orders:");
		        System.out.printf("%-15s %-10s %-10s %-20s %-10s %-15s %-15s %-20s%n", "Order ID", "Customer ID", "Item ID", "Item Name", "Quantity", "Order Date", "Status", "Customer Name");
		        System.out.println("---------------------------------------------------------------------------------------------------------");

		        while (result.next()) {
		            // Retrieve data from the result set
		            int orderId = result.getInt("CUSTOMER_ORDER_ID");
		            int customerId = result.getInt("CUSTOMER_ID");
		            String itemId = result.getString("ITEM_ID");
		            String itemName = result.getString("ITEM_NAME");
		            int quantity = result.getInt("ITEM_QUANTITY");
		            java.sql.Date orderDate = result.getDate("ORDER_DATE");
		            String status = result.getString("ORDER_STATUS");
		            String customerName = result.getString("CUSTOMER_NAME");

		            // Print table row
		            System.out.printf("%-15d %-10d %-10s %-20s %-10d %-15s %-15s %-20s%n", orderId, customerId, itemId, itemName, quantity, orderDate, status, customerName);
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	 }

    
    private static void updateStockQuantity(String itemName,String quantity) {
        String updateQuery = "UPDATE DATABASE_SYSTEM.INVENTORY_ITEMS SET STOCK_QUANTITY = STOCK_QUANTITY - ? WHERE ITEM_NAME = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {

            ps.setString(1, quantity);
            ps.setString(2, itemName);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Stock quantity updated successfully!");
            } else {
                System.out.println("Failed to update stock quantity.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isProductAvailable(String itemName) {
        String selectQuery = "SELECT * FROM DATABASE_SYSTEM.INVENTORY_ITEMS WHERE ITEM_NAME = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
             PreparedStatement ps = connection.prepareStatement(selectQuery)) {

            ps.setString(1, itemName);

            try (ResultSet resultSet = ps.executeQuery()) {
                // If there is at least one result, the product is available
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


private static int getCustomerIdByUsername(String username) {
    Scanner scanner = new Scanner(System.in);

    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {
        // Query to retrieve customer ID based on the username
        String query = "SELECT Customer_Id FROM Customer_login WHERE Username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // If a matching record is found, return the customer ID
                    return resultSet.getInt("Customer_Id");
                } else {
                    System.out.println("Customer with username " + username + " not found.");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    // Return a default value or handle the case where no customer is found
    return 0;
}

   
public static void cancelOrder() {
	System.out.println("+---------------------------+");
    System.out.printf("| %10s|%n","CANCEL ORDER....");
    System.out.println("+---------------------------+");
    Scanner scanner = new Scanner(System.in);

    // Get order details from the user
    System.out.println("Enter Order ID to cancel: ");
    int orderId = scanner.nextInt();

    try {
        String updateQuery = "UPDATE DATABASE_SYSTEM.CustomerOrder SET ORDER_STATUS = 'CANCELLED' WHERE customer_order_id = ?";
        Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

        PreparedStatement ps = connection.prepareStatement(updateQuery);
        ps.setInt(1, orderId);

        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Order canceled successfully!");
        } else {
            System.out.println("Failed to cancel order. Order not found or already canceled.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

//Assume getOrderDate1 and generateUniqueShipmentId are properly defined elsewhere
public static void ShipmentDetails() {
	System.out.println("+---------------------------+");
    System.out.printf("| %10s|%n","SHIPPMENT DETAILS....");
    System.out.println("+---------------------------+");
    try {
        Scanner scanner = new Scanner(System.in);

        // Capture other values from the user or any other source
        System.out.println("Enter Declined By: ");
        System.out.println("---------------------");
        String declinedBy = scanner.next();
        System.out.println("Enter customer order id");
        System.out.println("---------------------");

        int customerOrderId = scanner.nextInt();

        System.out.println("Enter Courier Type: ");
        System.out.println("---------------------");

        String courierType = scanner.next();

        System.out.println("Enter Shipment Status: ");
        System.out.println("---------------------");

        String shipmentStatus = scanner.next();

        // Establish JDBC connection
        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {

            // Assuming you have a method to get the order date based on orderId
             java.sql.Date orderDate = getOrderDate1(orderId); // Assuming this method is defined elsewhere

            // Calculate start date (after two days of order date)
            LocalDate startLocalDate = orderDate.toLocalDate().plusDays(2);
             java.sql.Date startDate = java.sql.Date.valueOf(startLocalDate);

            // Calculate end date (after five days of order date)
            LocalDate endLocalDate = orderDate.toLocalDate().plusDays(5);
             java.sql.Date endDate = java.sql.Date.valueOf(endLocalDate);

            // Calculate expected date (intermediate of start date and end date)
            LocalDate expectedLocalDate = startLocalDate.plusDays((5 - 2) / 2); // Intermediate of start and end date
             java.sql.Date expectedDate = java.sql.Date.valueOf(expectedLocalDate);

            // Prepare the SQL query
            String insertQuery = "INSERT INTO DATABASE_SYSTEM.Shipment_detail (Shipment_Id, Start_Date, End_Date, Expected_Date, Declined_By, Customer_Order_Id, Courier_Type, Shipment_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement
            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                // Set values in the prepared statement
                ps.setInt(1, generateUniqueShipmentId());
                 ps.setDate(2, startDate);
                 ps.setDate(3, endDate);
                ps.setDate(4, expectedDate);
                ps.setString(5, declinedBy);
                ps.setInt(6, customerOrderId);
                ps.setString(7, courierType);
                ps.setString(8, shipmentStatus);

                // Execute the update
                int rowsInserted = ps.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Shipment created and inserted into the database.");
                } else {
                    System.out.println("Failed to create and insert shipment.");
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }


    try {
        // Establish the connection to the database
        String selectQuery = "SELECT * FROM DATABASE_SYSTEM.shipment_detail";
        Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

        // Prepare and execute the SELECT query
        PreparedStatement ps = connection.prepareStatement(selectQuery);
        ResultSet result = ps.executeQuery();

        // Print table header
        System.out.println("+-----------------+------------------------+--------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-15s | %-23s | %-19s | %-17s |%-17s | %-16s | %-16s | %-16s |%n", "SHIPMENT_ID", "START_DATE", "END_DATE", "EXPECTED_DATE", "DECLINED_BY", "CUSTOMER_ORDER_ID", "COURIER_TYPE", "SHIPMENT_STATUS");
        System.out.println("+-----------------+------------------------+--------------------------------------------------------------------------------------------------------------+");

        while (result.next()) {
            int shipmentId = result.getInt("SHIPMENT_ID");
            String startDate = result.getString("START_DATE");
            String endDate = result.getString("END_DATE");
            String expectedDate = result.getString("EXPECTED_DATE");
            String declinedby = result.getString("Declined_by");
            int customerorderid = result.getInt("CUSTOMER_ORDER_ID");
            String courierType = result.getString("COURIER_TYPE");
            String shipmentstatus = result.getString("SHIPMENT_STATUS");

            // Print table row
            System.out.printf("| %-15d | %-23s | %-23s | %-23s | %-15s | %-15d | %-15s | %-15s |%n", shipmentId, startDate, endDate, expectedDate, declinedby, customerorderid, courierType, shipmentstatus);
            System.out.println("+-----------------+------------------------+---------------------------------------------------------------------------------------------------------------+");
        }

        // Close the resources
        result.close();
        ps.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


// Other methods...

// Example method to generate a unique shipment ID
private static int generateUniqueShipmentId() {
    // Logic to generate a unique shipment ID
    // You can use a sequence, timestamp, or any other logic
    // For simplicity, you can use a random number generator
    return (int) (Math.random() * 100000);
}


private static java.sql.Date getOrderDate1(int orderId) {
    String selectQuery = "SELECT ORDER_DATE FROM DATABASE_SYSTEM.CustomerOrder WHERE CUSTOMER_ORDER_ID = ?";

    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
         PreparedStatement ps = connection.prepareStatement(selectQuery)) {

        ps.setInt(1, orderId);

        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDate("ORDER_DATE");
            } else {
                // Handle the case where no order with the given orderId is found
                // You might throw an exception or return a default date as needed
                return new java.sql.Date(System.currentTimeMillis());
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately for your application
        // You might throw an exception or return a default date as needed
        return new java.sql.Date(System.currentTimeMillis());
    }
}






public static void generateDelivery(int itemCount, int customerOrderId) {
    // Logic to get the current date
    Scanner scanner = new Scanner(System.in);
  System.out.println("Enter customer id");
  System.out.println("---------------------");

  customerOrderId=scanner.nextInt();
    Date orderDate = getOrderDateFromDatabase(customerOrderId);
    System.out.print("Enter Item Count: ");
    System.out.println("---------------------");

    itemCount = scanner.nextInt();

    if (orderDate != null) {
        // Calculate the delivery date (7 days after the order date for example)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date deliveryDate = calendar.getTime();

        // Insert into Delivery table
        String insertDeliveryQuery = "INSERT INTO DATABASE_SYSTEM.DeliveryInfo (Delivery_Id, Delivery_Generate_Date, CUSTOMER_ORDER_ID, Item_Count) VALUES (?, ?, ?,  ?)";

        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
             PreparedStatement ps = connection.prepareStatement(insertDeliveryQuery)) {

            // Assuming you have a method to generate a unique delivery ID
            int deliveryId = generateUniqueDeliveryId();

            ps.setInt(1, deliveryId);
            ps.setDate(2, new java.sql.Date(deliveryDate.getTime()));
            ps.setInt(3, customerOrderId);
            ps.setInt(4, itemCount);
         

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Delivery generated successfully!");
                Payment payment =new Payment();
            } else {
                System.out.println("Failed to generate delivery.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Order date not found for Customer Order ID: " + customerOrderId);
    }

try {
    // Establish the connection to the database
    String selectQuery = "SELECT * FROM  DATABASE_SYSTEM.deliveryinfo";
    Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

    // Prepare and execute the SELECT query
    PreparedStatement ps = connection.prepareStatement(selectQuery);
    ResultSet result = ps.executeQuery();

    // Print table header
    System.out.println("+-----------------+------------------------+---------------------+");
    System.out.printf("| %-15s | %-23s | %-19s |%n", "DELIVERY_ID", "DELIVERY_GENERATE_DATE", "ITEM_COUNT");
    System.out.println("+-----------------+------------------------+---------------------+");

    while (result.next()) {
        // Extract data from the result set
        int deliveryId = result.getInt("DELIVERY_ID");
        String generateDate = result.getString("DELIVERY_GENERATE_DATE");
        int itemCount1 = result.getInt("ITEM_COUNT");

        // Print table row
        System.out.printf("| %-15d | %-23s | %-19d |%n", deliveryId, generateDate, itemCount1);
        System.out.println("+-----------------+------------------------+---------------------+");
    }

    // Close the resources
    result.close();
    ps.close();
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
}
}

private static java.sql.Date  getOrderDateFromDatabase(int orderId) {
    String selectQuery = "SELECT ORDER_DATE FROM DATABASE_SYSTEM.CustomerOrder WHERE CUSTOMER_ORDER_ID = ?";

    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
         PreparedStatement ps = connection.prepareStatement(selectQuery)) {

        ps.setInt(1, orderId);

        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDate("ORDER_DATE");
            } else {
                // Handle the case where no order with the given orderId is found
                // You might throw an exception or return a default date as needed
                return new java.sql.Date(System.currentTimeMillis());
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately for your application
        // You might throw an exception or return a default date as needed
        return new java.sql.Date(System.currentTimeMillis());
    }
}
private static int generateUniqueDeliveryId() {
    return (int) (Math.random() * 100000);
}

private static void OrderInvoice() {
    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password)) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the customer Order ID: ");
        int Customer_order_Id = scanner.nextInt();

        String sql = "SELECT o.Customer_Order_Id, o.customer_Id, c.Customer_Name, o.item_Name, o.item_Quantity, p.item_Price " +
                "FROM DATABASE_SYSTEM.CustomerOrder o " +
                "JOIN DATABASE_SYSTEM.Customerinfo c ON o.customer_Id = c.customer_Id " +
                "JOIN DATABASE_SYSTEM.INVENTORY_ITEMS p ON o.item_Name = p.item_Name " +
                "WHERE o.Customer_Order_Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Customer_order_Id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int customerId = resultSet.getInt("customer_Id");
                    String customerName = resultSet.getString("Customer_Name");
                    String productName = resultSet.getString("item_Name");
                    int item_quantity = resultSet.getInt("item_Quantity");
                    double unitCost = resultSet.getDouble("item_Price");

                    double totalCost = item_quantity * unitCost;

                    // Print table header
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.format("| %-15s | %-15s | %-25s | %-20s | %-10s | %-10s | %-10s |\n", "Customer_Order_ID", "Customer_ID", "Customer_Name", "item_Name", "item_Quantity", "Unit_Cost", "Total_Cost");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

                    // Print table row
                    System.out.format("| %-15d | %-15d | %-25s | %-20s | %-10d | $%-9.2f | $%-9.2f |\n", Customer_order_Id, customerId, customerName, productName, item_quantity, unitCost, totalCost);

                    // Print totals
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------- ");
                } else {
                    System.out.println("Order not found with ID: " + Customer_order_Id); // Corrected variable name here
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public static void makePayment(int customer_Order_Id, String payment_method) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("+---------------------------+");
    System.out.printf("| %10s|%n","PAYMENT DETAILS....");
    System.out.println("+---------------------------+");
    System.out.println("Enter customer order id");
    customer_Order_Id = scanner.nextInt();

    // Fetch total amount based on customer order ID
    double payment_amount = getTotalAmountFromDatabase(customer_Order_Id);

    // Logic to get the current date
    java.sql.Date paymentDate = new java.sql.Date(System.currentTimeMillis());
    System.out.println("Enter payment method");
    payment_method = scanner.next();

    // Insert into Payment table
    String insertPaymentQuery = "INSERT INTO DATABASE_SYSTEM.Paymentinfo (PAYMENT_ID, CUSTOMER_ORDER_ID,PAYMENT_AMOUNT, PAYMENT_METHOD,PAYMENTDATE) VALUES (?, ?, ?, ?,?)";

    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
         PreparedStatement ps = connection.prepareStatement(insertPaymentQuery)) {

        // Set parameters for the query
        ps.setInt(1, generateUniquePaymentId());
        ps.setInt(2, customer_Order_Id);
        ps.setDouble(3, payment_amount);
        ps.setString(4, payment_method);
        ps.setDate(5, paymentDate);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Payment made successfully!");

            // Update order status to "Paid" in CustomerOrder table
            updateOrderStatus(customer_Order_Id, "Paid");
        } else {
            System.out.println("Failed to make payment.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    try {
        // Establish the connection to the database
        Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);

        // Prepare and execute the SELECT query
        String selectQuery = "SELECT * FROM DATABASE_SYSTEM.paymentinfo";
        PreparedStatement ps = connection.prepareStatement(selectQuery);
        ResultSet result = ps.executeQuery();

        // Print table header
        System.out.println("+--------------+----------------------+----------------+------------------+---------------------+");
        System.out.printf("| %-12s | %-20s | %-14s | %-18s | %-19s |%n", "PAYMENT_ID", "CUSTOMER_ORDER_ID", "PAYMENT_AMOUNT", "PAYMENT_METHOD", "PAYMENTDATE");
        System.out.println("+--------------+----------------------+----------------+------------------+---------------------+");

        while (result.next()) {
            // Extract data from the result set
            int paymentId = result.getInt("PAYMENT_ID");
            int customerOrderId = result.getInt("CUSTOMER_ORDER_ID");
            double paymentAmount = result.getDouble("PAYMENT_AMOUNT");
            String paymentMethod = result.getString("PAYMENT_METHOD");
            Date paymentDate1 = result.getDate("PAYMENTDATE");

            // Print table row
            System.out.printf("| %-12d | %-20d | %-14.2f | %-18s | %-19s |%n", paymentId, customerOrderId, paymentAmount, paymentMethod, paymentDate1);
        }
        System.out.println("+--------------+----------------------+----------------+------------------+---------------------+");

        // Close the resources
        result.close();
        ps.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}

private static double getTotalAmountFromDatabase(int customer_order_Id) {
    // Implement the logic to fetch the total amount from the database based on customer_order_Id
    String query = "SELECT SUM(o.ITEM_QUANTITY * i.ITEM_PRICE) AS PAYMENT_AMOUNT " +
                   "FROM DATABASE_SYSTEM.CustomerOrder o " +
                   "JOIN DATABASE_SYSTEM.INVENTORY_ITEMS i ON o.ITEM_ID = i.ITEM_ID " +
                   "WHERE o.CUSTOMER_ORDER_ID = ?";

    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
         PreparedStatement ps = connection.prepareStatement(query)) {

        // Set parameter for the query
        ps.setInt(1, customer_order_Id);

        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                // Return the calculated total amount from the result set
                return resultSet.getDouble("PAYMENT_AMOUNT");
            } else {
                // Handle the case where no data is found for the given customer order ID
                // You might throw an exception or return a default value as needed
                return 0.0; // Placeholder value, replace with actual handling
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception appropriately (throw it or return a default value)
        return 0.0; // Placeholder value, replace with actual handling
    }
}
private static void updateOrderStatus(int customer_order_Id, String newStatus) {
    String updateStatusQuery = "UPDATE DATABASE_SYSTEM.CustomerOrder SET ORDER_STATUS = ? WHERE CUSTOMER_ORDER_ID = ?";

    try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
         PreparedStatement ps = connection.prepareStatement(updateStatusQuery)) {

        ps.setString(1, newStatus);
        ps.setInt(2, customer_order_Id);

        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Order status updated successfully!");
        } else {
            System.out.println("Failed to update order status. Order ID not found.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

	private static int generateUniquePaymentId() {
		return (int) (Math.random() * 100000);

}

	
}

    

    

