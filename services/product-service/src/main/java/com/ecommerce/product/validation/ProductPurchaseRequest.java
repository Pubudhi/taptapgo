package com.ecommerce.product.validation;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is required.")
        int productId,

        @NotNull(message = "Quantity is required.")
        double qty
) {
}
