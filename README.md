
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
-<img width="579" alt="Screenshot 2024-10-18 at 08 43 34" src="https://github.com/user-attachments/assets/1c65c196-3425-4b99-9c25-45e8a98f0949">
-<img width="583" alt="Screenshot 2024-10-18 at 08 44 01" src="https://github.com/user-attachments/assets/73009dd7-7b56-4a9e-9bab-ccecbb5ed0e8">
-<img width="586" alt="Screenshot 2024-10-18 at 08 44 16" src="https://github.com/user-attachments/assets/d4a13d73-c761-411d-8cb9-28070596d532">

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

Your Yiming - Yiming.Gao0907@gmail.com

Project Link: [Accounting-Ledger-Application](https://github.com/yourusername/accounting-ledger-app)
