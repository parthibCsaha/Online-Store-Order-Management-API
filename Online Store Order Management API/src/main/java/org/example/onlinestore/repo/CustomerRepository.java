package org.example.onlinestore.repo;

import org.example.onlinestore.service.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
