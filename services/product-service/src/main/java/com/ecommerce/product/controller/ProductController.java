package com.ecommerce.product.controller;

import com.ecommerce.product.response.ProductPurchaseResponse;
import com.ecommerce.product.response.ProductResponse;
import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.validation.ProductPurchaseRequest;
import com.ecommerce.product.validation.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequest) {
        return ResponseEntity.ok(productService.purchaseProduct(productPurchaseRequest));
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("product_id") int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        //TODO - Pagination
        return ResponseEntity.ok(productService.findAll());
    }


}
