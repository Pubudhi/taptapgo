package com.ecommerce.customer.validation;

import com.ecommerce.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "Customer first name is required")
                              String fname,

                              @NotNull(message = "Customer last name is required")
                              String lname,

                              @NotNull(message = "Customer email is required")
                              @Email(message = "Customer email is not valid")
                              String email,

                              Address address) {
}
