package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Brand findBrandById(int id);
}
