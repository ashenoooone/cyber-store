package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.Category;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(int id);

}
