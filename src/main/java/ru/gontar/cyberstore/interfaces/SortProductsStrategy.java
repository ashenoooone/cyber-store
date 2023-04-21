package ru.gontar.cyberstore.interfaces;

import ru.gontar.cyberstore.entity.Product;

import java.util.List;

public interface SortProductsStrategy {
    List<Product> sort(List<Product> products);
}
