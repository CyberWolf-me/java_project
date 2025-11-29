package com.colorhunt.view;

import com.colorhunt.model.ColorData;
import com.colorhunt.model.GameState;
import com.colorhunt.model.Question;
import com.colorhunt.util.ColorUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

/**
 * Main game view - handles UI display
 */
public class GameView {
    private VBox mainContainer;
    private Label wordLabel;
    private Label timerLabel;
    private Label scoreLabel;
    private Label streakLabel;
    private Label highScoreLabel;
    private Label instructionLabel;
    private VBox buttonContainer;
    private List<Button> colorButtons;
    
    private static final int NUM_BUTTONS = 4;
    
    public GameView() {
        initializeUI();
    }
    
    /**
     * Initialize the main game UI components
     */
    private void initializeUI() {
        // Main container
        mainContainer = new VBox(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(40));
        
        // Top section - Timer and Score
        HBox topBar = new HBox(30);
        topBar.setAlignment(Pos.CENTER);
        
        // Timer label
        timerLabel = new Label("Time: 30");
        timerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setEffect(new Glow(0.8));
        
        // Score label
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setEffect(new Glow(0.8));
        
        // Streak label
        streakLabel = new Label("Streak: 0");
        streakLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        streakLabel.setTextFill(Color.GOLD);
        streakLabel.setEffect(new Glow(0.6));
        
        // High score label
        highScoreLabel = new Label("High Score: 0");
        highScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        highScoreLabel.setTextFill(Color.LIGHTGRAY);
        
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
        colorButtons = new java.util.ArrayList<>();
        for (int i = 0; i < NUM_BUTTONS; i++) {
            Button colorButton = createColorButton();
            colorButtons.add(colorButton);
            buttonContainer.getChildren().add(colorButton);
        }
        
        // Assemble main container
        mainContainer.getChildren().addAll(topBar, instructionLabel, wordLabel, buttonContainer);
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
        
        return button;
    }
    
    /**
     * Update the display with a new question
     */
    public void updateQuestion(Question question) {
        wordLabel.setText(question.getWord());
        wordLabel.setTextFill(question.getWordColor());
        
        List<Color> answerOptions = question.getAnswerOptions();
        for (int i = 0; i < colorButtons.size() && i < answerOptions.size(); i++) {
            Button button = colorButtons.get(i);
            Color buttonColor = answerOptions.get(i);
            button.setText(ColorData.getColorName(buttonColor));
            
            // Set button background color
            String colorHex = ColorUtils.toHexString(buttonColor);
            button.setStyle(
                "-fx-background-color: " + colorHex + "; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5); " +
                "-fx-text-fill: white; " +
                "-fx-border-width: 3; " +
                "-fx-border-color: " + ColorUtils.darkenColor(colorHex) + "; " +
                "-fx-border-radius: 10;"
            );
            
            // Store the color as user data for comparison
            button.setUserData(buttonColor);
        }
    }
    
    /**
     * Update game state display
     */
    public void updateGameState(GameState gameState) {
        scoreLabel.setText("Score: " + gameState.getScore());
        timerLabel.setText("Time: " + gameState.getTimeRemaining());
        streakLabel.setText("Streak: " + gameState.getStreak());
        highScoreLabel.setText("High Score: " + gameState.getHighScore());
        
        // Update streak color
        if (gameState.getStreak() >= 5) {
            streakLabel.setTextFill(Color.GOLD);
            streakLabel.setEffect(new Glow(1.0));
        } else if (gameState.getStreak() >= 3) {
            streakLabel.setTextFill(Color.ORANGE);
            streakLabel.setEffect(new Glow(0.8));
        } else {
            streakLabel.setTextFill(Color.GOLD);
            streakLabel.setEffect(new Glow(0.6));
        }
        
        // Update timer color when time is running low
        if (gameState.getTimeRemaining() <= 10) {
            timerLabel.setTextFill(Color.RED);
            timerLabel.setEffect(new Glow(1.0));
        } else {
            timerLabel.setTextFill(Color.WHITE);
            timerLabel.setEffect(new Glow(0.8));
        }
    }
    
    /**
     * Flash button with specified color
     */
    public void flashButton(Button button, String flashColor) {
        String originalStyle = button.getStyle();
        button.setStyle(originalStyle.replace(
            "dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5)",
            "dropshadow(three-pass-box, " + flashColor + ", 20, 0, 0, 10)"
        ));
    }
    
    public void resetButtonStyle(Button button, String originalStyle) {
        button.setStyle(originalStyle);
    }
    
    // Getters
    public VBox getMainContainer() {
        return mainContainer;
    }
    
    public List<Button> getColorButtons() {
        return colorButtons;
    }
}

