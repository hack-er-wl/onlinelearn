package org.example.service;

import org.example.entity.Order;
import org.example.mapper.OrderMapper;
import org.example.service.impl.OrderServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService implements OrderServiceImpl {
    @Resource
    private OrderMapper orderMapper;
    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public int cancelOrder(String order_id) {
        return orderMapper.cancelOrder(order_id);
    }

    @Override
    public Order queryOrder(String user_id) {
        return orderMapper.queryOrder(user_id);
    }
}
