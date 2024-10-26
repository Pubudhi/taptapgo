package com.ecommerce.product.service;

import com.ecommerce.product.exception.ProductPurchaseException;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.response.ProductPurchaseResponse;
import com.ecommerce.product.response.ProductResponse;
import com.ecommerce.product.validation.ProductPurchaseRequest;
import com.ecommerce.product.validation.ProductRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct(@Valid ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequest) {
        /*
        * 1. do we have all the products
        * 2.do we have sufficient qty
        * 3.all goo start purchase
        * */

        //fetch all product ids in the purchase request
        var productIds = productPurchaseRequest
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        //fetch all product from the db
        var storedProduct = productRepository.findAllByIdInOrderById(productIds);

        //checks db records against purchase req product ids
        if (storedProduct.size() != productPurchaseRequest.size()) {
            throw new ProductPurchaseException("One or more products does not exixts");
        }

        var storedRequest = productPurchaseRequest
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProduct.size(); i++) {
            var product = storedProduct.get(i);
            var productRequest = storedRequest.get(i);

            if(product.getAvailable_qty() < productRequest.qty()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with id: "
                        + productRequest.productId());
            }

            //calculate new available qty and update the db
            var newAvailableQty =  product.getAvailable_qty() - productRequest.qty();
            product.setAvailable_qty(newAvailableQty);
            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.qty()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(int productId) {

        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow (() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
