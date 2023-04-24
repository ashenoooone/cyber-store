package ru.gontar.cyberstore.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.repositories.ProductsRepository;
import ru.gontar.cyberstore.service.ProductService;
import ru.gontar.cyberstore.utils.products.factory.ProductFactory;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private ProductFactory productFactory;

    @Test
    void createProduct() {
        when(service.saveProduct(new Product())).thenAnswer((Answer<Product>)
                invocationOnMock -> invocationOnMock.getArgument(0, Product.class));
    }

    @Test
    void getAllProducts() {
        when(service.getAllProducts()).thenReturn(this.getProducts());
        List<Product> result = service.getAllProducts();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertEquals("iphone 14", result.get(0).getProductName());
        Assertions.assertEquals("Samsung", result.get(1).getProductName());
        Assertions.assertEquals("huawei", result.get(2).getProductName());
    }

    private List<Product> getProducts() {
        Product p1 = new Product(
                1,
                "iphone 14",
                "new iphone",
                1,
                10000,
                11,
                null
        );
        Product p2 = new Product(
                2,
                "Samsung",
                "extra new samsung",
                1,
                1000,
                20,
                null
        );
        Product p3 = new Product(
                3,
                "huawei",
                "not really new huawei",
                2,
                500,
                2,
                null
        );
        return List.of(p1, p2, p3);
    }

    private List<Product> getProductsSortedByPrice() {
        Product p1 = new Product(
                1,
                "iphone 14",
                "new iphone",
                1,
                10000,
                11,
                null
        );
        Product p2 = new Product(
                2,
                "Samsung",
                "extra new samsung",
                1,
                1000,
                20,
                null
        );
        Product p3 = new Product(
                3,
                "huawei",
                "not really new huawei",
                2,
                500,
                2,
                null
        );
        return List.of(p3, p2, p1);
    }

    private List<Product> getProductsSortedByQuantity() {
        Product p1 = new Product(
                1,
                "iphone 14",
                "new iphone",
                1,
                10000,
                11,
                null
        );
        Product p2 = new Product(
                2,
                "Samsung",
                "extra new samsung",
                1,
                1000,
                20,
                null
        );
        Product p3 = new Product(
                3,
                "huawei",
                "not really new huawei",
                2,
                500,
                2,
                null
        );
        return List.of(p3, p1, p2);
    }

    private List<Product> getProductsSortedByCategoryId() {
        Product p1 = new Product(
                1,
                "iphone 14",
                "new iphone",
                1,
                10000,
                11,
                null
        );
        Product p2 = new Product(
                2,
                "Samsung",
                "extra new samsung",
                1,
                1000,
                20,
                null
        );
        Product p3 = new Product(
                3,
                "huawei",
                "not really new huawei",
                2,
                500,
                2,
                null
        );
        Product p4 = new Product(
                3,
                "huawei",
                "not really new huawei",
                2,
                500,
                2,
                null
        );
        Product p5 = new Product(
                3,
                "huawei",
                "not really new huawei",
                5,
                500,
                2,
                null
        );
        return List.of(p1, p2, p3, p4, p5);
    }

}