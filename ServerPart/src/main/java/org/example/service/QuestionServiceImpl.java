package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RestClientConfiguration;
import org.example.dto.QuestionRequestDto;
import org.example.dto.QuestionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Value("${trivia.api}")
    private String TRIVIA_API;
    private final RestClient restClient;

    @Override
    public List<QuestionResponseDto> getByLimit(Long limit) {

        String newUrl = "https://the-trivia-api.com/v2/questions?region=RU&difficulties=medium";
        List body = restClient.get().uri(newUrl).retrieve().body(List.class);

        log.info(body != null ? body.toString() : "null");
//        String param;
//        param = "?limit=2";
//        try {
//            QuestionRequestDto questionDto = restClient.get().uri(TRIVIA_API + param).retrieve().body(QuestionRequestDto.class);
//            log.info(questionDto != null ? questionDto.toString() : "getting question is wrong!");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return List.of();
    }
}
