@echo off
echo Starting Portal Management System...

REM Check if compiled
if not exist "out\edu\portal\ui\LoginFrame.class" (
    echo Application not compiled. Running build first...
    call build.bat
)

REM Run the application
java -cp "out;lib\sqlite-jdbc-3.42.0.0.jar" edu.portal.ui.LoginFrame

pause