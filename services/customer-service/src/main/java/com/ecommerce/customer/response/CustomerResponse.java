package com.ecommerce.customer.response;

import com.ecommerce.customer.entity.Address;

public record CustomerResponse(

        String id,
        String fname,
        String lname,
        String email,
        Address address) {
}
