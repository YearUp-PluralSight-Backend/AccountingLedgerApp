# Accounting Ledger Application

## Overview

The Accounting Ledger Application is a Java-based tool designed to help users manage financial transactions, generate reports, and maintain an accurate ledger. This application provides a user-friendly interface for adding deposits, making payments, viewing transaction history, and generating various financial reports.

## Features

1. **Transaction Management**

    - Add deposits
    - Make payments
    - View all transactions
    - View deposits only
    - View payments only
2. **Reporting**

    - Month-to-date report
    - Previous month report
    - Year-to-date report
    - Previous year report
    - Search by vendor name
    - Custom search by multiple properties
3. **Balance Tracking**

    - View current balance
4. **User Interface**

    - Home Screen
    - Ledger Screen
    - Report Screen

## Project Structure

The project is organized into several key classes:

- `AccountingLedgerApplication`: The main class that initializes the application and handles the home screen navigation.
- `Ledger`: Manages the list of transactions and provides methods for adding, displaying, and manipulating transaction data.
- `Report`: Generates various financial reports based on the transaction data.
- `Transaction`: Represents individual financial transactions.
- `InputUtil`: Utility class for handling user input and formatting output.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven (for dependency management)

#### This project covers several important Java features and concepts:

1. Object-Oriented Programming (OOP):

    - The project uses classes (AccountingLedgerApplication, Ledger, Report, Transaction) to encapsulate related functionality and data.
    - Inheritance is likely used (e.g., implementing interfaces like LedgerOperations).
    - Encapsulation is demonstrated through the use of private fields and public methods.
2. Interfaces:

    - The code mentions interfaces like LedgerOperations and ReportOperations, showing the use of interface-based design.
3. Collections Framework:

    - Extensive use of List interface and ArrayList implementation for managing transactions.
4. Streams API:

    - The code uses Java 8+ Streams for filtering and processing collections of transactions.
5. Functional Programming:

    - Lambda expressions are used, particularly with streams (e.g., .parallelStream().filter(...))
6. Date and Time API:

    - Usage of LocalDateTime and LocalDate classes from the java.time package.
7. Exception Handling:

    - Try-catch blocks are used to handle potential exceptions.
8. Generics:

    - Used in collection declarations (e.g., List<Transaction>).
9. File I/O:

    - The updateCSVFile method suggests file operations for persisting data.
10. Singleton Pattern:

    - Both Ledger and Report classes implement the singleton pattern.
11. String Manipulation:

    - Use of StringBuilder in the toString() method of the Ledger class.
12. Logging:

    - SLF4J is used for logging, demonstrating integration with logging frameworks.
13. Multi-threading:

    - The use of Thread.sleep() suggests some basic thread management.
14. Switch Statements:

    - Modern switch statements (with arrow syntax) are used for menu navigation.
15. Text Blocks:

    - Java 15+ text blocks are used for multi-line strings (menu options).
16. UUID:

    - java.util.UUID is used for generating unique identifiers.
17. Input/Output Operations:

    - Custom InputUtil class suggests handling of user input and output formatting.

This project covers a wide range of Java features, making it a good example of a moderately complex Java application. It demonstrates both core Java concepts and more advanced features introduced in recent Java versions.

---



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

## Contributing

Contributions to the Accounting Ledger Application are welcome. Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

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

## Contact

Your Yiming - Yiming.Gao0907@gmail.com

Project Link: [Accounting-Ledger-Application](https://github.com/yourusername/accounting-ledger-app)