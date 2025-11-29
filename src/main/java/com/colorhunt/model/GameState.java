package com.colorhunt.model;

/**
 * Game state data model
 */
public class GameState {
    private int score = 0;
    private int timeRemaining;
    private int streak = 0;
    private int highScore = 0;
    private boolean gameActive = false;
    
    public GameState(int gameDuration) {
        this.timeRemaining = gameDuration;
    }
    
    // Getters and Setters
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void incrementScore() {
        this.score++;
    }
    
    public int getTimeRemaining() {
        return timeRemaining;
    }
    
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
    
    public void decrementTime() {
        this.timeRemaining--;
    }
    
    public int getStreak() {
        return streak;
    }
    
    public void setStreak(int streak) {
        this.streak = streak;
    }
    
    public void incrementStreak() {
        this.streak++;
    }
    
    public void resetStreak() {
        this.streak = 0;
    }
    
    public int getHighScore() {
        return highScore;
    }
    
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    
    public boolean isGameActive() {
        return gameActive;
    }
    
    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }
    
    public void reset(int gameDuration) {
        this.score = 0;
        this.streak = 0;
        this.timeRemaining = gameDuration;
        this.gameActive = true;
    }
    
    public boolean isNewHighScore() {
        return score > highScore;
    }
    
    public void updateHighScore() {
        if (isNewHighScore()) {
            this.highScore = this.score;
        }
    }
}

