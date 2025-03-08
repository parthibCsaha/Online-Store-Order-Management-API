package org.example.onlinestore.repo;

import org.example.onlinestore.service.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
