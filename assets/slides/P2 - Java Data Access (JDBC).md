# Java Data Access (JDBC)

### Software Design

![](images/jdbc-software-design.png)

### Networked DBMS
The most of DBMS make use of the **TCP protocol** for communicating with applications. They accept incoming connections on a specific **TCP port**.
This allows both **local and remote connections**.
- MS SQL Server (TCP:1433)
- PostGreSQL (TCP:5432)
- MySQL (TCP:3306)
- Oracle (TCP:1521)
- SQLite (local DB)

### Local DB
There is also a family of libraries **capable of simulating a DBMS connection while providing access to a local file using the SQL metaphor**.

The most prominent example of this category is **SQLite** widely used on the Android platform

More: <https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase>

### What is JDBC?

*“An **API** that lets you access virtually **any tabular data source** from the Java programming language”*
- What’s an API? *Application Programming Interface*
- What’s a tabular data source? **Relational databases, spreadsheets, CSV
files.**

We’ll focus on accessing relational databases. Nevertheless, the same principles can be applied to all data sources.

![](images/jdbc-what-is.jpg)

### Basic steps 

1. Load vendor specific **Driver**
2. Establish a **Connection**
3. Create a **Statement**
4. Execute **SQL Statements**
5. Get **ResultSet**
6. Close the Connection

### Vendor Specific Drivers
JDBC drivers **provide the connection to the database** and **implement the protocol for transferring queries and results** between the client and the database.

There are 4 type of drivers. We refer to **Type 4: Pure Java** (see Appendix II).

Each database needs a specific driver.
- [mySQL] <https://dev.mysql.com/downloads/connector/j/>
- [SQLite] <https://github.com/xerial/sqlite-jdbc>

**Drivers are Java binary classes (.class files) usually packaged in a single .jar archive and have to be included in the CLASSPATH**

For including vendor specific drivers (or any external library) into the project, two main ways are possible:
- Manually download a .jar file and add it to the CLASSPATH of the project (not a good idea!)
- Configure a building tool (e.g., Maven, Gradle) to download it and make it available to the project

Gradle is the most recent tool and has been chosen as the default for Android Projects (**build.gradle** is the configuration file)

```
apply plugin: 'java-library'
apply plugin: 'idea'
group 'org.nbicocchi'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}
    
dependencies {
    …
    implementation 'com.zaxxer:HikariCP:5.0.1'
    implementation 'mysql:mysql-connector-java:8.0.32'
    implementation 'org.xerial:sqlite-jdbc:3.39.3.0'
    implementation 'com.h2database:h2:2.1.214'
    implementation 'com.microsoft.sqlserver:mssql-jdbc:11.2.1.jre17’
    …
}
```

### 1.Load vendor specific driver

```
import java.sql.*;

/* this is for MySQL*/
Class.forName(“com.mysql.jdbc.Driver”);

/* this is for SQLite */
Class.forName(“org.sqlite.jdbc”);
```

JDBC is an abstract API mostly composed of interfaces and abstract classes. Concrete implementations are mostly provided within drivers.
**Class.forname() dynamically loads the driver’s classes.**
*throws *ClassNotFoundException*!

### 2.Establish a Connection (with URL)

- DriverManager.getConnection(String url);
- DriverManager.getConnection(String url,String user,String password);
- DriverManager.getConnection(String url,Properties prop);

```
/* this is for MySQL*/
Connection c = DriverManager.getConnection(“jdbc:mysql://localhost/dbname?user=user&password=pass”); 

/* this is for SQLite */
Connection c = DriverManager.getConnection(“jdbc:sqlite:filename.db”);
```
Establishes a connection to a database mediated by the **Connection interface**. The driver implements the Connection interface defined within JDBC.

### 3. Create JDBC Statement(s)

```
Statement statement = c.createStatement(); 
```
- The JDBC **Statement**, CallableStatement, and PreparedStatement interfaces define the methods and properties enabling developers to **send SQL or PL/SQL commands and receive data** from your database.
- They also define methods helping **bridge data type differences between Java and SQL data types** used in a database.

### 4. Execute SQL Statements

