#!/bin/bash
# Color Hunt Game - Run Script for Linux/Mac
# Make sure JavaFX SDK is installed and set JAVAFX_PATH below

JAVAFX_PATH="/path/to/javafx-sdk-17"
MAIN_CLASS="com.colorhunt.ColorHuntGame"

echo "Compiling Color Hunt Game..."
javac --module-path "$JAVAFX_PATH/lib" --add-modules javafx.controls -d out src/main/java/com/colorhunt/*.java

if [ $? -ne 0 ]; then
    echo "Compilation failed! Please check your JavaFX path."
    exit 1
fi

echo ""
echo "Running Color Hunt Game..."
echo ""
java --module-path "$JAVAFX_PATH/lib" --add-modules javafx.controls -cp out $MAIN_CLASS

