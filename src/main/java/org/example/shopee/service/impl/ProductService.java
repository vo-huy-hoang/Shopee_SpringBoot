package org.example.shopee.service.impl;

import org.example.shopee.dto.ProductSearchDTO;
import org.example.shopee.model.Product;
import org.example.shopee.repository.IProductRepository;
import org.example.shopee.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public List<Product> search(ProductSearchDTO productSearchDTO) {
        Double fromPrice = null;
        Double toPrice = null;
        if (productSearchDTO.getPrice() != null) {
            if (productSearchDTO.getPrice().equals("d10")) {
                toPrice = 10000000.0;
            } else if (productSearchDTO.getPrice().equals("10-20")) {
                fromPrice = 10000000.0;
                toPrice = 20000000.0;
            } else if (productSearchDTO.getPrice().equals("20-30")) {
                fromPrice = 20000000.0;
                toPrice = 30000000.0;
            } else {
                fromPrice = 30000000.0;
            }
        }
        return productRepository.search(productSearchDTO.getName(), fromPrice, toPrice);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

//    @Override
//    public void delete(Product product) {
//        productRepository.delete(product);
//    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
