package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.QuestionRequestDto;
import org.example.dto.QuestionResponseDto;
import org.example.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getWithLimit(@RequestParam(required = false) Long limit,
                                          @RequestParam(required = false) String category,
                                          @RequestParam(required = false) String difficulty) {
        List<QuestionResponseDto> questions = questionService.getByParams(limit, category, difficulty);
        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<?> checkRequestDto(@RequestBody QuestionRequestDto questionRequestDto) {
        return null;
    }
}
