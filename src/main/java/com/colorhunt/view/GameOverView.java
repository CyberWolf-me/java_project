package com.colorhunt.view;

import com.colorhunt.model.GameState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Game over screen view
 */
public class GameOverView {
    private VBox gameOverContainer;
    private Label gameOverLabel;
    private Label finalScoreLabel;
    private Button restartButton;
    
    public GameOverView() {
        initializeUI();
    }
    
    /**
     * Initialize the game over screen
     */
    private void initializeUI() {
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
        
        gameOverContainer.getChildren().addAll(gameOverLabel, finalScoreLabel, restartButton);
    }
    
    /**
     * Update the game over screen with final score
     */
    public void updateGameOver(GameState gameState) {
        if (gameState.isNewHighScore()) {
            finalScoreLabel.setText("Final Score: " + gameState.getScore() + " (NEW HIGH SCORE!)");
            finalScoreLabel.setTextFill(Color.GOLD);
        } else {
            finalScoreLabel.setText("Final Score: " + gameState.getScore());
            finalScoreLabel.setTextFill(Color.WHITE);
        }
    }
    
    // Getters
    public VBox getGameOverContainer() {
        return gameOverContainer;
    }
    
    public Button getRestartButton() {
        return restartButton;
    }
}

