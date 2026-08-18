package com.pdamjanovic.quizapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class QuizRequest {
    @NotBlank(message = "Naslov kviza je obavezan")
    @Size(max = 150, message = "Naslov ne sme biti duži od 150 karaktera")
    private String title;

    @Size(max = 500, message = "Opis ne sme biti duži od 500 karaktera")
    private String description;

    @NotNull(message = "Kategorija je obavezna")
    private Long categoryId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
