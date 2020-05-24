package org.example.integration;

import lombok.extern.slf4j.Slf4j;
import org.example.integration.dao.OrderDao;
import org.example.integration.dao.OrderItemDao;
import org.example.integration.entity.Order;
import org.example.integration.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    /**
     * 分库分表插入数据测试
     * 对userId取模
     */
    @Test
    public void insertData() throws Exception {
        // 插入订单
        Order order = new Order();
        order.setUserId(111);
        this.orderDao.addOne(order);
        log.info("插入订单id:{}", order.getOrderId());

        // 插入订单明细
        OrderItem item = new OrderItem();
        item.setOrderId(order.getOrderId());
        item.setUserId(111);
        this.orderItemDao.addOne(item);
        log.info("插入订单明细id：{}", item.getOrderItemId());
    }

    /**
     * 查询用户订单
     */
    @Test
    public void selectOrder() {
        Order order = this.orderDao.selectOne(123L, 456L);
        System.out.println(order);
        List<Order> orders = this.orderDao.getOrderByUserId(2673);
        log.info("查询用户订单结果为：{}", Arrays.toString(orders.toArray()));
    }

    /**
     * 关联查询
     * 没有使用分片键，会产生笛卡尔积查询
     */
    @Test
    public void selectOrderItem() {
        List<OrderItem> orderItems = this.orderItemDao.getOrderItemByPrice(666);
        log.info("查询到结果为：{}", Arrays.toString(orderItems.toArray()));
    }
}

