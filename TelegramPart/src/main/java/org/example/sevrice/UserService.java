package org.example.sevrice;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${get.userInfo.url}")
    private String GET_USER_URL;
    private final RestClient restClient;
    private static final String FIRST_MESSAGE_PART = "Викторина будет с такими параметрами:";
    private static final String SECOND_MESSAGE_PART = "\nНа каждый " +
            "вопрос будет дано 20 секунд времени. Каждый из параметров можно изменить нажав на \n" +
            "соответсвующую кнопку.";

    public String getMessageForQuizCreate(Long userChatId) {
        UserRequestDto body = restClient.get()
                .uri(GET_USER_URL)
                .retrieve()
                .body(new ParameterizedTypeReference<UserRequestDto>() {
                });
        int questionsCount = body != null ? body.getQuestionsCount() : 10;
        String quizTopic = body != null ? body.getQuizTopic() : "any";
        String quizDif = body != null ? body.getQuizDif() : "any";

        return FIRST_MESSAGE_PART +
                "\n количество вопросов:" + questionsCount +
                "\n тематика: " + quizTopic +
                "\n сложность: " + quizDif + SECOND_MESSAGE_PART;
    }

}
