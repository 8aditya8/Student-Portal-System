@echo off
echo Compiling Portal Management System...

REM Create output directory
if not exist "out" mkdir out

REM Download SQLite JDBC driver if not present
if not exist "lib\sqlite-jdbc-3.42.0.0.jar" (
    echo Downloading SQLite JDBC driver...
    if not exist "lib" mkdir lib
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.42.0.0/sqlite-jdbc-3.42.0.0.jar' -OutFile 'lib\sqlite-jdbc-3.42.0.0.jar'"
)

REM Compile Java files
javac -cp "lib\sqlite-jdbc-3.42.0.0.jar" -d out src\main\java\edu\portal\model\*.java src\main\java\edu\portal\dao\*.java src\main\java\edu\portal\service\*.java src\main\java\edu\portal\ui\*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
    echo Run the application with: run.bat
) else (
    echo Compilation failed!
)

pause