package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.RestClientConfiguration;
import org.example.dto.QuestionRequestDto;
import org.example.dto.QuestionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Value("${trivia.api}")
    private String TRIVIA_API;
    private final RestClient restClient;

    /**
     * получение, обработка и предоставление quiz по полученным параметрам
     * @return список из вопросов по предоставленным параметрам
     */
    @Override
    public List<QuestionResponseDto> getByParams(Long limit, String category, String difficulty) {
        //формирование параметров запроса
        int paramsCount = 0;
        StringBuilder param = new StringBuilder();
        if (limit != null) {
            param.append("?limit=").append(limit);
            paramsCount++;
        }
        if (category != null) {
            if (paramsCount == 0) {
                param.append("?categories=").append(category);
            } else {
                param.append("&categories=").append(category);
            }
        }
        if (difficulty != null) {
            if (paramsCount == 0) {
                param.append("?difficulties=").append(difficulty);
            } else {
                param.append("&difficulties=").append(difficulty);
            }
        }

        String uri = TRIVIA_API + param;
        log.info("uri for find quiz: {}", uri);
        List<QuestionRequestDto> body = restClient.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<List<QuestionRequestDto>>() {});
//        List<QuestionRequestDto> body = restClient.get().uri(TRIVIA_API + param).retrieve().body(List.class);
        log.info(body != null ? body.toString() : "null");
        return collectionListAfterConvert(body != null ? body : List.of());
    }

    /**
     * конвертация полученного дто в отправляемое пользователю.
     * убирается все лишнее
     * @param requestDto -  полученное с сайта дто
     * @return - дто без лишнего
     */
    private QuestionResponseDto convertToCleanQuiz(QuestionRequestDto requestDto) {
        return new QuestionResponseDto(
                requestDto.getId(),
                requestDto.getQuestion().getText(),
                requestDto.getIncorrectAnswers(),
                requestDto.getCorrectAnswer()
        );
    }

    /**
     * метод использующий конвертатор собирает список полученных
     * в список отправляеемых
     * @param questionRequestDtos - полученный список
     * @return - отправляемый список
     */
    private List<QuestionResponseDto> collectionListAfterConvert(List<QuestionRequestDto> questionRequestDtos) {
        List<QuestionResponseDto> result = new ArrayList<>();
        for (QuestionRequestDto questionRequestDto : questionRequestDtos) {
            result.add(convertToCleanQuiz(questionRequestDto));
        }
        return result;
    }
}
