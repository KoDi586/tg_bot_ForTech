package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user_model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long userId;

    private Long chatId;
    private Integer trueCount;
    private String quizTopic;
    private String quizDif;
    private Integer questionsCount;

//    private List<Integer> questionsId;
    private Integer questionNumberStopped;
    private Integer waitAnswerNumber;

}
