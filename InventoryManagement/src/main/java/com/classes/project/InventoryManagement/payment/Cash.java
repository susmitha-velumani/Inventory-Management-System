package com.classes.project.InventoryManagement.payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cash extends Payment {
    private Scanner scanner = new Scanner(System.in);

    public Cash(String item_id) {
        this.item_id = item_id;
    }

    public int processCashPayment() {
        System.out.println("Cash payment selected.");

        try {
            double item_price = getSeatPrice(item_id);

            System.out.println("Enter cash amount: ");
            double amount = scanner.nextDouble();


            System.out.println("Cash payment processed successfully.");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private double getSeatPrice(String item_id) throws SQLException {
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
