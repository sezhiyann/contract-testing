package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductAPI {
    private static final List<Product> products;
    private static int counter = 4;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "Cricket Bat", 650.0));
        products.add(new Product(2, "Tennis Bat", 890.0));
        products.add(new Product(3, "Football", 530.0));
    }

    @GetMapping(path = "/", produces = "text/html")
    public String index() {
        return "Application has started OK!!!";
    }

    @PostMapping(path = "/internal/products")
    public Product addProduct(@RequestBody Product product) {
        if (product != null && !StringUtils.isEmpty(product.getName()) && product.getPrice() > 0) {
            product.setId(counter++);
            products.add(product);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/internal/products")
    public List<Product> getProducts() {
        return products;
    }
}
