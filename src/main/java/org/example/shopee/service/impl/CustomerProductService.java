package org.example.shopee.service.impl;

import org.example.shopee.dto.CustomerProductDTO;
import org.example.shopee.model.CustomerProduct;
import org.example.shopee.repository.ICustomerProductRepository;
import org.example.shopee.service.ICustomerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerProductService implements ICustomerProductService {
    @Autowired
    private ICustomerProductRepository customerProductRepository;

    @Override
    public void save(CustomerProduct customerProduct) {
        customerProductRepository.save(customerProduct);
    }

    @Override
    public List<CustomerProduct> findAll() {
        return customerProductRepository.findAll();
    }

    @Override
    public Boolean existProductIdAndCutomerId(CustomerProductDTO customerProductDTO) {
        boolean k = (customerProductDTO.getProductId() == null || customerProductDTO.getProductId().isEmpty());

        if (k) {
            return customerProductRepository.existProductIdAndCutomerId(null);
        }
        Integer s = Integer.valueOf(customerProductDTO.getProductId());
        return customerProductRepository.existProductIdAndCutomerId(s);
    }

    @Modifying
    @Transactional
    @Override
    public void duplicateProduct(CustomerProductDTO customerProductDTO) {
        customerProductRepository.duplicateProduct(customerProductDTO.getQuantity() == null || customerProductDTO.getQuantity().isEmpty() ? null : Integer.parseInt(customerProductDTO.getQuantity()),
                customerProductDTO.getProductId() == null || customerProductDTO.getProductId().isEmpty() ? null : Integer.parseInt(customerProductDTO.getProductId()));
    }

    @Modifying
    @Transactional
    @Override
    public void delete(CustomerProductDTO customerProductDTO) {
        customerProductRepository.delete(Integer.parseInt(customerProductDTO.getProductId()));
    }
}
