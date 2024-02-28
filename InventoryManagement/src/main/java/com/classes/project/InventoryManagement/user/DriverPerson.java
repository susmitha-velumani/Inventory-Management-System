package com.classes.project.InventoryManagement.user;
import com.classes.project.InventoryManagement.Order.OrderManagement;
import com.classes.project.InventoryManagement.user.Admin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DriverPerson {
	  private static Connection connection2;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome!!! Are you \n1.Admin\n2.Customer ");

        int choice = scanner.nextInt();

        if (choice == 1 || choice == 2 ) {
            try {
                switch (choice) {
                    case 1:
                        try {
                            Admin admin = new Admin();
                        } catch (ClassNotFoundException | IOException | SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        // Assuming you have a Customer class, create an instance here
                        Customer customer = new Customer();
                        break;
                    // Add case 3 if needed
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please enter 1, 2, or 3");
        }

        // Close the scanner
        scanner.close();
    }
}

