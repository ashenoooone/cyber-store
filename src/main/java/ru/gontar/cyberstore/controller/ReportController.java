package ru.gontar.cyberstore.controller;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gontar.cyberstore.entity.Brand;
import ru.gontar.cyberstore.entity.Category;
import ru.gontar.cyberstore.service.BrandService;
import ru.gontar.cyberstore.service.CategoriesService;
import ru.gontar.cyberstore.service.ReportService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {
//    fixme нужны не сырые данные ( более на бизнес упор )
//     ( напр. соклько денег на айфонах и на самуснгах ) реализовать напр через квери
//     ( брать стоимости, колчиество и даты )

    private ReportService reportService;
    private CategoriesService categoriesService;
    private BrandService brandService;

    @GetMapping("/products")
//    fixme отрефакторить код
    public ResponseEntity<?> generateProductsReport(@RequestParam(name = "category", required = false) String category,
                                                    @RequestParam(name = "brand", required = false) String brand) throws IOException {
        ByteArrayOutputStream outputStream;
        if (category != null && brand == null) {
            Category cat = categoriesService.getCategoryByName(category);
            outputStream = reportService
                    .generateProductReportByCategoryAndBrand(List.of(cat), brandService.getAllBrands());
        } else if (category == null && brand != null) {
            Brand brn = brandService.getBrandByName(brand);
            outputStream = reportService
                    .generateProductReportByCategoryAndBrand(categoriesService.getAllCategories(), List.of(brn));
        } else if (category != null && brand != null) {
            Category cat = categoriesService.getCategoryByName(category);
            Brand brn = brandService.getBrandByName(brand);
            outputStream = reportService
                    .generateProductReportByCategoryAndBrand(List.of(cat), List.of(brn));
        } else {
            outputStream = reportService
                    .generateProductReportByCategoryAndBrand(categoriesService.getAllCategories(), brandService.getAllBrands());
        }
        byte[] reportBytes = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "products.csv");

        return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> generateOrdersReport() throws IOException {
        ByteArrayOutputStream outputStream = reportService.generateOrdersCsvReport();
        byte[] reportBytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "orders.csv");

        return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/orders_items", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> generateOrdersItemsReport() throws IOException {
        reportService.generateProductOrderItemsJsonReport();
        String jsonString = new String(Files.readAllBytes(Paths.get("C:\\projects\\cyber-store\\src\\main\\java\\ru\\gontar\\cyberstore\\controller\\order_items.json")));
        JSONObject jsonObject = new JSONObject(jsonString);
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
