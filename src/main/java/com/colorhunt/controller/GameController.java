package com.colorhunt.controller;

import com.colorhunt.model.GameState;
import com.colorhunt.model.Question;
import com.colorhunt.view.GameOverView;
import com.colorhunt.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Game controller - handles game logic
 */
public class GameController {
    private static final int GAME_DURATION = 30;
    private static final int NUM_BUTTONS = 4;
    
    private GameState gameState;
    private GameView gameView;
    private GameOverView gameOverView;
    private StackPane rootPane;
    private Timeline gameTimer;
    private Question currentQuestion;
    
    public GameController(StackPane rootPane) {
        this.rootPane = rootPane;
        this.gameState = new GameState(GAME_DURATION);
        this.gameView = new GameView();
        this.gameOverView = new GameOverView();
        
        // Set up button click handlers
        for (Button button : gameView.getColorButtons()) {
            button.setOnAction(e -> handleButtonClick(button));
        }
        
        // Set up restart button
        gameOverView.getRestartButton().setOnAction(e -> restartGame());
    }
    
    /**
     * Start a new game
     */
    public void startGame() {
        gameState.reset(GAME_DURATION);
        gameView.updateGameState(gameState);
        generateNewQuestion();
        showGameScreen();
        
        // Start countdown timer
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (gameState.isGameActive()) {
                gameState.decrementTime();
                gameView.updateGameState(gameState);
                
                if (gameState.getTimeRemaining() <= 0) {
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
    public void endGame() {
        gameState.setGameActive(false);
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameState.updateHighScore();
        gameOverView.updateGameOver(gameState);
        showGameOverScreen();
    }
    
    /**
     * Restart the game
     */
    public void restartGame() {
        showGameScreen();
        startGame();
    }
    
    /**
     * Generate a new color-word question
     */
    private void generateNewQuestion() {
        currentQuestion = Question.generateRandom(NUM_BUTTONS);
        gameView.updateQuestion(currentQuestion);
    }
    
    /**
     * Handle button click
     */
    private void handleButtonClick(Button button) {
        if (!gameState.isGameActive()) return;
        
        Color selectedColor = (Color) button.getUserData();
        String originalStyle = button.getStyle();
        
        if (currentQuestion.isCorrect(selectedColor)) {
            // Correct answer
            gameState.incrementScore();
            gameState.incrementStreak();
            gameView.updateGameState(gameState);
            gameView.flashButton(button, "rgba(0,255,0,0.8)");
            
            // Generate new question after a brief delay
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(300), e -> {
                gameView.resetButtonStyle(button, originalStyle);
                generateNewQuestion();
            }));
            delay.play();
        } else {
            // Incorrect answer - reset streak
            gameState.resetStreak();
            gameView.updateGameState(gameState);
            gameView.flashButton(button, "rgba(255,0,0,0.8)");
            
            // Reset style after brief delay
            Timeline delay = new Timeline(new KeyFrame(Duration.millis(300), e -> {
                gameView.resetButtonStyle(button, originalStyle);
            }));
            delay.play();
        }
    }
    
    /**
     * Show the main game screen
     */
    private void showGameScreen() {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(gameView.getMainContainer());
        gameOverView.getGameOverContainer().setVisible(false);
    }
    
    /**
     * Show the game over screen
     */
    private void showGameOverScreen() {
        rootPane.getChildren().clear();
        rootPane.getChildren().add(gameOverView.getGameOverContainer());
        gameOverView.getGameOverContainer().setVisible(true);
    }
}

