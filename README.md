# Color Hunt Game

A time-based, interactive desktop game that tests your ability to quickly match colors with words. Inspired by the Stroop effect, this game challenges players to click the correct color button as quickly as possible to score points.

## Features

- **Color-Word Display**: A word is displayed on screen (e.g., "RED") in a random font color that may not match the word itself
- **Multiple Choice Buttons**: 4 color buttons for user selection
- **Scoring System**: Correct clicks = +1 point
- **Timer/Countdown**: 30-second game duration with visual countdown
- **Game Over & Restart**: Display final score and high score tracking with restart option
- **Polished UI**: Beautiful gradient background, smooth animations, and visual feedback

## Technologies Used

- **Java 11+** (or Java 8+)
- **JavaFX** - For GUI, buttons, labels, layout, and animations

## How to Run

### Option 1: Quick Start (Try This First!)

**For Windows PowerShell:**
```powershell
.\run-simple.bat
```

**Or if you have JavaFX SDK installed:**
```powershell
.\run.ps1
```

**For Command Prompt (CMD):**
```cmd
run-simple.bat
```

**Note:** If you have Java 8-10, JavaFX is built-in and `run-simple.bat` should work. For Java 11+, you'll need JavaFX SDK.

### Option 2: Using JavaFX SDK (Java 11+)

1. **Download JavaFX SDK** (if not already installed):
   - Download from: https://openjfx.io/
   - Extract to a location (e.g., `C:\javafx-sdk-17`)

2. **Update the script**:
   - Open `run.bat` or `run.ps1`
   - Change `JAVAFX_PATH` to your JavaFX installation path

3. **Run the script**:
   ```powershell
   .\run.bat        # For CMD
   .\run.ps1        # For PowerShell
   ```

### Option 3: Manual Compilation & Run

**If you have Java 8-10 (JavaFX built-in):**
```bash
javac -d out src/main/java/com/colorhunt/*.java
java -cp out com.colorhunt.ColorHuntGame
```

**If you have Java 11+ with JavaFX SDK:**
```bash
javac --module-path <path-to-javafx-sdk>/lib --add-modules javafx.controls -d out src/main/java/com/colorhunt/*.java
java --module-path <path-to-javafx-sdk>/lib --add-modules javafx.controls -cp out com.colorhunt.ColorHuntGame
```

### Option 4: Using Maven

1. **Install Maven** (if not already installed)

2. **Run with Maven**:
   ```bash
   mvn clean javafx:run
   ```

### Option 3: Using an IDE (IntelliJ IDEA, Eclipse, etc.)

1. **Set up JavaFX**:
   - Add JavaFX library to your project
   - Configure VM options: `--module-path <path-to-javafx>/lib --add-modules javafx.controls`

2. **Run the `ColorHuntGame` class**

## How to Play

1. **Objective**: Click the button that matches the **color** of the displayed word (not the word itself!)
2. **Example**: If you see "RED" written in **blue**, you should click the **BLUE** button
3. **Scoring**: Each correct answer gives you 1 point
4. **Timer**: You have 30 seconds to score as many points as possible
5. **Game Over**: When time runs out, your final score is displayed
6. **Restart**: Click "Play Again" to start a new game

## Game Mechanics

- The game displays a color word (RED, BLUE, GREEN, YELLOW, PURPLE, ORANGE) in a random font color
- You must identify the **actual color** of the text, not the word itself
- This creates a cognitive challenge known as the Stroop effect
- The timer turns red when 10 seconds or less remain
- High scores are tracked during your session

## Project Structure

```
java_project/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── colorhunt/
│                   └── ColorHuntGame.java
├── pom.xml (optional - for Maven)
└── README.md
```

## Learning Outcomes

This project demonstrates:
- **GUI Development**: JavaFX layouts, Labels, Buttons, Text formatting, Event-driven programming
- **Game Mechanics**: Time-based logic using Timeline, Scorekeeping
- **Programming Logic**: Random selection, User input comparison, Game state management
- **UI/UX Design**: Animations, Visual feedback, Polished interface

## Future Enhancements

Potential improvements you could add:
- Sound effects for correct/incorrect answers
- Difficulty levels (more buttons, faster timer, etc.)
- Persistent high score tracking (file I/O)
- More color options
- Achievement system
- Multiplayer mode

## Requirements

- Java 8 or higher
- JavaFX SDK (OpenJFX) - Download from https://openjfx.io/

## License

This project is open source and available for educational purposes.

