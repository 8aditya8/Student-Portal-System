@echo off
echo Portal Management System - Fixed Runner

REM Set Java path
set JAVA_HOME=C:\Program Files\Java\jdk-24
set JAVA_BIN=%JAVA_HOME%\bin
set PATH=%JAVA_BIN%;%PATH%

REM Check if Java is available
"%JAVA_BIN%\java.exe" -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java not found at %JAVA_HOME%
    echo Please check your Java installation
    pause
    exit /b 1
)

REM Check if classes directory exists
if not exist "classes" (
    echo ERROR: Classes directory not found!
    echo Please run compile_fixed.bat first
    pause
    exit /b 1
)

echo.
echo 🚀 Starting Portal Management System...
echo.
echo Login Credentials:
echo ==================
echo Admin:    username: admin    password: admin
echo Student:  username: student  password: student
echo.
echo Features:
echo - Enhanced Admin Dashboard with 6 unique features
echo - Enhanced Student Dashboard with 6 unique features
echo - Modern UI with attractive design
echo - Real-time updates and interactive elements
echo.

REM Run the application
"%JAVA_BIN%\java.exe" -cp "classes;lib/*" edu.portal.ui.LoginFrame

echo.
echo Application closed.
pause