package ru.gontar.cyberstore.utils.products.factory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.repositories.ProductsRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class ProductFactoryTest {
    @InjectMocks
    private ProductFactory productFactory;
    private AutoCloseable closeable;
    @Mock
    private ProductsRepository productsRepository;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void createProduct() {
        //        given
        Product product = new Product();
        product.setProductName("Iphone");
        product.setPrice(1000);
        product.setQuantity(1);
        //        when
        productFactory.createProduct(product);
        //        then
        verify(productsRepository, times(1)).save(product);
    }

    @Test()
    void createProductThrowsIllegalArgumentException() throws IllegalAccessError {
        //        given
        Product product = new Product();
        //        when
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productFactory.createProduct(product);
        });
        //        then
        Assertions.assertEquals("Неверные значения полей", exception.getMessage());
    }
}