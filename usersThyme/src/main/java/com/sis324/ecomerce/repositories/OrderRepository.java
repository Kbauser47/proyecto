package com.sis324.ecomerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sis324.ecomerce.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
