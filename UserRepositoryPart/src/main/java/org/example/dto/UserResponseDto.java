package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long chatId;
    private Integer trueCount;
    private String quizTopic;
    private String quizDif;
    private Integer questionsCount;
//    private List<Integer> questionsId;
    private Integer questionNumberStopped;
    private Integer waitAnswerNumber;

}
