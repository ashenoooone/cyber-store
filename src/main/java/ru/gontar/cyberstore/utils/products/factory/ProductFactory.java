package ru.gontar.cyberstore.utils.products.factory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.repositories.ProductsRepository;


/**
 * Класс ProductFactory реализует паттерн Фабрика (Factory)
 * Он предназначен для создания экземпляров класса Product и сохранения их в базе данных
 * Паттерн Фабрика используется здесь для создания объектов Product без необходимости вызывать
 * конструктор напрямую.
 * Вместо этого мы создаем фабрику (ProductFactory) и вызываем ее методы для создания новых объектов
 * Product.
 * При этом в фабрике могут быть скрыты сложности создания объекта.
 * необходимых ресурсов и т.д. Таким образом, паттерн Фабрика облегчает создание объектов,
 * улучшает читаемость и
 * поддерживаемость кода и позволяет скрыть реализацию создания объекта от клиента.
 */
@AllArgsConstructor
@Component("productFactory")
public class ProductFactory {
    private ProductsRepository repository; // репозиторий для сохранения в базе данных


    public Product createProduct(int productId, String productName, String productDescription,
                                 int categoryId, float price, int quantityInStock, String imageUrl) {
        Product product = new Product(productId, productName, productDescription, categoryId, price, quantityInStock, imageUrl);

        // Сохраняем объект продукта в базе данных
        repository.save(product);

        return product;
    }

    public Product createProduct(Product product) {
        repository.save(product);
        return product;
    }
}