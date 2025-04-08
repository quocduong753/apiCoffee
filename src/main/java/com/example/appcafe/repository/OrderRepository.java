package com.example.appcafe.repository;

import com.example.appcafe.models.User;
import com.example.appcafe.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> getOrderByUserId(Long id);

}