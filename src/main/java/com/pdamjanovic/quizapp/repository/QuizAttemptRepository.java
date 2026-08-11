package com.pdamjanovic.quizapp.repository;

import com.pdamjanovic.quizapp.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
}
