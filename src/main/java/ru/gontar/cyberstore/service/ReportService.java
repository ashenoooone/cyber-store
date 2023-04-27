package ru.gontar.cyberstore.service;

import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.gontar.cyberstore.entity.*;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private ProductService productService;
    private OrderService ordersService;
    private BrandService brandService;
    private CategoriesService categoriesService;
//    fixme для реализации фасада сделать чтобы вызывались все заказы и все например продукты, тогда будет фасад


    public ByteArrayOutputStream generateProductReportByCategoryAndBrand(List<Category> categories, List<Brand> brands) throws IOException {
        if (categories.size() == 0) throw new RuntimeException("Категорий нет");
        if (brands.size() == 0) throw new RuntimeException("Брендов нет");
        int sum;
        int count;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.RFC4180_LINE_END);
        for (Category category :
                categories) {
            csvWriter.writeNext(new String[]{"#", "#", "#", category.getName(), "#", "#", "#"});

            for (Brand brand :
                    brands) {
                List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
                sum = 0;
                count = 0;
                for (Product product :
                        products) {
                    List<OrderItems> orderItems = product.getOrderItems();
                    for (OrderItems orderItem :
                            orderItems) {
                        sum += orderItem.getPrice();
                        count += orderItem.getQuantity();
                    }
                }
                csvWriter.writeNext(new String[]{"#", brand.getName(),
                        "income:", sum + "$",
                        "sold:", count + " pieces", "#"});
            }
            csvWriter.writeNext(new String[]{"#", "#", "#", "#", "#", "#", "#"});
        }


        csvWriter.close();
        return outputStream;
    }

    public void generateProductOrderItemsJsonReport() throws IOException {
        JSONObject object = new JSONObject();
        object.put("type", "Отчет по заказам продуктов");
        object.put("date", new Date());

        List<Product> products = productService.getAllProducts();
        for (Product p :
                products) {
            JSONArray pArray = new JSONArray();
            for (OrderItems oi :
                    p.getOrderItems()) {
                JSONObject jsonOI = new JSONObject();
                JSONObject customerIO = new JSONObject();
                customerIO.put("username", oi.getOrder().getUser().getUsername());
                customerIO.put("email", oi.getOrder().getUser().getEmail());
                customerIO.put("lastLogin", oi.getOrder().getUser().getLastLoginDate());
                jsonOI.put("date", oi.getOrder().getOrderDate());
                jsonOI.put("customer", customerIO);
                jsonOI.put("quantity", oi.getQuantity());
                jsonOI.put("price", oi.getPrice());
                pArray.put(jsonOI);
            }
            object.put("orderItems", pArray);
        }

        FileWriter file = new FileWriter("C:\\projects\\cyber-store\\src\\main\\java\\ru\\gontar\\cyberstore\\controller\\order_items.json");
        try {
            file.write(object.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public ByteArrayOutputStream generateOrdersCsvReport() throws IOException {
        List<Order> products = ordersService.getAllOrdersSortedByDate();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.RFC4180_LINE_END);
        csvWriter.writeNext(new String[]{"id", "username", "date", "method_of_payment", "status"});
        for (Order order : products) {
            csvWriter.writeNext(new String[]{
                    String.valueOf(order.getId()),
                    String.valueOf(order.getUser().getUsername()),
                    String.valueOf(order.getOrderDate()),
                    String.valueOf(order.getMethodOfPayment()),
                    order.getOrderStatus()
            });
        }

        csvWriter.close();
        return outputStream;
    }
}
