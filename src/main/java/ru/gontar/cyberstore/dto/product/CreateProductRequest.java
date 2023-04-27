package ru.gontar.cyberstore.dto.product;

import lombok.Data;

@Data
public class CreateProductRequest {
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private int quantity = 1;
    private int categoryId;
    private int brandId;
}