Once you've created a Statement object, you can then use it to execute an SQL statement with one of its methods.

- **int executeUpdate (String SQL):** Used for **writing** the database. Use this method to execute SQL statements such as **INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE, etc.** Returns the **number of rows** affected by the execution of the SQL statement.
- **ResultSet executeQuery (String SQL)**: Used for **reading** the database. Use this method to execute SQL statements such as **SELECT**. Returns a **ResultSet** object.

```
statement.executeUpdate("CREATE TABLE person (" +
    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
    "name VARCHAR(30), " +
    "surname VARCHAR(30), " +
    "salary REAL)");

statement.executeUpdate("INSERT INTO person (name, surname, salary) VALUES ('Chuck', 'Palahniuk’, 200.23)");

statement.executeQuery("SELECT * FROM person");

// Locales – among other things - influence the way in which strings are formatted
// Very nasty when creating SQL statements using string formatting

Locale locale = new Locale("en", "US");
// Locale locale = new Locale("it", "IT");

String sql = String.format(
 "INSERT INTO person (name, surname, salary) VALUES ('%s', '%s', '%f')",  
 person.getName(),
 person.getSurname(),
 person.getSalary()
);

statement.executeUpdate(sql);
"INSERT INTO person (name, surname, salary) VALUES ('Mario', 'Rossi', '11.2')”
// "INSERT INTO person (name, surname, salary) VALUES ('Mario', 'Rossi', '11,2')"

// PreparedStatement provides an alternative to string formatting
// PreparedStatement also allows reuse with different paramenters (faster)

PreparedStatement insertPerson = connection.prepareStatement(
 "INSERT INTO person (name, surname, salary) VALUES (?, ?, ?)");

for (Person person: persons) {
 insertPerson.setString(1, person.getName());
 insertPerson.setString(2, person.getSurname());
 insertPerson.setDouble(3, person.getSalary());
 insertPerson.execute();
}
```

### 5. Get ResultSet
The **java.sql.ResultSet** interface represents the **result set of a database query**. Objects implementing the ResultSet interface maintain a **cursor pointing to the current row** in the result set.
- **Navigational methods**: Used to move the cursor around the ResultSet
- **Get methods**: Used to view the data in the columns of the current row being pointed by the cursor.
- **Update methods**: Used to update the data in the columns of the current row. Updates are transparently written within the underlying database (if supported)

### Navigational methods 

#### absolute()

```
boolean absolute(int row) throws SQLException
```
Moves the cursor to the given row number in this ResultSet object.

Parameters
- *row* - the number of the row to which the cursor should move. 
A value of zero indicates that the cursor will be positioned before the first row; a positive number indicates the row number counting from the beginning of the result set; a negative number indicates the row number counting from the end of the result set.

Returns
- true if the cursor is moved to a position in this ResultSet object; false if the cursor is before the first row or after the last row.

#### afterlast()

```
void afterLast() throws SQLException
```

Moves the cursor to the end of this ResultSet object, just after the last row. This method has no effect if the result set contains no rows.

#### beforeFirst()

```
void beforeFirst() throws SQLException
```
Moves the cursor to the front of this ResultSet object, just before the first row. This method has no effect if the result set contains no rows.

#### first()

```
boolean first() throws SQLException
```
Moves the cursor to the first row in this ResultSet object.

Returns
- true if the cursor is on a valid row; false if there are no rows in the result set

#### last()

```
boolean last() throws SQLException
```
Moves the cursor to the last row in this ResultSet object.

Returns:
- true if the cursor is on a valid row; false if there are no rows in the result set

#### next()

```
boolean next() throws SQLException
```
Moves the cursor forward one row from its current position. A ResultSet cursor is initially positioned before the first row; the first call to the method next makes the first row the current row; the second call makes the second row the current row, and so on.

Returns
- true if the new current row is valid; false if there are no more rows

#### previous()

```
boolean previous() throws SQLException
```
Moves the cursor to the previous row in this ResultSet object. When a call to the previous method returns false, the cursor is positioned before the first row. Any invocation of a ResultSet method which requires a current row will result in a SQLException being thrown.

