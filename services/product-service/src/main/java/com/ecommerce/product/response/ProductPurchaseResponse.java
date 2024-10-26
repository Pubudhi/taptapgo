package com.ecommerce.product.response;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        int productid,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
