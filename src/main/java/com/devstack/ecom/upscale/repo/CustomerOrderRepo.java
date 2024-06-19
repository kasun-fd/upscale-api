package com.devstack.ecom.upscale.repo;

import com.devstack.ecom.upscale.entity.CustomerOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrders,String> {
    @Query(value = "SELECT * FROM customer_order WHERE customer_id LIKE %?1% ORDER BY property_id DESC", nativeQuery = true)
    public Page<CustomerOrders> findAllWithSearchText(String searchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM customer_order WHERE customer_id LIKE %?1%", nativeQuery = true)
    public long countAllWithSearchText(String searchText);
}