Returns:
- true if the cursor is now positioned on a valid row; false if the cursor is positioned before the first row

#### relative()

```
boolean relative(int rows) throws SQLException
```
Moves the cursor a relative number of rows, either positive or negative.

Parameters:
- rows - an int specifying the number of rows to move from the current row; a positive number moves the cursor forward; a negative number moves the cursor backward

Returns:
- true if the cursor is on a row; false otherwise

#### getRow()

```
int getRow() throws SQLException
```
Retrieves the current row number. The first row is number 1, the second number 2, and so on.

Returns
- the current row number; 0 if there is no current row

#### getType()

```
int getType() throws SQLException
```
Retrieves the type of this ResultSet object. The type is determined by the Statement object that created the result set.

Returns:
- ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, or ResultSet.TYPE_SCROLL_SENSITIVE

#### isAfterLast()

```
boolean isAfterLast() throws SQLException
```
Retrieves whether the cursor is after the last row in this ResultSet object.

Returns:
- true if the cursor is after the last row; false if the cursor is at any other position or the result set contains no rows.

#### isBeforeFirst()
```
boolean isBeforeFirst() throws SQLException
```
Retrieves whether the cursor is before the first row in this ResultSet object.

Returns:
- true if the cursor is before the first row; false if the cursor is at any other position or the result set contains no rows

#### isFirst()

```
boolean isFirst() throws SQLException
```
Retrieves whether the cursor is on the first row of this ResultSet object.

Returns:
- true if the cursor is on the first row; false otherwise

*More: <https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html>*

### Get methods
resultSet.get**XXX**(), where **XXX is a primitive data type**. Columns can be selected via either **name** or **id**.

```
rs.getString(“columnName”)
rs.getLong(“columnName”)
rs.getInt(“columnName”)
rs.getDouble(“columnName”)

rs.getString(1)
rs.getLong(2)
rs.getInt(3)
rs.getDouble(4)
```

```
ResultSet rs = statement.executeQuery(
 "SELECT * FROM person");

while(rs.next()) {
 rs.getInt(“_id”);
 rs.getString(“cf”);
 rs.getString(“name”);
 rs.getString(“surname”);
}
```

```
ResultSet rs = statement.executeQuery(
 "SELECT * FROM person");

while(rs.next()) {
 rs.getInt(1);
 rs.getString(2);
 rs.getString(3);
 rs.getString(4);
}
```

```
int idIndex = rs.findColumn(“_id”);
int cfIndex = rs.findColumn(“cf”);
int nameIndex = rs.findColumn(“name”);
int surnameIndex = rs.findColumn(“surname”);

while(rs.next()) {
    int id = rs.getInt(idIndex);
    String cf = rs.getString(cfIndex);
    String name = rs.getString(nameIndex);
    String surname = rs.getString(surnameIndex);
}
```
*If you need to access by column id but know only the column names*

### 6. Close Connection
Programs should recover from errors and **always** leave the database in a consistent state. **Runtime errors must be minimized in industrial applications!**
If a statement throws an exception, it must be caught within a catch statement.
The **finally {…}** clause can be used to leave the database in a consistent state.

```
connection = null;
statement = null;

try {
        …
} catch(SQLException e) {
 // do something
} finally {
 if (connection != null) {
  statement.close();
  connection.close();
 }
}
```
### Types
There are significant variations between the SQL types supported by different database products. For example, most of the major databases support an SQL data type for large binary values, but Oracle calls this type LONG RAW, Sybase calls it IMAGE and Informix calls it BYTE.
- JDBC programmers mostly program with existing database tables, and they need not concern themselves with the exact SQL type names that were used.
- The one major place where programmers may **need to use SQL type names** is in the SQL CREATE TABLE statement when they are **creating a new database table**. In this case programmers must take care to use SQL type names that are supported by their target database.

### Mapping JDBC to Java types
![](images/jdbc-mapping-types.png)

