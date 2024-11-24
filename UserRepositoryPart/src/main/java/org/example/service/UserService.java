package org.example.service;

import lombok.RequiredArgsConstructor;
//import org.example.dto.QuestionDto;
import org.example.dto.UserRequestDto;
import org.example.dto.UserResponseDto;
//import org.example.model.QuestionModel;
import org.example.model.UserModel;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final QuestionService questionService;


    public void setInfo(UserRequestDto userRequestDto) {
        UserModel userModel = userRepository.findByChatId(userRequestDto.getChatId()).orElse(new UserModel());
        if (userModel.getUserId() == null) {
            userModel = new UserModel();
            userModel.setChatId(userRequestDto.getChatId());
        }

        if (userModel.getQuestionsCount() == null) {
            userModel.setQuestionsCount(10);
        }
        userModel.setQuizDif(userRequestDto.getQuizDif());
        userModel.setQuestionsCount(userRequestDto.getQuestionsCount());
        userModel.setQuizTopic(userRequestDto.getQuizTopic());

//        userModel.setQuestionsId(userRequestDto.getQuestionsId());
        userModel.setQuestionNumberStopped(userRequestDto.getQuestionNumberStopped());
        userModel.setTrueCount(userRequestDto.getTrueCount());
        userRepository.save(userModel);
    }

    public UserResponseDto findUserByChatId(Long userChatId) {

        UserModel userModel = userRepository.findByChatId(userChatId).orElse(new UserModel());
        if (userModel.getChatId() == null) {
            return new UserResponseDto();
        }
        return new UserResponseDto(
                userModel.getChatId(),
                userModel.getTrueCount(),
                userModel.getQuizTopic(),
                userModel.getQuizDif(),
                userModel.getQuestionsCount(),

//                userModel.getQuestionsId(),
                userModel.getQuestionNumberStopped()
        );


    }

//    public QuestionDto convertQuestionToDto(QuestionModel questionModel) {
//        return new QuestionDto(
//                questionModel.getId(),
//                questionModel.getQuestionText(),
//                questionModel.getVariants(),
//                questionModel.getAnswer()
//        );
//    }
//
//    public QuestionModel convertDtoToModel(QuestionDto questionDto, UserModel userModel) {
//        return questionService.save(new QuestionModel(
//                questionDto.getId(),
//                questionDto.getQuestionText(),
//                questionDto.getVariants(),
//                questionDto.getAnswer(),
//                userModel
//        ));
//    }
//
//    public List<QuestionModel> collectionAfterConvert(List<QuestionDto> questionDtoList, UserModel userModel) {
//        List<QuestionModel> result = new ArrayList<>();
//        for (QuestionDto questionDto : questionDtoList) {
//            result.add(convertDtoToModel(questionDto, userModel));
//        }
//        return result;
//    }
//
//    public List<QuestionDto> collectionDtosAfterConvert(List<QuestionModel> questionModelList) {
//        List<QuestionDto> result = new ArrayList<>();
//        for (QuestionModel questionModel : questionModelList) {
//            result.add(convertQuestionToDto(questionModel));
//        }
//        return result;
//    }
}
