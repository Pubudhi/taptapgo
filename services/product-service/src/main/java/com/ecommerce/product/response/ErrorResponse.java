package com.ecommerce.product.response;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
