package org.example.service.impl;

import org.example.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderServiceImpl {
    int insertOrder(Order order);
    int cancelOrder(String order_id);
    Order queryOrder(String user_id);
    List queryOrderAll();
}
