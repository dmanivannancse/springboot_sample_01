@echo off
echo Checking Java Installation
echo =========================
echo.

echo Current JAVA_HOME:
echo %JAVA_HOME%
echo.

echo Current PATH (Java-related entries):
echo %PATH% | findstr /i java
echo.

echo Checking if java.exe exists in JAVA_HOME\bin:
if exist "%JAVA_HOME%\bin\java.exe" (
    echo ✓ java.exe found at %JAVA_HOME%\bin\java.exe
) else (
    echo ✗ java.exe NOT found at %JAVA_HOME%\bin\java.exe
)
echo.

echo Trying to run java -version:
java -version 2>&1
echo.

echo Checking common Java installation locations:
if exist "C:\Program Files\Java\" (
    echo Found Java installations:
    dir "C:\Program Files\Java\" /b
) else (
    echo No Java found in C:\Program Files\Java\
)
echo.

if exist "C:\Program Files\Eclipse Adoptium\" (
    echo Found Eclipse Adoptium installations:
    dir "C:\Program Files\Eclipse Adoptium\" /b
)
echo.

pause