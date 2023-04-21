package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
