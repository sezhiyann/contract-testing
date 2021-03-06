package org.example;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
        try {
            return productClient.addProduct(product);
        } catch (FeignException ex) {
            if (ex.status() == 400) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/products", produces = "application/json")
    public List<Product> getProducts() {
        return productClient.getProducts();
    }
}
