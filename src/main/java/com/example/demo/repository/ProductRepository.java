package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new ArrayList<>();
        products.add(new Product("czekolada", 3.5, Category.GROCERY));
        products.add(new Product("sok", 4.5, Category.GROCERY));
        products.add(new Product("paluszki", 1.3, Category.GROCERY));
        products.add(new Product("czajnik", 65.0, Category.APPLIANCES));
        products.add(new Product("toster", 38.0, Category.APPLIANCES));
        products.add(new Product("młynek", 27.0, Category.APPLIANCES));
        products.add(new Product("serwetki", 2.5, Category.OTHER));
        products.add(new Product("świeczka", 4.3, Category.OTHER));
        products.add(new Product("łyżki", 2.5, Category.OTHER));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> showAll() {
        return new ArrayList<>(products);
    }

    public List<Product> showCategory(Category category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }
}