# Advanced Result Set
ResultSet are iterator-like objects
With default ResultSets (TYPE_FORWARD_ONLY):
- It is **not possible to move back and forth** with a default. 
Only **next()** can be called. 
- It is **not possible to modify the data with dedicated methods** and, transparently, the database. Data have to be manipulated in memory and stored back with another operation (**statement.executeUpdate()**). 

#### **createStatement**

```
Statement createStatement(int resultSetType,
                          int resultSetConcurrency)
                   throws SQLException
```
Creates a *Statement* object that will generate ResultSet objects with the given type and concurrency. It allows the default result set type and concurrency to be overridden. The holdability of the created result sets can be determined by calling getHoldability().

Parameters:

- resultSetType - a result set type;one of ResultSet.TYPE_FORWARD_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, or ResultSet.TYPE_SCROLL_SENSITIVE
- resultSetConcurrency - a concurrency type; one of ResultSet.CONCUR_READ_ONLY or ResultSet.CONCUR_UPDATABLE

Returns:
- a *new Statement* object that will generate ResultSet objects with the given type and concurrency.

### JDBC – Scrollable ResultSet

```
Statement s = c.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE | ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

ResultSet rs = s.executeQuery(“SELECT * FROM person”);

rs.previous();     // go 1 record back
rs.relative(-5);   // go 5 records back
rs.relative(7);    // go 7 records forward
rs.absolute(100);  // go to 100th record
```

### JDBC – Updateable ResultSet

```
Statement s = c.createStatement(
 ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

ResultSet rs = s.executeQuery(“SELECT * FROM students WHERE type=‘listening’”);

while (rs.next()) {
 int grade = rs.getInt(“grade”);
 rs.updateInt(“grade”, grade + 1);
 rs.updateRow();
}
```
### Connection MetaData
A **Connection** object provides a **DatabaseMetaData** object which is able to provide **schema** information describing:
- tables
- supported SQL grammar
- *supported capabilities of the connection*
- stored procedures

**What is a stored procedure? A group of SQL statements forming a logical unit aimed at performing a specific task*

```
// Establish Connection
Class.forName("org.sqlite.JDBC");
Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

// Get metadata
DatabaseMetaData md = connection.getMetaData();   

// Verify ResultSet supported types
System.out.println("-- ResultSet Type --");
System.out.println("Supports TYPE_FORWARD_ONLY: ”
 + md.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));

System.out.println("Supports TYPE_SCROLL_INSENSITIVE: ”
 + md.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));

System.out.println("Supports TYPE_SCROLL_SENSITIVE: ”
 + md.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
```

### ResultSet MetaData
A **ResultSet** object provides a **ResultSetMetaData** object providing **schema information**.
- Useful for writing code running on different tables. For example, converting in JSON or XML the output of different queries.

```
public static void printRS(ResultSet rs) throws SQLException {
 ResultSetMetaData md = rs.getMetaData();
 // get number of columns
 int nCols = md.getColumnCount();
 // print column names
 for(int i=1; i < nCols; ++i)
  System.out.print(md.getColumnName(i) + ",");
```

# Transactions

### Definition
A transaction is a **set of actions to be performed atomically**. **Either all the actions are carried out, or none of them are.**

The classic example of when transactions are necessary is the example of bank accounts. You need to transfer $100 from one account to the other. You do so by subtracting $100 from the first account and adding $100 to the second account. If this process fails after you have subtracted the $100 from the first bank account, the $100 is never added to the second bank account. The money is lost in cyberspace.

### JDBC Transactions

- JDBC allows SQL statements to be grouped together into a single transaction
- Transaction control is performed by the Connection object, default mode is auto-commit, i.e., each sql statement is treated as a transaction
- We can turn off the auto-commit mode with connection.setAutoCommit(false);
- And turn it back on with connection.setAutoCommit(true);
- Once auto-commit is off, no SQL statement will be committed until an explicit is invoked connection.commit(). At this point all changes done by the SQL statements will be made permanent in the database.

```
Connection connection = ...
try {
    connection.setAutoCommit(false);

    // create and execute statements etc.

    connection.commit();
} catch(Exception e) {
    connection.rollback();
} finally {
    if(connection != null) {
        connection.close();
    }
}
```
