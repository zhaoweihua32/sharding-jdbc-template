package org.example.integration.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

  private long orderId;
  private long userId;

  private long price;

}
