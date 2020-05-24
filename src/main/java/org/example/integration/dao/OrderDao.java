package org.example.integration.dao;

import org.apache.ibatis.annotations.Param;
import org.example.integration.entity.Order;

import java.util.List;

public interface OrderDao {

    long addOne(Order order);

    Order selectOne(@Param("orderId") long orderId, @Param("userId") long userId);

    List<Order> getOrderByUserId(@Param("id")long id);

}
