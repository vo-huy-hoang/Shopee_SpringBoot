package org.example.shopee.repository;

import org.example.shopee.model.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

public interface ICustomerProductRepository extends JpaRepository<CustomerProduct, Integer> {
    @Query(value = "select count(*) from customer_product as cp where (:productId is null or cp.product_id = :productId)", nativeQuery = true)
    BigInteger countProductByProductId(@Param("productId") Integer productId);

    default Boolean existProductIdAndCutomerId(Integer productId) {
        BigInteger count = countProductByProductId(productId);
        return count != null && count.compareTo(BigInteger.ZERO) > 0;
    }
    @Modifying
    @Transactional
    @Query(value = "update customer_product as cp set cp.quantity = cp.quantity + :quantity where (:productId is null or cp.product_id = :productId)", nativeQuery = true)
    void duplicateProduct(@Param("quantity") Integer quantity, @Param("productId") Integer productId);

    @Modifying
    @Transactional
    @Query(value = "delete from customer_product as cp where (:productId is null or cp.product_id = :productId)", nativeQuery = true)
    void delete(@Param("productId") Integer productId);
}
