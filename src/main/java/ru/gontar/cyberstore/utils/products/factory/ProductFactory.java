package ru.gontar.cyberstore.utils.products.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gontar.cyberstore.dto.product.CreateProductRequest;
import ru.gontar.cyberstore.entity.Brand;
import ru.gontar.cyberstore.entity.Category;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.repositories.ProductsRepository;
import ru.gontar.cyberstore.service.BrandService;
import ru.gontar.cyberstore.service.CategoriesService;


/**
 * Класс ProductFactory реализует паттерн Фабрика (Factory)
 * Он предназначен для создания экземпляров класса Product и сохранения их в базе данных
 * Паттерн Фабрика используется здесь для создания объектов Product без необходимости вызывать
 * конструктор напрямую.
 * Вместо этого мы создаем фабрику (ProductFactory) и вызываем ее методы для создания новых объектов
 * Product.
 */
@AllArgsConstructor
@Component
public class ProductFactory {
    private ProductsRepository repository; // репозиторий для сохранения в базе данных
    private CategoriesService categoriesService;
    private BrandService brandService;

    public Product createProduct(CreateProductRequest request) {
        System.out.println(request.getBrandId());
        System.out.println(request.getCategoryId());
        Category category = categoriesService.getCategoryById(request.getCategoryId());
        Brand brand = brandService.getBrandById(request.getBrandId());
        Product product = new Product(
                0,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getQuantity(),
                request.getImageUrl(),
                category,
                brand
        );
        return repository.save(product);
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }
}