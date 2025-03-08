package org.example.onlinestore.controller;

import org.example.onlinestore.repo.CustomerRepository;
import org.example.onlinestore.repo.OrderRepository;
import org.example.onlinestore.repo.ProductRepository;
import org.example.onlinestore.service.Customer;
import org.example.onlinestore.service.Order;
import org.example.onlinestore.service.OrderItem;
import org.example.onlinestore.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order.getCustomer() == null || order.getCustomer().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Customer> customer = customerRepository.findById(order.getCustomer().getId());
        if (!customer.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        order.setCustomer(customer.get());
        if (order.getOrderItems() != null) {
            for (OrderItem orderItem : order.getOrderItems()) {
                if (orderItem.getProduct() == null || orderItem.getProduct().getId() == null) {
                    return ResponseEntity.badRequest().build();
                }
                Optional<Product> product = productRepository.findById(orderItem.getProduct().getId());
                if (!product.isPresent()) {
                    return ResponseEntity.badRequest().build();
                }
                orderItem.setProduct(product.get());
                orderItem.setOrder(order);
            }
        }
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderRepository
                .findById(id)
                .map(order -> {
                    order.getOrderItems().clear();
                    if (updatedOrder.getOrderItems() != null) {
                        for (OrderItem orderItem : updatedOrder.getOrderItems()) {
                            if (orderItem.getProduct() == null || orderItem.getProduct().getId() == null) {
                                return ResponseEntity.badRequest().build();
                            }
                            Optional<Product> product = productRepository.findById(orderItem.getProduct().getId());
                            if (!product.isPresent()) {
                                return ResponseEntity.badRequest().build();
                            }
                            orderItem.setProduct(product.get());
                            orderItem.setOrder(order);
                            order.getOrderItems().add(orderItem);
                        }
                    }
                    return ResponseEntity.ok(orderRepository.save(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return orderRepository
                .findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
