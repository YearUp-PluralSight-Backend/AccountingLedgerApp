package com.pluralsight;


import java.util.ArrayList;

/**
 * The Main class serves as the entry point for the application.
 */
public class Main {

    /**
     * The main method is the entry point of the application.
     * It initializes the AccountingLedgerAPP and displays the home screen.
     *
     * @param args Command line arguments  | not used
     */
    public static void main(String[] args) {
        AccountingLedgerApplication.getInstance("inventory.csv").storeHomeScreen();
    }
}