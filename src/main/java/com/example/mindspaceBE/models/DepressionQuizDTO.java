package com.example.mindspaceBE.models;

import lombok.Data;

@Data
public class DepressionQuizDTO {
    private String email;
    private int totalQuestions;
    private int correctAnswers;
}
