package ru.gontar.cyberstore.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gontar.cyberstore.service.ReportService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;

    @GetMapping("/products")
    public ResponseEntity<?> generateProductsReport() throws IOException {
        ByteArrayOutputStream outputStream = reportService.generateProductsCsvReport();
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
}
