# Portal Management System - Setup Instructions

## Prerequisites

1. **Java Development Kit (JDK) 8 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Or use OpenJDK: https://openjdk.org/
   - Make sure `java` and `javac` commands are available in your PATH

2. **SQLite JDBC Driver**
   - Download `sqlite-jdbc-3.42.0.0.jar` from:
     https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.42.0.0/sqlite-jdbc-3.42.0.0.jar
   - Place the downloaded JAR file in the `lib\` folder

## Quick Setup Steps

1. **Install Java JDK**
   - Download and install Java JDK 8 or higher
   - Verify installation by opening Command Prompt and typing: `java -version`

2. **Download SQLite JDBC Driver**
   - Create a `lib` folder in the project directory if it doesn't exist
   - Download the SQLite JDBC JAR file and place it in the `lib` folder

3. **Compile the Application**
   - Double-click `compile.bat` or run it from Command Prompt
   - Wait for compilation to complete

4. **Run the Application**
   - Double-click `start_app.bat` or run it from Command Prompt
   - Use default credentials: admin/admin123

## Manual Compilation (Alternative)

If the batch files don't work, you can compile manually:

```cmd
# Create classes directory
mkdir classes

# Compile Java files
javac -cp "lib\sqlite-jdbc-3.42.0.0.jar" -d classes src\main\java\edu\portal\model\*.java src\main\java\edu\portal\dao\*.java src\main\java\edu\portal\service\*.java src\main\java\edu\portal\ui\*.java

# Run the application
java -cp "classes;lib\sqlite-jdbc-3.42.0.0.jar" edu.portal.ui.LoginFrame
```

## Default Login Credentials

- **Username**: admin
- **Password**: admin123
- **Role**: ADMIN

Additional test users:
- student1/pass123 (STUDENT)
- instructor1/pass123 (INSTRUCTOR)
- john_doe/password (STUDENT)
- jane_smith/password (INSTRUCTOR)

## Troubleshooting

### "Java is not recognized"
- Install Java JDK and add it to your system PATH
- Restart Command Prompt after installation

### "SQLite JDBC driver not found"
- Download the JAR file and place it in the `lib\` folder
- Make sure the filename is exactly: `sqlite-jdbc-3.42.0.0.jar`

### Compilation Errors
- Make sure all Java source files are in the correct directory structure
- Check that the SQLite JDBC driver is in the `lib\` folder
- Verify Java JDK is properly installed

### Application Won't Start
- Make sure compilation was successful (check for .class files in `classes\` folder)
- Verify SQLite JDBC driver is present
- Check that you're using the correct login credentials

## Features

Once running, the application provides:

### Admin Features
- Complete user management (Add, Edit, Delete users)
- Search and filter users by role
- Reset user passwords
- Export/Import user data (CSV format)
- View detailed user information

### Security Features
- Role-based access control
- Input validation
- Protection against self-deletion
- Secure authentication

### User Interface
- Professional Swing-based GUI
- Responsive design
- Keyboard shortcuts
- Real-time feedback and error handling

## Project Structure

```
src/main/java/edu/portal/
├── model/User.java          # User data model
├── dao/
│   ├── Database.java        # Database connection
│   └── UserDAO.java         # Data access layer
├── service/UserService.java # Business logic
└── ui/                      # User interface components
    ├── LoginFrame.java
    ├── MainFrame.java
    ├── UserDialog.java
    └── UserTableModel.java
```

## Support

If you encounter any issues:
1. Check this setup guide
2. Verify all prerequisites are met
3. Try manual compilation steps
4. Check error messages in Command Prompt

The application creates a SQLite database file (`portal.db`) automatically on first run.