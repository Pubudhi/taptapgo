package com.ecommerce.order.orderline;

public record OrderLineResponse(
        Integer id,
        double quantity
) { }