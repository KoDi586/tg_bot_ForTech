package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String id;
    private String questionText;
    private List<String> variants;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "user_model_id")
    private UserModel userModel;
}
