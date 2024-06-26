package org.example.shopee.service;

import org.example.shopee.model.Customer;

public interface ICustomerService {
    Customer findById(Integer id);
}
