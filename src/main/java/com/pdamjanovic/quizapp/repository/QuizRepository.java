package com.pdamjanovic.quizapp.repository;

import com.pdamjanovic.quizapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
