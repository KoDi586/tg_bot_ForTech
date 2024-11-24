package org.example.sevrice;

import lombok.RequiredArgsConstructor;
import org.example.dto.QuestionResponseDto;
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

    public UserRequestDto findUser() {
        return restClient.get()
                .uri(GET_USER_URL)
                .retrieve()
                .body(new ParameterizedTypeReference<UserRequestDto>() {
                });
    }


    public String getMessageForQuizCreate(Long userChatId) {
        UserRequestDto body = restClient.get()
                .uri(GET_USER_URL)
                .retrieve()
                .body(new ParameterizedTypeReference<UserRequestDto>() {
                });
        int questionsCount = body != null ? body.getQuestionsCount() : 10;
        String quizTopic = body != null ? body.getQuizTopic() : "any";
        String quizDif = body != null ? body.getQuizDif() : "any";

        String FIRST_MESSAGE_PART = "Викторина будет с такими параметрами:";
        String SECOND_MESSAGE_PART = "\nНа каждый вопрос будет дано 20 секунд времени. Каждый из " +
                "параметров можно изменить нажав на соответсвующую кнопку.";

        return FIRST_MESSAGE_PART +
                "\n количество вопросов:" + questionsCount +
                "\n тематика: " + quizTopic +
                "\n сложность: " + quizDif + SECOND_MESSAGE_PART;
    }

    public QuestionResponseDto startQuiz(Long userChatId) {
        UserRequestDto userRequestDto = findUser(userChatId);
        userRequestDto.setQuestions();
        userRequestDto.setQuestionNumberStopped(0);
        userRequestDto.setQuestionsCount(userRequestDto.getQuestions().size());
        return userRequestDto.getQuestions().get(0);
    }

    public boolean checkAnswer(Long userChatId, int answerNumber) {
        UserRequestDto userRequestDto = findUser(userChatId);
        boolean result = false;
        if (result) {
            userRequestDto.setTrueCount(userRequestDto.getTrueCount()+1);
        }
        userRequestDto.setQuestionNumberStopped(userRequestDto.getQuestionNumberStopped()+1);
        return result;
    }

    public QuestionResponseDto getContinueQuestion(Long userChatId) {
        UserRequestDto userRequestDto = findUser(userChatId);
        if (userRequestDto.getQuestionsCount() == userRequestDto.getQuestionNumberStopped()) {
            throw new RuntimeException();
        }
        return userRequestDto.getQuestions().get(userRequestDto.getQuestionNumberStopped());

    }
}
