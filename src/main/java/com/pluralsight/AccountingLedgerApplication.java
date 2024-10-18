package com.pluralsight;

import com.pluralsight.Repository.LedgerOperations;
import com.pluralsight.utils.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The AccountingLedgerAPP class is the main class for the Accounting Ledger Application.
 * It handles the initialization and main menu navigation of the application.
 */
public class AccountingLedgerApplication {

    // Logger instance for logging information
    private static final Logger logger = LoggerFactory.getLogger(AccountingLedgerApplication.class);
    // Singleton instance of the Ledger class
    private static final LedgerOperations ledger = Ledger.getInstance();
    // Singleton instance of the AccountingLedgerAPP class
    private static AccountingLedgerApplication accountingLedgerApp;

    /**
     * Private constructor to prevent instantiation.
     */
    private AccountingLedgerApplication() {
    }

    /**
     * Returns the singleton instance of the AccountingLedgerAPP class.
     * If the instance is null, it initializes it.
     *
     * @return The singleton instance of AccountingLedgerAPP
     */
    public static AccountingLedgerApplication getInstance(String fileName) {
        if (accountingLedgerApp == null) {
            accountingLedgerApp = new AccountingLedgerApplication();
        }
        return accountingLedgerApp;
    }

    /**
     * Displays the home screen menu and processes user commands.
     * The loop continues until the user chooses to exit.
     */
    public void storeHomeScreen() {
        logger.info("Showing Home Screen!");
        boolean flag = true;
        while (flag) {
            try {
                homeScreenMenu();
                String command = InputUtil.promptForString(" (StoreHomeScreen) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "D" -> ledger.addDeposit();    // Add the Deposit
                    case "P" -> ledger.makePayment();   // Make a Payment
                    case "L" -> ledger.ledgerScreen();  // Display Ledger Screen
                    case "X" -> flag = false;           // Exit the application
                    default -> System.out.println("Invalid Option. Please choose between (D, P, L, X)\n");
                }
            } catch (Exception e) {
                logger.info("Wrong command or Option!");
                e.printStackTrace();
            }
        }
    }

    /**
     * Displays the home screen menu options to the user.
     */
    private static void homeScreenMenu() {
        String options =
                """
                ========================================================================
                =====          Welcome To Accounting Ledger Application            =====
                =====          Choose one of the options below:                    =====
                =====          Option: (D)  Add Deposit                            =====
                =====          Option: (P)  Make Payment (Debit)                   =====
                =====          Option: (L)  Ledger Screen                          =====
                =====          Option: (X)  Exit                                   =====
                ========================================================================
                """;
        System.out.println(options);
    }
}