package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;
    private Integer trueCount;
    private String quizTopic;
    private String quizDif;
    private Integer questionsCount;
//    @OneToMany(mappedBy = "UserModel")
    private List<QuestionModel> questions;
    private Integer questionNumberStopped;

}
