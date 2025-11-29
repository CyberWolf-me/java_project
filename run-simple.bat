@echo off
REM Simple run script - tries to run without JavaFX module path (for Java 8-10)
REM If this doesn't work, use run.bat and set your JavaFX path

echo Compiling Color Hunt Game...
javac -d out src\main\java\com\colorhunt\*.java src\main\java\com\colorhunt\model\*.java src\main\java\com\colorhunt\view\*.java src\main\java\com\colorhunt\controller\*.java src\main\java\com\colorhunt\util\*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Running Color Hunt Game...
echo.
java -cp out com.colorhunt.ColorHuntGame

pause

