package com.colorhunt;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

/**
 * Color Hunt Game - A time-based game testing color-word matching skills
 * Inspired by the Stroop effect
 */
public class ColorHuntGame extends Application {
    
    // Game constants
    private static final int GAME_DURATION = 30; // seconds
    private static final int NUM_BUTTONS = 4; // Number of color buttons
    private static final String[] COLOR_NAMES = {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE"};
    private static final Color[] COLORS = {
        Color.RED, Color.BLUE, Color.GREEN, 
        Color.YELLOW, Color.PURPLE, Color.ORANGE
    };
    
    // Game state
    private int score = 0;
    private int timeRemaining = GAME_DURATION;
    private String currentWord;
    private Color currentWordColor;
    private Color correctAnswer;
    private boolean gameActive = false;
    
    // UI Components
    private Label wordLabel;
    private Label timerLabel;
    private Label scoreLabel;
    private VBox buttonContainer;
    private VBox mainContainer;
    private StackPane rootPane;
    private Timeline gameTimer;
    private Button restartButton;
    private Label gameOverLabel;
    private Label finalScoreLabel;
    private VBox gameOverContainer;
    
    // High score tracking
    private int highScore = 0;
    private Label highScoreLabel;
    private Label instructionLabel;
    private int streak = 0;
    private Label streakLabel;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Hunt Game");
        
        // Create root pane
        rootPane = new StackPane();
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3c72, #2a5298);");
        
        // Initialize main game UI
        initializeGameUI();
        
        // Show main game screen
        showGameScreen();
        
        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Start the game
        startGame();
    }
    
