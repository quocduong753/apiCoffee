package com.example.appcafe.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
        private Long drinkId;
    private Integer quantity;
}