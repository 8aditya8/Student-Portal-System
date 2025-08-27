@echo off
echo Portal Management System - Simple Compilation
echo.

REM Check if Java is available
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK 8 or higher and add it to your PATH
    echo Download from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Create directories
if not exist "classes" mkdir classes
if not exist "lib" mkdir lib

echo Checking for SQLite JDBC driver...
if not exist "lib\sqlite-jdbc-3.42.0.0.jar" (
    echo SQLite JDBC driver not found.
    echo Please download sqlite-jdbc-3.42.0.0.jar from:
    echo https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.42.0.0/sqlite-jdbc-3.42.0.0.jar
    echo and place it in the lib\ folder
    pause
    exit /b 1
)

echo Compiling Java source files...
javac -cp "lib\sqlite-jdbc-3.42.0.0.jar" -d classes src\main\java\edu\portal\model\*.java src\main\java\edu\portal\dao\*.java src\main\java\edu\portal\service\*.java src\main\java\edu\portal\ui\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilation successful!
    echo Run the application with: start_app.bat
) else (
    echo.
    echo Compilation failed! Please check the error messages above.
)

pause