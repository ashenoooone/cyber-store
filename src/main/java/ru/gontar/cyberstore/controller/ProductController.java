package ru.gontar.cyberstore.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gontar.cyberstore.dto.product.CreateProductRequest;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.service.ProductService;
import ru.gontar.cyberstore.utils.products.strategies.sort.SortByCategoryIdProductsStrategy;
import ru.gontar.cyberstore.utils.products.strategies.sort.SortByPriceProductsStrategy;
import ru.gontar.cyberstore.utils.products.strategies.sort.SortByQuantityProductsStrategy;

/**
 * Этот класс является реализацией паттерна "Фасад".
 * Он предоставляет удобный интерфейс для работы с продуктами в системе.
 * Класс ProductController объединяет в себе работу с сервисом ProductService и сортировкой списка продуктов
 * по заданному фильтру. Клиенты могут работать с продуктами через ProductController,
 * не зная о том, как именно
 * работает сервис и как реализована сортировка. Таким образом, ProductController предоставляет
 * удобный интерфейс
 * для работы с продуктами, скрывая сложность и детали реализации от клиентов.
 * Таким образом, класс ProductController является примером реализации паттерна "Фасад",
 * предоставляющего удобный интерфейс для работы с продуктами в системе.
 */
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService service;

    @PostMapping("/")
    @Transactional
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product savedProduct = service.saveProduct(request);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    /**
     * Обработчик GET-запросов на эндпоинт "/"
     * Метод получает список продуктов с возможностью сортировки по заданному фильтру.
     *
     * @param filter строка с названием поля, по которому необходимо провести сортировку
     * @return ResponseEntity со списком продуктов, отсортированным по указанному фильтру
     */
    @GetMapping("/")
    @Transactional
    public ResponseEntity<?> getProduct(@RequestParam(value = "filter", required = false) String filter) {
        if (filter == null) {
            // Если фильтр не указан, возвращаем список продуктов без сортировки
            return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
        } else if (filter.equals("price")) {
            return new ResponseEntity<>(service
                    .getAllProductsSortedByStrategy(new SortByPriceProductsStrategy()),
                    HttpStatus.OK);
        } else if (filter.equals("quantity")) {
            return new ResponseEntity<>(service
                    .getAllProductsSortedByStrategy(new SortByQuantityProductsStrategy()),
                    HttpStatus.OK);
        } else if (filter.equals("category")) {
            return new ResponseEntity<>(service
                    .getAllProductsSortedByStrategy(new SortByCategoryIdProductsStrategy()),
                    HttpStatus.OK);
        } else {
            // Если указанный фильтр не соответствует доступным вариантам, возвращаем ошибку
            return ResponseEntity.badRequest().build();
        }
    }
}
