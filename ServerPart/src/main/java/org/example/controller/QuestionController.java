package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.QuestionRequestDto;
import org.example.dto.QuestionResponseDto;
import org.example.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getWithLimit(/*@RequestParam(required = false) Long limit,*/
                                          @RequestParam(required = false) String category,
                                          @RequestParam(required = false) String difficulty) {
        QuestionResponseDto question = questionService.getByParams(1L, category, difficulty).getFirst();
        return ResponseEntity.ok(question);
    }

}
