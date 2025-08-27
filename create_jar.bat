@echo off
echo Creating JAR file for Portal Management System...

REM Check if compiled
if not exist "classes\edu\portal\ui\LoginFrame.class" (
    echo Application not compiled. Please run compile.bat first.
    pause
    exit /b 1
)

REM Create manifest file
echo Main-Class: edu.portal.ui.LoginFrame > manifest.txt
echo Class-Path: lib/sqlite-jdbc-3.42.0.0.jar >> manifest.txt

REM Create JAR file
jar cfm PortalManagement.jar manifest.txt -C classes .

REM Clean up
del manifest.txt

if exist "PortalManagement.jar" (
    echo.
    echo JAR file created successfully: PortalManagement.jar
    echo.
    echo To run the JAR file:
    echo java -jar PortalManagement.jar
    echo.
    echo Note: Make sure lib\sqlite-jdbc-3.42.0.0.jar is in the same directory
) else (
    echo Failed to create JAR file.
)

pause