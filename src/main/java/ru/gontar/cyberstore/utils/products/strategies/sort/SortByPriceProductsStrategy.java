package ru.gontar.cyberstore.utils.products.strategies.sort;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.interfaces.SortProductsStrategy;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Component
public class SortByPriceProductsStrategy implements SortProductsStrategy {

    @Override
    public List<Product> sort(List<Product> products) {
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }
}
