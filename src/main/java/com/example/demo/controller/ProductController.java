package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ResponseBody
    @GetMapping ("/lista-cała")
    public String showAllProducts() {
        List<Product> productList = productRepository.showAll();

        double totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }

        return productList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br>")) + "<br><br>" + "Lączna cena produktów: " + totalPrice;
    }

    @ResponseBody
    @GetMapping ("/lista")
    public String showCategory(@RequestParam (value="kategoria") Category category) {
        List<Product> productList = productRepository.showCategory(category);

        double totalPrice = 0.0;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }

        return productList.stream()
                .map(Product::toString)
                .collect(Collectors.joining("<br>")) + "<br><br>" + "Lączna cena produktów: " + totalPrice;
    }

    @ResponseBody
    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam Category category) {
        if (name != null && name.equals("") && price >=0 && category != null) {
            return "Niepoprawne dane";
        } else {
            Product product = new Product(name, price, category);
            productRepository.addProduct(product);
            return "Produkt dodany";
        }
    }
}