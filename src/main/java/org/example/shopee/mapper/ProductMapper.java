package org.example.shopee.mapper;

import org.example.shopee.dto.ProductCreateDTO;
import org.example.shopee.dto.ProductEditDTO;
import org.example.shopee.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("productMapper")
public interface ProductMapper {
    Product toProductFromProductCreateDTO (ProductCreateDTO productCreateDTO);
    ProductEditDTO toProductEditDTOFromProduct (Product product);
    Product toProductFromProductEditDTO (ProductEditDTO productEditDTO);
}
