package org.example.integration.service;

import org.example.integration.dao.OrderItemDao;
import org.example.integration.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    public long addOne(OrderItem item){
        this.orderItemDao.addOne(item);
        return item.getOrderItemId();
    }



}
