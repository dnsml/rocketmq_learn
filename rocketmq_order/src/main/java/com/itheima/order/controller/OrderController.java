package com.itheima.order.controller;

import com.itheima.order.entity.OrderEntity;
import com.itheima.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody OrderEntity orderEntity){
        if (orderService.addOrder(orderEntity)){
            return "下单成功";
        }
        return "下单失败";
    }

    @RequestMapping("/delOrder/{id}")
    public String delOrder(@PathVariable("id") Integer id){
        if (orderService.delOrder(id)){
            return "删除订单成功";
        }
        return "删除订单失败";
    }
}
