@echo off
echo Checking Java and Maven Installation
echo ====================================
echo.

echo Checking Java...
java -version
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 and add to PATH
) else (
    echo Java is installed correctly!
)

echo.
echo Checking Maven...
mvn -version
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and add to PATH
) else (
    echo Maven is installed correctly!
)

echo.
echo Environment Variables:
echo JAVA_HOME: %JAVA_HOME%
echo MAVEN_HOME: %MAVEN_HOME%
echo.
pause