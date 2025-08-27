@echo off
echo Portal Management System - Fixed Compilation

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

echo Java found: %JAVA_HOME%
"%JAVA_BIN%\java.exe" -version

REM Create directories
if not exist "classes" mkdir classes
if not exist "lib" mkdir lib

echo.
echo Compiling Java source files...

REM Compile all Java files
"%JAVA_BIN%\javac.exe" -d classes -cp "lib/*" src/main/java/edu/portal/model/*.java
if errorlevel 1 (
    echo ERROR: Failed to compile model classes
    pause
    exit /b 1
)

"%JAVA_BIN%\javac.exe" -d classes -cp "lib/*;classes" src/main/java/edu/portal/dao/*.java
if errorlevel 1 (
    echo ERROR: Failed to compile DAO classes
    pause
    exit /b 1
)

"%JAVA_BIN%\javac.exe" -d classes -cp "lib/*;classes" src/main/java/edu/portal/service/*.java
if errorlevel 1 (
    echo ERROR: Failed to compile service classes
    pause
    exit /b 1
)

"%JAVA_BIN%\javac.exe" -d classes -cp "lib/*;classes" src/main/java/edu/portal/ui/*.java
if errorlevel 1 (
    echo ERROR: Failed to compile UI classes
    pause
    exit /b 1
)

echo.
echo ✅ Compilation successful!
echo All classes compiled to 'classes' directory
echo.
echo You can now run the application using: run_fixed.bat
pause