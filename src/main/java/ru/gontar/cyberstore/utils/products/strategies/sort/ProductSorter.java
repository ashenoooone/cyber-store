package ru.gontar.cyberstore.utils.products.strategies.sort;

import lombok.AllArgsConstructor;
import ru.gontar.cyberstore.entity.Product;
import ru.gontar.cyberstore.interfaces.SortProductsStrategy;

import java.util.List;

@AllArgsConstructor
public class ProductSorter {
    private SortProductsStrategy strategy;

    public List<Product> sort(List<Product> products) {
        return strategy.sort(products);
    }
}
