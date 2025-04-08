package com.example.appcafe.repository;

import com.example.appcafe.models.Drink;
import com.example.appcafe.models.OrderItem;
import com.example.appcafe.models.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    boolean existsByDrink(Drink drink);
}