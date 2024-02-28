package com.classes.project.InventoryManagement.payment;

import java.util.Scanner;

public class Payment {
 
    Scanner scanner = new Scanner(System.in);
    protected static String item_id;

    public int getPaymentType() {
        System.out.println("╔═════════════════════════════════════════════════════════╗");
        System.out.printf(" ║ %-60s \n", "Payment Type");                               
        System.out.println("║ 1. Cash                                                 ");
        System.out.println("║ 2. Card                                                 ");
        System.out.println("╚═════════════════════════════════════════════════════════╝");
        int choice = scanner.nextInt();
        System.out.println("Enter your item id :");
        item_id = scanner.next();

        switch (choice) {
            case 1:
                Cash cash = new Cash(item_id);
                return cash.processCashPayment();
//             Uncomment the following lines when implementing the Card class
             case 2:
                 Card card = new Card(item_id);
                 return card.processCardPayment();
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
                return -1;
        }
    }
}
