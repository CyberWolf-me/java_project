package com.colorhunt;

import com.colorhunt.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Color Hunt Game - Main application entry point
 * A time-based game testing color-word matching skills
 * Inspired by the Stroop effect
 */
public class ColorHuntGame extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Hunt Game");
        
        // Create root pane
        StackPane rootPane = new StackPane();
        rootPane.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3c72, #2a5298);");
        
        // Create game controller
        GameController gameController = new GameController(rootPane);
        
        // Create scene
        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        // Start the game
        gameController.startGame();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
