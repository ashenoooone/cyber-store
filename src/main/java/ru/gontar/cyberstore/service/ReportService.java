package ru.gontar.cyberstore.service;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.Order;
import ru.gontar.cyberstore.entity.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    //    запись продуктов в файл csv
    private ProductService productService;
    private OrdersService ordersService;

    public ByteArrayOutputStream generateProductsCsvReport() throws IOException {
        List<Product> products = productService.getAllProducts();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.RFC4180_LINE_END);
        csvWriter.writeNext(new String[]{"categoryId", "imageUrl", "price", "productDescription", "productName", "quantity"});
        for (Product product : products) {
            csvWriter.writeNext(new String[]{
                    String.valueOf(product.getCategoryId()),
                    product.getImageUrl(),
                    String.valueOf(product.getPrice()),
                    product.getProductDescription(),
                    product.getProductName(),
                    String.valueOf(product.getQuantity())
            });
        }

        csvWriter.close();
        return outputStream;
    }

    public ByteArrayOutputStream generateOrdersCsvReport() throws IOException {
        List<Order> products = ordersService.getAllOrders();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.RFC4180_LINE_END);
        csvWriter.writeNext(new String[]{"id", "user_id", "date", "method_of_payment"});
        for (Order order : products) {
            csvWriter.writeNext(new String[]{
                    String.valueOf(order.getId()),
                    String.valueOf(order.getUser_id()),
                    String.valueOf(order.getOrder_date()),
                    String.valueOf(order.getMethod_of_payment()),
                    order.getOrder_status()
            });
        }

        csvWriter.close();
        return outputStream;
    }
}
