package org.example.service;

import org.example.dto.QuestionResponseDto;

import java.util.List;

public interface QuestionService {


    List<QuestionResponseDto> getByLimit(Long limit);
}
