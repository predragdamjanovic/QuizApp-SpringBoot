package com.pdamjanovic.quizapp.dto;

import java.time.LocalDateTime;

public class QuizResponse {
    private Long id;
    private String title;
    private String description;
    private String categoryName;
    private String createdByUsername;
    private LocalDateTime createdAt;

    public QuizResponse(Long id, String title, String description, String categoryName, String createdByUsername, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.createdByUsername = createdByUsername;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public void setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
