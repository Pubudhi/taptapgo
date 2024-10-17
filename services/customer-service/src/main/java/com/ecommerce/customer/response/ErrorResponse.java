package com.ecommerce.customer.response;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
