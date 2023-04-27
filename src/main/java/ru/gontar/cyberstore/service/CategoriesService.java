package ru.gontar.cyberstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.Category;
import ru.gontar.cyberstore.repositories.CategoriesRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService {
    private CategoriesRepository categoriesRepository;


    public Category getCategoryById(int id) {
        return categoriesRepository.findCategoryById(id);
    }

    public List<Category> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Category getCategoryByName(String name) {
        return categoriesRepository.findCategoryByName(name);
    }
}
