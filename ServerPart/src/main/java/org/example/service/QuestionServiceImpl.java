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
        StringBuilder param = new StringBuilder();
        param.append("?limit=").append(limit.toString());
        param.append("&region=RU");
        List<QuestionRequestDto> body = restClient.get().uri(TRIVIA_API + param).retrieve().body(List.class);
        log.info(body != null ? body.toString() : "null");
        return List.of();
    }
}
