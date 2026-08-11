package com.pdamjanovic.quizapp.repository;

import com.pdamjanovic.quizapp.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
}
