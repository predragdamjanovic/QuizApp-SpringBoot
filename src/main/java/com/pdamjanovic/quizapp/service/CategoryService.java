package com.pdamjanovic.quizapp.service;

import com.pdamjanovic.quizapp.dto.CategoryRequest;
import com.pdamjanovic.quizapp.dto.CategoryResponse;
import com.pdamjanovic.quizapp.entity.Category;
import com.pdamjanovic.quizapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> new CategoryResponse
                (category.getId(), category.getName(),
                        category.getDescription())
        ).collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id).map(category -> new CategoryResponse(category.getId(), category.getName(), category.getDescription())).orElseThrow(() -> new IllegalArgumentException("Kategorija sa id: " + id + " ne postoji"));

    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Kategorija sa id: " + id + " ne postoji");
        }
        categoryRepository.deleteById(id);
    }

    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Kategorija sa nazivom' " + request.getName() + " 'postoji");
        }
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category saved = categoryRepository.save(category);

        return new CategoryResponse(saved.getId(), saved.getName(), saved.getDescription());
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("\"Kategorija sa id: \"+id+\" ne postoji\""));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updated = categoryRepository.save(category);
        return new CategoryResponse(updated.getId(), updated.getName(), updated.getDescription());

    }

    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Kategorija sa nazivom '" + name + "' ne postoji"));

        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
}
