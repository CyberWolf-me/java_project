# Color Hunt Game - PowerShell Run Script
# Make sure JavaFX SDK is installed and set JAVAFX_PATH below

# IMPORTANT: Update this path to where you extracted JavaFX SDK
# Download from: https://openjfx.io/
$JAVAFX_PATH = "D:\java\java_project\javafx-sdk-25.0.1"
$MAIN_CLASS = "com.colorhunt.ColorHuntGame"

# Check if JavaFX path exists
if (-not (Test-Path "$JAVAFX_PATH\lib")) {
    Write-Host "ERROR: JavaFX not found at: $JAVAFX_PATH" -ForegroundColor Red
    Write-Host "Please download JavaFX SDK from https://openjfx.io/" -ForegroundColor Yellow
    Write-Host "Or update JAVAFX_PATH in this script to point to your JavaFX installation." -ForegroundColor Yellow
    Write-Host ""
    Write-Host "If you have Java 8-10, JavaFX might be built-in. Try running without --module-path:" -ForegroundColor Cyan
    Write-Host "  javac -d out src\main\java\com\colorhunt\*.java" -ForegroundColor Cyan
    Write-Host "  java -cp out $MAIN_CLASS" -ForegroundColor Cyan
    pause
    exit 1
}

Write-Host "Compiling Color Hunt Game..." -ForegroundColor Green
javac --module-path "$JAVAFX_PATH\lib" --add-modules javafx.controls -d out src\main\java\com\colorhunt\*.java src\main\java\com\colorhunt\model\*.java src\main\java\com\colorhunt\view\*.java src\main\java\com\colorhunt\controller\*.java src\main\java\com\colorhunt\util\*.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed! Please check your JavaFX path." -ForegroundColor Red
    pause
    exit 1
}

Write-Host ""
Write-Host "Running Color Hunt Game..." -ForegroundColor Green
Write-Host ""
java --module-path "$JAVAFX_PATH\lib" --add-modules javafx.controls --enable-native-access=javafx.graphics -cp out $MAIN_CLASS

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "Run failed. If you have Java 8-10, try:" -ForegroundColor Yellow
    Write-Host "  java -cp out $MAIN_CLASS" -ForegroundColor Cyan
}

pause