    /**
     * Initialize the main game UI components
     */
    private void initializeGameUI() {
        // Main container
        mainContainer = new VBox(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(40));
        
        // Top section - Timer and Score
        HBox topBar = new HBox(30);
        topBar.setAlignment(Pos.CENTER);
        
        // Timer label
        timerLabel = new Label("Time: " + timeRemaining);
        timerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setEffect(new Glow(0.8));
        
        // Score label
        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setEffect(new Glow(0.8));
        
        // High score label
        highScoreLabel = new Label("High Score: " + highScore);
        highScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        highScoreLabel.setTextFill(Color.LIGHTGRAY);
        
        // Streak label
        streakLabel = new Label("Streak: 0");
        streakLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        streakLabel.setTextFill(Color.GOLD);
        streakLabel.setEffect(new Glow(0.6));
        
        topBar.getChildren().addAll(timerLabel, scoreLabel, streakLabel, highScoreLabel);
        
        // Instruction label - explains the Stroop effect challenge
        instructionLabel = new Label("Click the button that matches the COLOR of the text (not the word!)");
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        instructionLabel.setTextFill(Color.LIGHTYELLOW);
        instructionLabel.setEffect(new Glow(0.5));
        instructionLabel.setWrapText(true);
        instructionLabel.setMaxWidth(600);
        instructionLabel.setAlignment(Pos.CENTER);
        instructionLabel.setPadding(new Insets(10));
        
        // Word display label
        wordLabel = new Label();
        wordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 72));
        wordLabel.setEffect(new DropShadow(10, Color.BLACK));
        wordLabel.setPadding(new Insets(20));
        
        // Button container
        buttonContainer = new VBox(15);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPadding(new Insets(20));
        
        // Add buttons
        for (int i = 0; i < NUM_BUTTONS; i++) {
            Button colorButton = createColorButton();
            buttonContainer.getChildren().add(colorButton);
        }
        
        // Assemble main container
        mainContainer.getChildren().addAll(topBar, instructionLabel, wordLabel, buttonContainer);
        
        // Game Over screen
        initializeGameOverScreen();
    }
    
    /**
     * Initialize the game over screen
     */
    private void initializeGameOverScreen() {
        gameOverContainer = new VBox(30);
        gameOverContainer.setAlignment(Pos.CENTER);
        gameOverContainer.setPadding(new Insets(40));
        gameOverContainer.setVisible(false);
        
        gameOverLabel = new Label("GAME OVER!");
        gameOverLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        gameOverLabel.setTextFill(Color.WHITE);
        gameOverLabel.setEffect(new Glow(1.0));
        
        finalScoreLabel = new Label();
        finalScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        finalScoreLabel.setTextFill(Color.WHITE);
        
        restartButton = new Button("Play Again");
        restartButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        restartButton.setPrefSize(200, 50);
        restartButton.setStyle(
            "-fx-background-color: #4CAF50; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 10; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);"
        );
        restartButton.setOnMouseEntered(e -> {
            restartButton.setStyle(
                "-fx-background-color: #45a049; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 7);"
            );
        });
        restartButton.setOnMouseExited(e -> {
            restartButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);"
            );
        });
        restartButton.setOnAction(e -> restartGame());
        
        gameOverContainer.getChildren().addAll(gameOverLabel, finalScoreLabel, restartButton);
    }
    
    /**
     * Create a styled color button
     */
    private Button createColorButton() {
        Button button = new Button();
        button.setPrefSize(250, 60);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        button.setStyle(
            "-fx-background-radius: 10; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5); " +
            "-fx-text-fill: white; " +
            "-fx-border-width: 3; " +
            "-fx-border-radius: 10;"
        );
        
        // Hover effects
        button.setOnMouseEntered(e -> {
            button.setStyle(
                button.getStyle() + 
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 7); " +
                "-fx-scale-x: 1.05; " +
                "-fx-scale-y: 1.05;"
            );
        });
        button.setOnMouseExited(e -> {
            String baseStyle = button.getStyle();
            baseStyle = baseStyle.replace("-fx-scale-x: 1.05; ", "");
            baseStyle = baseStyle.replace("-fx-scale-y: 1.05; ", "");
            baseStyle = baseStyle.replace("dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 7)", 
                                         "dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5)");
            button.setStyle(baseStyle);
        });
        
        button.setOnAction(e -> handleButtonClick(button));
        
        return button;
    }
    
    /**
     * Show the main game screen
     */
    private void showGameScreen() {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(mainContainer);
        gameOverContainer.setVisible(false);
    }
    
    /**
     * Show the game over screen
     */
    private void showGameOverScreen() {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(gameOverContainer);
        gameOverContainer.setVisible(true);
        
        finalScoreLabel.setText("Final Score: " + score);
        
        if (score > highScore) {
            highScore = score;
            highScoreLabel.setText("High Score: " + highScore);
            finalScoreLabel.setText("Final Score: " + score + " (NEW HIGH SCORE!)");
            finalScoreLabel.setTextFill(Color.GOLD);
        }
    }
    
    /**
     * Start a new game
     */
    private void startGame() {
        gameActive = true;
        score = 0;
        streak = 0;
        timeRemaining = GAME_DURATION;
        updateScore();
        updateStreak();
        updateTimer();
        generateNewQuestion();
        
        // Start countdown timer
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (gameActive) {
                timeRemaining--;
                updateTimer();
                
                if (timeRemaining <= 0) {
                    endGame();
                }
            }
        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();
    }
    
    /**
     * End the game
     */
    private void endGame() {
        gameActive = false;
        if (gameTimer != null) {
            gameTimer.stop();
        }
        showGameOverScreen();
    }
    
    /**
     * Restart the game
     */
    private void restartGame() {
        showGameScreen();
        startGame();
    }
    
    /**
     * Generate a new color-word question
     */
    private void generateNewQuestion() {
        Random random = new Random();
        
        // Select a random word
        currentWord = COLOR_NAMES[random.nextInt(COLOR_NAMES.length)];
        
        // Select a random color for the word (may not match the word)
        currentWordColor = COLORS[random.nextInt(COLORS.length)];
        wordLabel.setText(currentWord);
        wordLabel.setTextFill(currentWordColor);
        
        // Select the correct answer (the actual color of the word, not the word itself)
        correctAnswer = currentWordColor;
        
        // Create a list of possible answers
        List<Color> answerOptions = new ArrayList<>();
        answerOptions.add(correctAnswer);
        
        // Add random wrong answers
        while (answerOptions.size() < NUM_BUTTONS) {
            Color randomColor = COLORS[random.nextInt(COLORS.length)];
            if (!answerOptions.contains(randomColor)) {
                answerOptions.add(randomColor);
            }
        }
        
        // Shuffle the answers
        Collections.shuffle(answerOptions);
        
        // Update button colors and text
        for (int i = 0; i < buttonContainer.getChildren().size(); i++) {
            Button button = (Button) buttonContainer.getChildren().get(i);
            Color buttonColor = answerOptions.get(i);
            button.setText(getColorName(buttonColor));
            
            // Set button background color
            String colorHex = toHexString(buttonColor);
            button.setStyle(
                "-fx-background-color: " + colorHex + "; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5); " +
                "-fx-text-fill: white; " +
                "-fx-border-width: 3; " +
                "-fx-border-color: " + darkenColor(colorHex) + "; " +
                "-fx-border-radius: 10;"
            );
            
            // Store the color as user data for comparison
            button.setUserData(buttonColor);
        }
    }
    
    /**
     * Handle button click
     */
    private void handleButtonClick(Button button) {
        if (!gameActive) return;
        
        Color selectedColor = (Color) button.getUserData();
        
        if (selectedColor.equals(correctAnswer)) {
            // Correct answer
            score++;
            streak++;
            updateScore();
            updateStreak();
            
            // Visual feedback - green flash
            button.setStyle(button.getStyle().replace(
                "dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5)",
                "dropshadow(three-pass-box, rgba(0,255,0,0.8), 20, 0, 0, 10)"
            ));
            
            // Generate new question after a brief delay
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(300), e -> {
                generateNewQuestion();
            }));
            delay.play();
        } else {
            // Incorrect answer - reset streak and red flash
            streak = 0;
            updateStreak();
            
            button.setStyle(button.getStyle().replace(
                "dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5)",
                "dropshadow(three-pass-box, rgba(255,0,0,0.8), 20, 0, 0, 10)"
            ));
            
            // Reset style after brief delay
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(300), e -> {
                String baseStyle = button.getStyle();
                baseStyle = baseStyle.replace(
                    "dropshadow(three-pass-box, rgba(255,0,0,0.8), 20, 0, 0, 10)",
                    "dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5)"
                );
                button.setStyle(baseStyle);
            }));
            delay.play();
        }
    }
    
    /**
     * Update score display
     */
    private void updateScore() {
        scoreLabel.setText("Score: " + score);
    }
    
    /**
     * Update streak display
     */
    private void updateStreak() {
        streakLabel.setText("Streak: " + streak);
        if (streak >= 5) {
            streakLabel.setTextFill(Color.GOLD);
            streakLabel.setEffect(new Glow(1.0));
        } else if (streak >= 3) {
            streakLabel.setTextFill(Color.ORANGE);
            streakLabel.setEffect(new Glow(0.8));
        } else {
            streakLabel.setTextFill(Color.GOLD);
            streakLabel.setEffect(new Glow(0.6));
        }
    }
    
    /**
     * Update timer display
     */
    private void updateTimer() {
        timerLabel.setText("Time: " + timeRemaining);
        
        // Change color when time is running low
        if (timeRemaining <= 10) {
            timerLabel.setTextFill(Color.RED);
            timerLabel.setEffect(new Glow(1.0));
        } else {
            timerLabel.setTextFill(Color.WHITE);
            timerLabel.setEffect(new Glow(0.8));
        }
    }
    
    /**
     * Get color name from Color object
     */
    private String getColorName(Color color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
                return COLOR_NAMES[i];
            }
        }
        return "UNKNOWN";
    }
    
    /**
     * Convert Color to hex string
     */
    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255)
        );
    }
    
    /**
     * Darken a hex color for border
     */
    private String darkenColor(String hex) {
        // Simple darkening by reducing RGB values
        hex = hex.substring(1); // Remove #
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        
        r = Math.max(0, r - 40);
        g = Math.max(0, g - 40);
        b = Math.max(0, b - 40);
        
        return String.format("#%02X%02X%02X", r, g, b);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

