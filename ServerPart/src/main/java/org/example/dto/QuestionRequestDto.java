package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDto {

    private String category;
    private String id;
    private String correctAnswer;
    private List<String> incorrectAnswers;
    private QuestionTextDto question;
    private List<String> tags;
    private String type;
    private String difficulty;
    private List<String> regions;
    private Boolean isNiche;

}
