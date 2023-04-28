package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gontar.cyberstore.entity.Brand;
import ru.gontar.cyberstore.entity.Category;
import ru.gontar.cyberstore.entity.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByCategoryAndBrand(Category category, Brand brand);

    Product findProductsById(int id);
}
