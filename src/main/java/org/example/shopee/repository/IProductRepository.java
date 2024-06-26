package org.example.shopee.repository;

import org.example.shopee.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "from Product as p where (:name = '' or :name is null or p.name like concat('%', :name, '%'))" +
                                        "and (:toPrice is null or (p.price <= :toPrice))" +
                                        "and (:fromPrice is null or (p.price >= :fromPrice))")
    List<Product> search(@Param("name") String name, @Param("fromPrice") Double fromPrice, @Param("toPrice") Double toPrice);
}
