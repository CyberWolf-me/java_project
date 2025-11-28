# JavaFX Setup Guide for Color Hunt Game

Since you're using Java 24, you need to download and install JavaFX separately (JavaFX was removed from Java after version 10).

## Quick Setup Steps

### Step 1: Download JavaFX SDK

1. Go to: https://openjfx.io/
2. Click "Download" and select:
   - **Version**: 17 or 21 (recommended: 21 for Java 24)
   - **Platform**: Windows
   - **SDK**: JavaFX SDK
3. Download and extract the ZIP file to a location like:
   - `C:\javafx-sdk-21` (or `C:\javafx-sdk-17`)

### Step 2: Update the Run Script

1. Open `run.ps1` (or `run.bat`) in a text editor
2. Find the line: `$JAVAFX_PATH = "C:\javafx-sdk-17"`
3. Change it to match your JavaFX installation path:
   ```powershell
   $JAVAFX_PATH = "C:\javafx-sdk-21"  # Update this path!
   ```

### Step 3: Run the Game

**In PowerShell:**
```powershell
.\run.ps1
```

**Or in Command Prompt:**
```cmd
run.bat
```

## Alternative: Manual Commands

If you prefer to compile and run manually:

```powershell
# Replace C:\javafx-sdk-21 with your actual JavaFX path
$JAVAFX = "C:\javafx-sdk-21"

# Compile
javac --module-path "$JAVAFX\lib" --add-modules javafx.controls -d out src\main\java\com\colorhunt\*.java

# Run
java --module-path "$JAVAFX\lib" --add-modules javafx.controls -cp out com.colorhunt.ColorHuntGame
```

## Troubleshooting

### Error: "JavaFX not found"
- Make sure you downloaded the **SDK** (not just the runtime)
- Check that the path in the script matches where you extracted JavaFX
- Verify the `lib` folder exists inside your JavaFX directory

### Error: "Module not found"
- Make sure you're using `--add-modules javafx.controls`
- Check that JavaFX version is compatible with your Java version (Java 21+ works with JavaFX 21)

### Still having issues?
Try using an IDE like IntelliJ IDEA or Eclipse, which can help manage JavaFX dependencies automatically.

