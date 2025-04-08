package com.example.appcafe.services;
import com.example.appcafe.dto.OrderItemRequest;
import com.example.appcafe.models.*;
import com.example.appcafe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DrinkRepository drinkRepository;
    private final UserRepository userRepository;

    public Order createOrder(Long userId, List<OrderItemRequest> items, String shippingAddress) {
        Optional<User> user = userRepository.findById(userId);

        Order order = Order.builder()
                .createdAt(LocalDateTime.now())
                .status("Đang xử lý")
                .user(user.get())
                .ShippingAddress(shippingAddress)
                .totalAmount(0.0)
                .build();

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderItemRequest itemRequest : items) {
            Optional<Drink> drink = drinkRepository.findById(itemRequest.getDrinkId());
            if (drink.isEmpty()) continue;

            double itemTotal = drink.get().getPrice() * itemRequest.getQuantity();

            OrderItemId itemId = new OrderItemId();
            itemId.setOrderId(order.getId());
            itemId.setDrinkId(drink.get().getId());

            OrderItem orderItem = OrderItem.builder()
                    .id(itemId)
                    .order(order)
                    .drink(drink.get())
                    .quantity(itemRequest.getQuantity())
                    .totalPrice(itemTotal)
                    .build();

            orderItems.add(orderItem);
            totalAmount += itemTotal;
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        for (OrderItem item : orderItems) {
            item.getId().setOrderId(savedOrder.getId());
        }

        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }


    public Order updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

}