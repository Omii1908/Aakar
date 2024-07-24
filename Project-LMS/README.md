
# LIBRARY MANAGEMENT SYSTEM

The Library Management System is a software application designed to manage the operations of a library. It provides functionalities for managing books, members, and transactions. The system uses Java for the frontend and Oracle SQL for the backend to store and manage data.


## Features

- **Book Management:** Add, update, delete, and search for books.
- **Member Management:** Register new members, update member information, and manage member records.
- **Transaction Management:** Issue and return books, track due dates, and manage fines.
- **Search Functionality:** Search for books and members using various criteria.
- **Reports:** Generate reports on book availability, member activity, and transaction history.


## Tech Stack

**Frontend:**  Java 

**Backend:**  Oracle SQL

**IDE:**  IntelliJ IDEA / Eclipse

**Database Connectivity:**  JDBC


## Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Oracle Database
- JDBC Driver for Oracle
- Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse


### Steps

1. Clone the Repository:

```bash
  git clone https://github.com/Omii1908/sva_sangraha.git/Project-LMS.git
  cd library-management-system

```
2. Set Up the Database:

- Create a new database in Oracle.
- Run the SQL scripts provided in the database folder to create the necessary tables and insert initial data.

3. Configure Database Connection:

- Update the database connection settings in the db.properties file with your Oracle database credentials.


4. Build and Run the Application:

- Open the project in your IDE.
- Build the project to resolve dependencies.
- Run the Main.java file to start the application.


## Usage

1. **Login:** Use the admin credentials to log in to the system.

2. **Manage Books:** Navigate to the Books section to add, update, delete, or search for books.

3. **Manage Members:** Navigate to the Members section to register new members or update member information.

4. **Issue/Return Books:** Use the Transactions section to issue or return books and manage due dates and fines.

5. **Generate Reports:** Access the Reports section to generate and view various reports.


## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## Contributing

Contributions are welcome! 

Please fork the repository and create a pull request with your changes.

Ensure that your code follows the project’s coding standards and includes appropriate tests.