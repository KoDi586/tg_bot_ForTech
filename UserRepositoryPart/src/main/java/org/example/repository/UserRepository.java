package org.example.repository;

import org.example.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {


    Optional<UserModel> findByChatId(Long chatId);
}
