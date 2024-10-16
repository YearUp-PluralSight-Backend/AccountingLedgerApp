package com.pluralsight;


import com.pluralsight.utils.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountingLedgerAPP {


    private static final Logger logger = LoggerFactory.getLogger(AccountingLedgerAPP.class);
    private static final Ledger ledger = Ledger.getInstance();
    private static AccountingLedgerAPP accountingLedgerApp;

    private AccountingLedgerAPP() {
    }
    public static AccountingLedgerAPP getInstance() {

        if (accountingLedgerApp == null) {
            accountingLedgerApp = new AccountingLedgerAPP();
        }
        return accountingLedgerApp;
    }

    public void storeHomeScreen() {

        boolean flag = true;
        while(flag) {
            try {
                homeScreenMenu();
                String command = InputUtil.promptForString(" (StoreHomeScreen) Enter your Option: ").toUpperCase();
                switch (command) {
                    case "D" -> ledger.addDeposit();    // Add the Deposit
                    case "P" -> ledger.makePayment();   // Make a Payment
                    case "L" -> ledger.ledgerScreen();  // Display Ledger Screen
                    case "X" -> flag = false;
                    default -> System.out.println("Invalid Option. Please choose between (D, P, L, X)\n");
                }
            } catch (Exception e) {
                logger.info("Wrong command or Option!");
                e.printStackTrace();

            }
        }

    }

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
