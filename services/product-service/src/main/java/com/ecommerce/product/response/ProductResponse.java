package com.ecommerce.product.response;

import java.math.BigDecimal;

public record ProductResponse(
         int id,
         String name,
         String description,
         double available_qty,
         BigDecimal price,
         int categoryId,
         String categoryName,
         String categoryDescription
) {
}
