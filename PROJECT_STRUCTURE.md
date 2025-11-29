# Color Hunt Game - Project Structure

## MVC Architecture

The project has been refactored into a clean Model-View-Controller (MVC) architecture for better code organization and maintainability.

## Directory Structure

```
com.colorhunt/
├── model/
│   ├── GameState.java       # Game state data (score, timer, streak, highScore)
│   ├── Question.java        # Question model (word, color, answer options)
│   └── ColorData.java       # Color constants & utilities
│
├── controller/
│   └── GameController.java  # Game logic & event handling
│
├── view/
│   ├── GameView.java        # Main game UI components
│   └── GameOverView.java    # Game over screen UI
│
├── util/
│   └── ColorUtils.java      # Color utility functions (hex conversion, etc.)
│
└── ColorHuntGame.java       # Main application entry point
```

## Component Responsibilities

### Model Layer (`model/`)
- **GameState.java**: Manages all game state data
  - Score, timer, streak tracking
  - High score management
  - Game active status
  
- **Question.java**: Represents a color-word question
  - Word and color pairing
  - Correct answer and answer options
  - Question generation logic
  
- **ColorData.java**: Color constants and data
  - Color names and Color objects
  - Color lookup utilities

### View Layer (`view/`)
- **GameView.java**: Main game UI
  - Timer, score, streak displays
  - Color word display
  - Color button UI
  - UI update methods
  
- **GameOverView.java**: Game over screen
  - Final score display
  - Restart button
  - High score notification

### Controller Layer (`controller/`)
- **GameController.java**: Game logic coordinator
  - Handles button clicks
  - Manages game flow (start, end, restart)
  - Coordinates between model and view
  - Timer management

### Utility Layer (`util/`)
- **ColorUtils.java**: Color operations
  - Color to hex string conversion
  - Color darkening for borders

### Main Application
- **ColorHuntGame.java**: Application entry point
  - JavaFX Application setup
  - Initializes controller
  - Creates main window

## Benefits of This Structure

1. **Separation of Concerns**: Each layer has a clear responsibility
2. **Maintainability**: Easy to find and modify specific functionality
3. **Testability**: Models and controllers can be tested independently
4. **Scalability**: Easy to add new features without affecting other parts
5. **Code Reusability**: Models and utilities can be reused

## How It Works

1. **ColorHuntGame** creates the main window and initializes **GameController**
2. **GameController** creates and manages **GameState**, **GameView**, and **GameOverView**
3. User interactions trigger controller methods
4. Controller updates the model (GameState, Question)
5. View observes model changes and updates UI accordingly

## Compilation

All Java files need to be compiled together:

```bash
javac --module-path <javafx-path>/lib --add-modules javafx.controls \
  -d out \
  src/main/java/com/colorhunt/*.java \
  src/main/java/com/colorhunt/model/*.java \
  src/main/java/com/colorhunt/view/*.java \
  src/main/java/com/colorhunt/controller/*.java \
  src/main/java/com/colorhunt/util/*.java
```

The run scripts (`run.bat`, `run.ps1`) have been updated to compile all files automatically.

