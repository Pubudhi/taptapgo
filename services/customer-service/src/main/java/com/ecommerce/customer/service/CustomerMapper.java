package com.ecommerce.customer.service;

import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.response.CustomerResponse;
import com.ecommerce.customer.validation.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    private final CustomerService customerService;

    public CustomerMapper(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer toCustomer(@Valid CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstName(request.fname())
                .lastName(request.lname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
