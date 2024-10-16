package com.pluralsight;

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
        AccountingLedgerAPP.getInstance().storeHomeScreen();
    }
}