package org.example.sevrice;

import lombok.RequiredArgsConstructor;
import org.example.dto.QuestionDto;
import org.example.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
//@RequiredArgsConstructor
public class UserService {

    @Value("${get.user.url}")
    private String GET_USER_URL;
    @Autowired
    @Qualifier("restUserClient")
    private RestClient restClient;
    @Autowired
    private QuizService quizService;

//    public UserService(RestClient restClient, QuizService quizService) {
//        this.restClient = restClient;
//        this.quizService = quizService;
//    }

    public UserRequestDto findUser(Long userChatId) {
        return restClient.get()
                .uri(GET_USER_URL + "?userChatId=" + userChatId)
                .retrieve()
                .body(new ParameterizedTypeReference<UserRequestDto>() {
                });
    }

    public void saveUserInfo(UserRequestDto userRequestDto) {
        restClient.put()
                .uri(GET_USER_URL)
                .body(userRequestDto) // Используем BodyInserters
                .retrieve();
    }



    public String getMessageForQuizCreate(Long userChatId) {
        UserRequestDto body = restClient.get()
                .uri(GET_USER_URL + "?userChatId=" + userChatId)
                .retrieve()
                .body(new ParameterizedTypeReference<UserRequestDto>() {
                });
        int questionsCount = body != null ? body.getQuestionsCount() : 10;
        String quizTopic = body != null ? body.getQuizTopic() : "history";
        String quizDif = body != null ? body.getQuizDif() : "easy";

        String FIRST_MESSAGE_PART = "Викторина будет с такими параметрами:";
        String SECOND_MESSAGE_PART = "\nНа каждый вопрос будет дано 20 секунд времени. Каждый из " +
                "параметров можно изменить нажав на соответсвующую кнопку.";

        return FIRST_MESSAGE_PART +
                "\n количество вопросов:" + questionsCount +
                "\n тематика: " + quizTopic +
                "\n сложность: " + quizDif + SECOND_MESSAGE_PART;
    }

    public QuestionDto startQuiz(Long userChatId) {
        UserRequestDto userRequestDto = findUser(userChatId);
//        List<Integer> questionNumbers = quizService.createListQuestionNumbers();
//        userRequestDto.setQuestionsId(questionNumbers);
        userRequestDto.setQuestionNumberStopped(0);
        userRequestDto.setTrueCount(0);
//        userRequestDto.setQuestionsCount(10);
        saveUserInfo(userRequestDto);

        int paramsCount = 0;
        StringBuilder param = new StringBuilder();
        if (userRequestDto.getQuizDif() != null) {
            param.append("?difficulty=").append(userRequestDto.getQuizDif());
            paramsCount++;
        }
        if (userRequestDto.getQuizTopic() != null) {
            if (paramsCount == 0) {
                param.append("?category=").append(userRequestDto.getQuizTopic());
            } else {
                param.append("&category=").append(userRequestDto.getQuizTopic());
            }
        }

        QuestionDto questionDto = quizService.getQuestionDto(param.toString());
        List<String> variants = questionDto.getVariants();
        variants.add(questionDto.getAnswer());
        Collections.shuffle(variants);
        questionDto.setVariants(variants);

        for (int i = 0; i < variants.size(); i++) {
            if (variants.get(i).equals(questionDto.getAnswer())) {
                userRequestDto.setWaitAnswerNumber(i+1);
            }
        }

        saveUserInfo(userRequestDto);


        return questionDto;
    }

    public boolean checkAnswer(Long userChatId, Integer answerNumber) {
        UserRequestDto userRequestDto = findUser(userChatId);

        boolean result = userRequestDto.getWaitAnswerNumber().equals(answerNumber);
        if (result) {
            userRequestDto.setTrueCount(userRequestDto.getTrueCount()+1);
        }
        userRequestDto.setQuestionNumberStopped(userRequestDto.getQuestionNumberStopped()+1);
        saveUserInfo(userRequestDto);
        return result;
    }

    public QuestionDto getContinueQuestion(Long userChatId) {
        UserRequestDto userRequestDto = findUser(userChatId);
        if (userRequestDto.getQuestionsCount().equals(userRequestDto.getQuestionNumberStopped())) {

            throw new RuntimeException();
        }
        int paramsCount = 0;
        StringBuilder param = new StringBuilder();
        if (userRequestDto.getQuizDif() != null) {
            param.append("?difficulty=").append(userRequestDto.getQuizDif());
            paramsCount++;
        }
        if (userRequestDto.getQuizTopic() != null) {
            if (paramsCount == 0) {
                param.append("?category=").append(userRequestDto.getQuizTopic());
            } else {
                param.append("&category=").append(userRequestDto.getQuizTopic());
            }
        }

        QuestionDto questionDto = quizService.getQuestionDto(param.toString());
        List<String> variants = questionDto.getVariants();
        variants.add(questionDto.getAnswer());
        Collections.shuffle(variants);
        questionDto.setVariants(variants);

        for (int i = 0; i < variants.size(); i++) {
            if (variants.get(i).equals(questionDto.getAnswer())) {
                userRequestDto.setWaitAnswerNumber(i+1);
            }
        }

        saveUserInfo(userRequestDto);
        return questionDto;

    }
}
