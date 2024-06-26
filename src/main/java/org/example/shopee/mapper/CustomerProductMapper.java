package org.example.shopee.mapper;

import org.example.shopee.dto.CustomerProductDTO;
import org.example.shopee.model.CustomerProduct;
import org.example.shopee.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("customerProductMapper")
public interface CustomerProductMapper {
    CustomerProduct toCustomerProductfromCustomerProductDTO (CustomerProductDTO customerProductDTO);
}
