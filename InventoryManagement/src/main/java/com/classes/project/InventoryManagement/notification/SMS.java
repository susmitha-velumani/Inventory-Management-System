package com.classes.project.InventoryManagement.notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.classes.project.InventoryManagement.Order.DatabaseConnection;



public class SMS extends Notification 
{
	public void sendNotification(String message)
	{
	
	
		 message="order has to be placed";
	
	    System.out.println("Sending SMS: " + message);
	}
}