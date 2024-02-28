package com.classes.project.InventoryManagement.notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.classes.project.InventoryManagement.Order.DatabaseConnection;



public class Email extends Notification 
{
	public void sendNotification(String message)
	{
		message ="Order to be proceesd";
	    System.out.println("Sending Email: " + message);
	}
}