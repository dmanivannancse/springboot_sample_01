@echo off
echo Setting up OAuth2 Environment Variables
echo =====================================
echo.
echo Please replace the placeholder values with your actual OAuth2 credentials:
echo.

set /p GOOGLE_CLIENT_ID="Enter your Google Client ID: "
set /p GOOGLE_CLIENT_SECRET="Enter your Google Client Secret: "
set /p GITHUB_CLIENT_ID="Enter your GitHub Client ID: "
set /p GITHUB_CLIENT_SECRET="Enter your GitHub Client Secret: "
set /p KEYCLOAK_CLIENT_ID="Enter your Keycloak Client ID: "
set /p KEYCLOAK_CLIENT_SECRET="Enter your Keycloak Client Secret: "
echo.
echo Setting environment variables...
setx GOOGLE_CLIENT_ID "%GOOGLE_CLIENT_ID%"
setx GOOGLE_CLIENT_SECRET "%GOOGLE_CLIENT_SECRET%"
setx GITHUB_CLIENT_ID "%GITHUB_CLIENT_ID%"
setx GITHUB_CLIENT_SECRET "%GITHUB_CLIENT_SECRET%"
setx KEYCLOAK_CLIENT_ID "%KEYCLOAK_CLIENT_ID%"
setx KEYCLOAK_CLIENT_SECRET "%KEYCLOAK_CLIENT_SECRET%"

echo.
echo Environment variables have been set!
echo Please restart your command prompt or IDE for the changes to take effect.
echo.
pause