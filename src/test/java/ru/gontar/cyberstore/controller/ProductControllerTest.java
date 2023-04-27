package ru.gontar.cyberstore.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gontar.cyberstore.repositories.ProductsRepository;
import ru.gontar.cyberstore.service.ProductService;
import ru.gontar.cyberstore.utils.products.factory.ProductFactory;

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
//        when(service.saveProduct(new Product())).thenAnswer((Answer<Product>)
//                invocationOnMock -> invocationOnMock.getArgument(0, Product.class));
    }

    @Test
    void getAllProducts() {
//        when(service.getAllProducts()).thenReturn(this.getProducts());
//        List<Product> result = service.getAllProducts();
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(3, result.size());
//        Assertions.assertEquals("iphone 14", result.get(0).getProductName());
//        Assertions.assertEquals("Samsung", result.get(1).getProductName());
//        Assertions.assertEquals("huawei", result.get(2).getProductName());
    }


}