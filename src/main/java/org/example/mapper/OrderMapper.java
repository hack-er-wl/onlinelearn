package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Order;


@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    int cancelOrder(String order_id);
    Order queryOrder(String order_id);
}
