package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private Long chatId;
    private Integer doneQuizCount;
    private String quizTopic;
    private String quizDif;
    private Integer questionsCount;

}
