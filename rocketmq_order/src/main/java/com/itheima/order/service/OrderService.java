package com.itheima.order.service;

import com.itheima.order.entity.OrderEntity;

public interface OrderService {

    boolean addOrder(OrderEntity orderEntity);

    boolean delOrder(Integer id);
}
