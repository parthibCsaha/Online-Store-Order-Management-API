package org.example.onlinestore.repo;

import org.example.onlinestore.service.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
