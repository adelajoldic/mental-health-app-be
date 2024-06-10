package com.example.mindspaceBE.models;

import lombok.Data;

@Data
public class AnxietyQuizDTO {
    private String email;
    private int totalQuestions;
    private int correctAnswers;
}