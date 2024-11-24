package org.example.sevrice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.QuestionDto;
import org.example.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizService {

    private final RestClient restClient;
    @Value("${get.quiz.url}")
    private String URI;


    public QuestionDto getQuestionDto(String params) {
        return restClient.get()
                .uri(URI+params)
                .retrieve()
                .body(new ParameterizedTypeReference<QuestionDto>() {
                });
    }
}
