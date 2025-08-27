@echo off
echo Java PATH Fix Utility
echo =====================

REM Check if running as administrator
net session >nul 2>&1
if %errorLevel% == 0 (
    echo Running as Administrator ✅
) else (
    echo.
    echo ⚠️  This script needs to run as Administrator to modify system PATH
    echo.
    echo Please:
    echo 1. Right-click on this file
    echo 2. Select "Run as administrator"
    echo 3. Click "Yes" when prompted
    echo.
    pause
    exit /b 1
)

set JAVA_HOME=C:\Program Files\Java\jdk-24
set JAVA_BIN=%JAVA_HOME%\bin

echo.
echo Java installation found at: %JAVA_HOME%
echo.

REM Test Java
"%JAVA_BIN%\java.exe" -version
if errorlevel 1 (
    echo ERROR: Java executable not found!
    pause
    exit /b 1
)

echo.
echo Adding Java to system PATH...

REM Add to system PATH (requires admin privileges)
setx PATH "%PATH%;%JAVA_BIN%" /M

echo.
echo ✅ Java has been added to system PATH!
echo.
echo Please:
echo 1. Close this command prompt
echo 2. Open a new command prompt
echo 3. Test with: java -version
echo.
echo After that, you can use the regular compile.bat and run.bat files
pause