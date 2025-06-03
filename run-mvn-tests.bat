cd /d "C:\Users\indan\OneDrive\Documents\API\RestAssuredAPIApplicationFramework"

:: Create logs folder if it doesn't exist
if not exist Logs_from_MavenConsole mkdir Logs_from_MavenConsole

:: Get timestamp (yyyy-MM-dd_HH-mm-ss)
for /f %%a in ('powershell -Command "Get-Date -Format yyyy-MM-dd_HH-mm-ss"') do set timestamp=%%a

:: Run mvn test and store logs
mvn test verify  > Logs_from_MavenConsole\test-log-%timestamp%.txt 2>&1

:: Beep on completion
powershell -c "[console]::beep(1000,5000)"

echo.
echo ✅ Test run complete.
echo 📄 Logs saved to: Logs_from_MavenConsole\test-log-%timestamp%.txt
pause
