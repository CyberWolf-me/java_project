@echo off
REM Color Hunt Game - Run Script for Windows
REM Make sure JavaFX SDK is installed and set JAVAFX_PATH below

REM IMPORTANT: Update this path to where you extracted JavaFX SDK
REM Download from: https://openjfx.io/
set JAVAFX_PATH=D:\java\java_project\javafx-sdk-25.0.1
set MAIN_CLASS=com.colorhunt.ColorHuntGame

echo Compiling Color Hunt Game...
javac --module-path %JAVAFX_PATH%\lib --add-modules javafx.controls -d out src\main\java\com\colorhunt\*.java src\main\java\com\colorhunt\model\*.java src\main\java\com\colorhunt\view\*.java src\main\java\com\colorhunt\controller\*.java src\main\java\com\colorhunt\util\*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed! Please check your JavaFX path.
    pause
    exit /b 1
)

echo.
echo Running Color Hunt Game...
echo.
java --module-path %JAVAFX_PATH%\lib --add-modules javafx.controls --enable-native-access=javafx.graphics -cp out %MAIN_CLASS%

pause

