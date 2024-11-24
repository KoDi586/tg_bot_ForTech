package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequestDto;
import org.example.model.UserModel;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void setInfo(UserRequestDto userRequestDto) {
        UserModel userModel = userRepository.findByChatId(userRequestDto.getChatId()).orElse(new UserModel());

        userModel.setQuizDif(userRequestDto.get);
    }
}
