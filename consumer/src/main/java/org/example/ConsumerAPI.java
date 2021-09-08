package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ConsumerAPI {
    private final ProductClient productClient;

    @Autowired
    public ConsumerAPI(ProductClient client) {
        productClient = client;
    }

    @GetMapping(path = "/", produces = "text/html")
    public String index() {
        return "Application has started OK!!!";
    }

    @PostMapping(path = "/products", produces = "application/json", consumes = "application/json")
    public Product addProduct(@RequestBody Product product) {
        return productClient.addProduct(product);
    }

    @GetMapping(path = "/products", produces = "application/json")
    public List<Product> getProducts() {
        return productClient.getProducts();
    }
}
