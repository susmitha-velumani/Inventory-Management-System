package com.classes.project.InventoryManagement.payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Card extends Payment {
    private Scanner scanner = new Scanner(System.in);

    public Card(String item_id) {
        this.item_id = item_id;
    }

    public int processCardPayment() {
        System.out.println("Card payment selected.");

        try {
            double item_price = getItemPrice(item_id);

            System.out.println("Enter card number: ");
            String cardNumber = scanner.next();

            // Additional card payment processing logic can be added here

            System.out.println("Card payment processed successfully.");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private double getItemPrice(String item_id) throws SQLException {
        String query = "SELECT item_price FROM DATABASE_SYSTEM.INVENTORY_ITEMS WHERE item_id = ?";

        try (Connection connection = DriverManager.getConnection(DatabaseManager.URL, DatabaseManager.username, DatabaseManager.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item_id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("item_price");
                } else {
                    throw new SQLException("Item not found with ID: " + item_id);
                }
            }
        }
    }
}
