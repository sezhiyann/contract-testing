package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="product", url="${product.url}")
public interface ProductClient {
    @GetMapping(value = "/internal/products", consumes="application/json", produces = "application/json")
    List<Product> getProducts();

    @PostMapping(path = "/internal/products" , produces = "application/json", consumes = "application/json")
    Product addProduct(Product product);
}
