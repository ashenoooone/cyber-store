package ru.gontar.cyberstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.Brand;
import ru.gontar.cyberstore.repositories.BrandRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {
    private BrandRepository brandRepository;

    public Brand getBrandById(int id) {
        return brandRepository.findBrandById(id);
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandByName(String name) {
        return brandRepository.findBrandByName(name);
    }
}
