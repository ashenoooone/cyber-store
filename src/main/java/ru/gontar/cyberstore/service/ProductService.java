package ru.gontar.cyberstore.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.interfaces.SortProductsStrategy;
import ru.gontar.cyberstore.repositories.ProductsRepository;
import ru.gontar.cyberstore.utils.products.factory.ProductFactory;
import ru.gontar.cyberstore.utils.products.strategies.sort.ProductSorter;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductFactory factory;

    private ProductsRepository repository;

    public Product saveProduct(Product product) {
        return factory.createProduct(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public List<Product> getAllProductsSortedByStrategy(SortProductsStrategy strategy) {
        ProductSorter sorter = new ProductSorter(strategy);
        return sorter.sort(this.getAllProducts());
    }

}