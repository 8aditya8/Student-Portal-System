@echo off
echo Starting Portal Management System...

REM Check if compiled
if not exist "classes\edu\portal\ui\LoginFrame.class" (
    echo Application not compiled. Please run compile.bat first.
    pause
    exit /b 1
)

REM Check for SQLite JDBC driver
if not exist "lib\sqlite-jdbc-3.42.0.0.jar" (
    echo SQLite JDBC driver not found in lib\ folder.
    echo Please download and place sqlite-jdbc-3.42.0.0.jar in the lib\ folder.
    pause
    exit /b 1
)

echo.
echo Default login credentials:
echo Username: admin
echo Password: admin123
echo.

REM Run the application
java -cp "classes;lib\sqlite-jdbc-3.42.0.0.jar" edu.portal.ui.LoginFrame

pause