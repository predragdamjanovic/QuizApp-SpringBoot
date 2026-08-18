package com.pdamjanovic.quizapp.service;

import com.pdamjanovic.quizapp.dto.QuizRequest;
import com.pdamjanovic.quizapp.dto.QuizResponse;
import com.pdamjanovic.quizapp.entity.Category;
import com.pdamjanovic.quizapp.entity.Quiz;
import com.pdamjanovic.quizapp.entity.User;
import com.pdamjanovic.quizapp.repository.CategoryRepository;
import com.pdamjanovic.quizapp.repository.QuizRepository;
import com.pdamjanovic.quizapp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<QuizResponse> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kviz sa id " + id + " ne postoji"));

        return mapToResponse(quiz);
    }

    @Transactional
    public QuizResponse createQuiz(QuizRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Kategorija sa id " + request.getCategoryId() + " ne postoji"));

        User currentUser = getCurrentUser();

        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(category);
        quiz.setCreatedBy(currentUser);
        quiz.setCreatedAt(LocalDateTime.now());

        Quiz saved = quizRepository.save(quiz);

        return mapToResponse(saved);
    }

    @Transactional
    public QuizResponse updateQuiz(Long id, QuizRequest request) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kviz sa id " + id + " ne postoji"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Kategorija sa id " + request.getCategoryId() + " ne postoji"));

        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setCategory(category);

        Quiz updated = quizRepository.save(quiz);

        return mapToResponse(updated);
    }

    public void deleteQuiz(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new IllegalArgumentException("Kviz sa id " + id + " ne postoji");
        }

        quizRepository.deleteById(id);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Ulogovani korisnik nije pronađen u bazi"));
    }

    private QuizResponse mapToResponse(Quiz quiz) {
        String categoryName = quiz.getCategory() != null ? quiz.getCategory().getName() : null;
        String createdByUsername = quiz.getCreatedBy() != null ? quiz.getCreatedBy().getUsername() : null;

        return new QuizResponse(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                categoryName,
                createdByUsername,
                quiz.getCreatedAt()
        );
    }
}