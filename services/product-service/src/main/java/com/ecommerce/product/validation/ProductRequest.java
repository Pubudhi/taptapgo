package com.ecommerce.product.validation;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
         int id,

         @NotNull(message = "Product name id required.")
         String name,

         @NotNull(message = "Product description is required.")
         String description,

         @Positive(message = "Available quantity should be positive.")
         double available_qty,

         @Positive(message = "Price should be positive.")
         BigDecimal price,

         @NotNull(message = "Product category is required.")
         int categoryId
) {
}
