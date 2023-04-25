package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gontar.cyberstore.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
