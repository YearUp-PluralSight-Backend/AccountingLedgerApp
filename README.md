
# Accounting Ledger Application

This is my first capstone project at Year Up United, developed using Java. It reads data from a CSV file and provides a simple command-line interface for managing financial tasks. Users can add deposits, make payments, view transaction history, and generate various financial reports.


## Features

1. **User Interface**

    - Home Screen
    - Ledger Screen
    - Report Screen

2. **Home Screen**

    - Add deposits
    - Make payments
    - Ledger Screen

3. **Ledger Screen**
    - View all transactions
    - View deposits only
    - View payments only
    - View current balance
    - Report Screen
    - Home Screen

4. **Report Screen**

    - Month-to-date report
    - Previous month report
    - Year-to-date report
    - Previous year report
    - Search by vendor name
    - Custom search by multiple properties
    - Back to Ledger Screen

## Project Structure

The project is organized into several key classes:

- `AccountingLedgerApplication`: The main class that initializes the application and handles the home screen navigation.
- `Ledger`: Manages the list of transactions and provides methods for adding, displaying, and manipulating transaction data.
- `Report`: Generates various financial reports based on the transaction data.
- `Transaction`: Represents individual financial transactions.
- `InputUtil`: Utility class for handling user input and formatting output.


## ScreenShots

#### ***Example of Home Screen***
-<img width="579" alt="Screenshot 2024-10-18 at 08 43 34" src="https://github.com/user-attachments/assets/1c65c196-3425-4b99-9c25-45e8a98f0949">

#### ***Example of Ledger Screen***
-<img width="583" alt="Screenshot 2024-10-18 at 08 44 01" src="https://github.com/user-attachments/assets/73009dd7-7b56-4a9e-9bab-ccecbb5ed0e8">

#### ***Example of Report Screen***
-<img width="586" alt="Screenshot 2024-10-18 at 08 44 16" src="https://github.com/user-attachments/assets/d4a13d73-c761-411d-8cb9-28070596d532">

#### ***Example of Data***
-<img width="558" alt="Screenshot 2024-10-18 at 08 48 36" src="https://github.com/user-attachments/assets/91fa6e78-83a3-40f9-b12b-5fa5d30a7c9a">


## Interesting Code
By implement the singleton instance, I believe it can save a lot of resources!

```java

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

```

----------------------------------------------------------------------------------------------------


By implement the userInput, I think it will make my code more concise and clean


```java

public static Transaction userInput() {
        try {
            String dateTime = promptForString("Enter the Date time (yyyy-MM-dd HH:mm:ss) Or default Today's Now: ");

            LocalDateTime createdDateTime;

            if (dateTime.isBlank() || dateTime.isEmpty()) {
                createdDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
                System.out.println("You have used the default time: Now " + createdDateTime);

            } else {
                createdDateTime = LocalDateTime.parse(dateTime, InputUtil.dateTimeFormatter);
            }

            String vendorName = promptForString("Enter the Vendor Name: ");
            String description = promptForString("Enter the Description: ");
            Double amount = promptForDouble("Enter the Amount: ");

            System.out.println();
            Transaction transaction = new Transaction(createdDateTime, description, vendorName, amount);
            logger.info("Created transaction: {}", transaction);

            return transaction;
        } catch (Exception e) {
            logger.error("Error while processing user input: ", e);
            System.out.println("Date Time Format issues! Please try it again!");
            e.printStackTrace();
        }

        return null;
    }
```


## Get Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven (for dependency management)

### Installation

1. Clone the repository:

   ```
   git clone https://github.com/yourusername/accounting-ledger-app.git
   ```
2. Navigate to the project directory:

   ```
   cd accounting-ledger-app
   ```
3. Build the project using Maven:

   ```
   mvn clean install
   ```

### Running the Application

Run the main class `AccountingLedgerApplication` to start the application:

```
java -cp target/accounting-ledger-app-1.0-SNAPSHOT.jar com.pluralsight.AccountingLedgerApplication
```

## Usage

1. **Home Screen**: Choose from options to add deposits, make payments, view the ledger, or exit the application.
2. **Ledger Screen**: View all entries, deposits only, payments only, check balance, access the report screen, or return to the home screen.
3. **Report Screen**: Generate various financial reports or perform custom searches on transactions.


## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments

- SLF4J for logging
- Reference : [How to Write README for My software project](https://eheidi.dev/tech-writing/20221212_documentation-101/)
- Reference : [How to Write README](https://www.freecodecamp.org/news/how-to-write-a-good-readme-file/)
- Reference : [How to Get First Date or Last Date in LocalDateTime](https://stackoverflow.com/questions/9382897/how-to-get-start-and-end-date-of-a-year)
- Reference : [How to Compare Date in Java](https://www.baeldung.com/java-8-comparator-comparing)
- Reference : [Interface vs Abstract Class](https://stackoverflow.com/questions/761194/interface-vs-abstract-class-general-oo)
- Reference : [How to use the Stream API](https://www.baeldung.com/java-8-streams)
- Reference : [How to use the Collections Framework](https://www.baeldung.com/java-collections)
- Reference : [How to use Comparator.Comparing](https://www.baeldung.com/java-8-comparator-comparing)
- Reference : [How to Format the DateTime](https://www.geeksforgeeks.org/instant-truncatedto-method-in-java-with-examples/#)

## Contact

Name Yiming - Yiming.Gao0907@gmail.com

Project Link: [Accounting-Ledger-Application](https://github.com/YearUp-PluralSight-Backend/AccountingLedgerApp) 
