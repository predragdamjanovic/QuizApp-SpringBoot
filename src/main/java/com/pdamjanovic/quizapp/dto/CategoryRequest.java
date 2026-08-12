package com.pdamjanovic.quizapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {

    @NotBlank(message = "Naziv kategorije je obavezan")
    @Size(max = 100, message = "Naziv ne sme biti duzi od 100 karaktera")
    private String name;

    @Size(max = 300, message = "Opis ne sme biti duzi od 300 karaktera")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
