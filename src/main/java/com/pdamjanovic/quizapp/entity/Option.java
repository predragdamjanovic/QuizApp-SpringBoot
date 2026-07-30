package com.pdamjanovic.quizapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @Column(name = "option_label", length = 1, nullable = false)
    private char optionLabel;
    @Column(name = "option_text", length = 300, nullable = false)
    private String optionText;
    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;

    public Option(){}

    public Option(Question question, char optionLabel, String optionText, Boolean isCorrect) {
        this.question = question;
        this.optionLabel = optionLabel;
        this.optionText = optionText;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public char getOptionLabel() {
        return optionLabel;
    }

    public void setOptionLabel(char optionLabel) {
        this.optionLabel = optionLabel;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
