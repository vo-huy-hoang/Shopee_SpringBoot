package org.example.shopee.service;

import org.example.shopee.dto.CustomerProductDTO;
import org.example.shopee.model.CustomerProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerProductService {
    void save(CustomerProduct customerProduct);
    List<CustomerProduct> findAll();
    Boolean existProductIdAndCutomerId(CustomerProductDTO customerProductDTO);
    void duplicateProduct(CustomerProductDTO customerProductDTO);
    void delete(CustomerProductDTO customerProductDTO);
}
