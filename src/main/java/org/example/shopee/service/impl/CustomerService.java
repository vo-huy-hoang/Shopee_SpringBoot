package org.example.shopee.service.impl;

import org.example.shopee.model.Customer;
import org.example.shopee.repository.ICustomerRepository;
import org.example.shopee.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
