package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity<Void> setUserInfo(@RequestBody UserRequestDto userRequestDto) {
        userService.setInfo(userRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam Long userChatId) {
        return userService.findUserByChatId(userChatId);
    }
}
