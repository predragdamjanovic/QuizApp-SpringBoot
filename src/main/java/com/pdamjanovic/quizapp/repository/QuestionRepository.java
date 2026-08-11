package com.pdamjanovic.quizapp.repository;

import com.pdamjanovic.quizapp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    /*
    findBy - standardni prefiks za upit
    QuizId - filtrila po quiz.id (Posto Question ima many to many vezu sa Quiz)
    OrderByQuestionOrderAsc - sortira rezultate rastuci (1, 2, 3, 4...)
    Pageable - parametar koji nosi informaciju koju stranicu i koliko elemenata po stranici

    Page<Question> objekat koji dobijaš nazad sadrži ne samo pitanje, već i korisne
    metapodatke: getTotalPages(), getTotalElements(), hasNext(), hasPrevious(), getNumber()
    (trenutna stranica) — sve što ti treba da napraviš "Sledeće pitanje"/ "Prethodno pitanje"
    navigaciju na frontend-u.
     */
    Page<Question> findByQuizIdOrderByQuestionOrderAsc(Long quizId, Pageable pageable);

    /*
    Metoda koja vraca listu pitanja za odredjeni kviz
     */
    List<Question> findByQuizIdOrderByQuestionOrderAsc(Long quizId);

    // Vraca broj pitanja u kvizu
    long countByQuizId(Long quizId);

}
