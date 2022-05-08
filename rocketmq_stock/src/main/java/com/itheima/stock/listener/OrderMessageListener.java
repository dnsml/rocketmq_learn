package com.itheima.stock.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.stock.entity.MessageEntity;
import com.itheima.stock.entity.OrderEntity;
import com.itheima.stock.entity.StockEntity;
import com.itheima.stock.mapper.MessageMapper;
import com.itheima.stock.service.StockService;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RocketMQMessageListener(topic = "order-topic",consumerGroup = "stock-service-group",selectorExpression = "stock")
public class OrderMessageListener implements RocketMQListener<String> {

    @Autowired
    private StockService stockService;

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public void onMessage(String message) {

        System.out.println(LocalTime.now()+"stock service : "+ message);

        //解析消息
        OrderEntity orderEntity = JSON.parseObject(message, OrderEntity.class);

        QueryWrapper<StockEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StockEntity::getGoodsId,orderEntity.getGoodsId());
        StockEntity stockEntity = stockService.getOne(wrapper);

        stockService.reduceStock(orderEntity.getGoodsId(),orderEntity.getNumber(),stockEntity.getVersion());

    }
}
