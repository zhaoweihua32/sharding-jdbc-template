package org.example.integration.entity;


import lombok.Data;

@Data
public class OrderItem {

    private long userId;
    private long orderItemId;
    private long orderId;

    private long price;




}
