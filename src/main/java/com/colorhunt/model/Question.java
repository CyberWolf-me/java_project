package com.colorhunt.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Question model - represents a color-word question
 */
public class Question {
    private String word;
    private Color wordColor;
    private Color correctAnswer;
    private List<Color> answerOptions;
    
    public Question(String word, Color wordColor, Color correctAnswer, List<Color> answerOptions) {
        this.word = word;
        this.wordColor = wordColor;
        this.correctAnswer = correctAnswer;
        this.answerOptions = answerOptions;
    }
    
    /**
     * Generate a random question
     */
    public static Question generateRandom(int numButtons) {
        Random random = new Random();
        
        // Select a random word
        String word = ColorData.COLOR_NAMES[random.nextInt(ColorData.COLOR_NAMES.length)];
        
        // Select a random color for the word (may not match the word)
        Color wordColor = ColorData.COLORS[random.nextInt(ColorData.COLORS.length)];
        
        // The correct answer is the actual color of the word
        Color correctAnswer = wordColor;
        
        // Create a list of possible answers
        List<Color> answerOptions = new ArrayList<>();
        answerOptions.add(correctAnswer);
        
        // Add random wrong answers
        while (answerOptions.size() < numButtons) {
            Color randomColor = ColorData.COLORS[random.nextInt(ColorData.COLORS.length)];
            if (!answerOptions.contains(randomColor)) {
                answerOptions.add(randomColor);
            }
        }
        
        // Shuffle the answers
        Collections.shuffle(answerOptions);
        
        return new Question(word, wordColor, correctAnswer, answerOptions);
    }
    
    // Getters
    public String getWord() {
        return word;
    }
    
    public Color getWordColor() {
        return wordColor;
    }
    
    public Color getCorrectAnswer() {
        return correctAnswer;
    }
    
    public List<Color> getAnswerOptions() {
        return answerOptions;
    }
    
    public boolean isCorrect(Color selectedColor) {
        return correctAnswer.equals(selectedColor);
    }
}

