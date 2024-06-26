package org.example.shopee.service;

import org.example.shopee.dto.ProductSearchDTO;
import org.example.shopee.model.Product;

import java.util.List;

public interface IProductService {
    void save(Product product);
    Product findById(Integer id);

    List<Product> search(ProductSearchDTO productSearchDTO);
    void deleteById(Integer id);
//    void delete(Product product);
    List<Product> findAll();

}
