# Portal Management System

A comprehensive Java Swing-based user management system with full CRUD operations and advanced features.

## Features

### User Management (Admin Only)
- **Complete CRUD Operations**: Add, Edit, Delete, and View users
- **Search & Filter**: Search by username and filter by role
- **Password Reset**: Reset passwords for any user
- **User Details**: View comprehensive user information
- **Data Validation**: Robust input validation and error handling
- **Export/Import**: CSV export and import functionality
- **Bulk Operations**: Support for bulk user operations

### Security Features
- **Role-based Access Control**: Different interfaces for Admin, Instructor, and Student roles
- **Authentication**: Secure login system
- **Self-protection**: Users cannot delete their own accounts
- **Input Validation**: Comprehensive validation for all user inputs

### User Interface
- **Professional Design**: Modern, clean interface with styled components
- **Responsive Layout**: Properly organized panels and components
- **Keyboard Shortcuts**: Support for Enter key login and ESC key cancellation
- **Status Information**: Real-time status updates and user feedback
- **Error Handling**: User-friendly error messages and confirmations

## Default Login Credentials

- **Username**: admin
- **Password**: admin123
- **Role**: ADMIN

Additional test users are automatically created:
- student1/pass123 (STUDENT)
- instructor1/pass123 (INSTRUCTOR)
- john_doe/password (STUDENT)
- jane_smith/password (INSTRUCTOR)

## System Requirements

- Java 8 or higher
- Windows operating system (for batch files)
- Internet connection (for initial SQLite JDBC driver download)

## Installation & Running

### Method 1: Using Batch Files (Recommended)
1. Double-click `build.bat` to compile the application
2. Double-click `run.bat` to start the application

### Method 2: Manual Compilation
1. Download SQLite JDBC driver and place in `lib/` folder
2. Compile: `javac -cp "lib\sqlite-jdbc-3.42.0.0.jar" -d out src\main\java\edu\portal\**\*.java`
3. Run: `java -cp "out;lib\sqlite-jdbc-3.42.0.0.jar" edu.portal.ui.LoginFrame`

## Project Structure

```
src/main/java/edu/portal/
├── model/
│   └── User.java                 # User data model
├── dao/
│   ├── Database.java            # Database connection and initialization
│   └── UserDAO.java             # User data access operations
├── service/
│   └── UserService.java         # Business logic layer
└── ui/
    ├── LoginFrame.java          # Login interface
    ├── MainFrame.java           # Main application window
    ├── UserDialog.java          # User add/edit dialog
    └── UserTableModel.java      # Table model for user display
```

## Database

The application uses SQLite database (`portal.db`) which is automatically created on first run. The database includes:

- **users** table with fields: id, username, password, role
- Automatic creation of default admin and sample users
- Role validation (ADMIN, STUDENT, INSTRUCTOR)

## User Roles & Permissions

### ADMIN
- Full user management capabilities
- Add, edit, delete users
- Reset passwords
- Export/import user data
- View all system information

### INSTRUCTOR
- View courses and students (placeholder)
- Manage course content (placeholder)

### STUDENT
- View enrolled courses (placeholder)
- View grades (placeholder)

## Advanced Features

### Search & Filter
- Real-time search by username
- Filter users by role
- Combined search and filter operations

### Export/Import
- Export user data to CSV format
- Import users from CSV files
- Automatic error handling and reporting

### Data Validation
- Username: 3+ characters, alphanumeric and underscore only
- Password: 4+ characters minimum
- Role: Must be ADMIN, STUDENT, or INSTRUCTOR
- Duplicate username prevention

### User Experience
- Confirmation dialogs for destructive operations
- Loading indicators for database operations
- Comprehensive error messages
- Keyboard navigation support

## Technical Implementation

### Architecture
- **MVC Pattern**: Clear separation of Model, View, and Controller
- **Singleton Pattern**: UserService for centralized business logic
- **DAO Pattern**: Database access abstraction
- **Observer Pattern**: Table model updates

### Database Operations
- **Connection Pooling**: Efficient database connection management
- **Prepared Statements**: SQL injection prevention
- **Transaction Support**: Data integrity assurance
- **Error Handling**: Comprehensive exception management

### UI Components
- **Swing Components**: Professional desktop application interface
- **Custom Styling**: Consistent color scheme and fonts
- **Responsive Design**: Proper layout management
- **Background Processing**: SwingWorker for non-blocking operations

## Troubleshooting

### Common Issues

1. **Compilation Errors**
   - Ensure Java is installed and in PATH
   - Check if SQLite JDBC driver is downloaded

2. **Database Issues**
   - Delete `portal.db` file to reset database
   - Check file permissions in application directory

3. **Login Problems**
   - Use default credentials: admin/admin123
   - Check if database was initialized properly

### Error Messages
- "Invalid username or password": Check credentials
- "Username already exists": Choose different username
- "Failed to load users": Database connection issue

## Future Enhancements

- Course management system
- Grade management
- Email notifications
- Advanced reporting
- User activity logging
- Password encryption
- Multi-language support

## License

This project is developed for educational purposes.