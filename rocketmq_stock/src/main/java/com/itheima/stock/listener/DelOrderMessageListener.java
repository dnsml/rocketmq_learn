package com.itheima.stock.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.stock.entity.MessageEntity;
import com.itheima.stock.entity.OrderEntity;
import com.itheima.stock.entity.StockEntity;
import com.itheima.stock.mapper.MessageMapper;
import com.itheima.stock.service.StockService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

//@Component
//@RocketMQMessageListener(topic = "order-del-topic",consumerGroup = "order-del-group")
public class DelOrderMessageListener implements RocketMQListener<String> {

    @Autowired
    private StockService stockService;

    @Override
    public void onMessage(String message) {

        System.out.println("stock receive info : " + message);
        OrderEntity orderEntity = JSON.parseObject(message, OrderEntity.class);
        QueryWrapper<StockEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StockEntity::getGoodsId,orderEntity.getGoodsId());
        StockEntity stockEntity = stockService.getOne(wrapper);

        stockService.revertStock(stockEntity.getGoodsId(),orderEntity.getNumber(),stockEntity.getVersion());
    }
}
