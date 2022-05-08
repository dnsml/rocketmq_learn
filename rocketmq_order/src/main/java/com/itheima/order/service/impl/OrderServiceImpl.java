package com.itheima.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.itheima.order.entity.OrderEntity;
import com.itheima.order.mapper.OrderMapper;
import com.itheima.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private boolean flag;

    @Override
    public boolean addOrder(OrderEntity orderEntity) {

        try {
            orderEntity.setIsDel(0);
            int insertResult = orderMapper.insert(orderEntity);

            if (insertResult>=1){

                Message<String> message = new GenericMessage<>(JSON.toJSONString(orderEntity));
                rocketMQTemplate.syncSend("order-topic:stock",message,5000,4);
//                //基础同步消息发送消息
//                rocketMQTemplate.convertAndSend("order-topic:stock",JSON.toJSON(orderEntity));

//                //同步消息发送消息
//                SendResult sendResult = rocketMQTemplate.syncSend("order-topic", JSON.toJSON(orderEntity));
//
//
//                SendStatus sendStatus = sendResult.getSendStatus();
//                if (!(0 == sendStatus.ordinal())){
//                    log.error("消息发送失败");
//                    return flag;
//                }

////                异步发送消息
//                rocketMQTemplate.asyncSend("order-topic", JSON.toJSON(orderEntity), new SendCallback() {
//                    @Override
//                    public void onSuccess(SendResult sendResult) {
//                        System.out.println("发送成功");
//                    }
//
//                    @Override
//                    public void onException(Throwable throwable) {
//                        System.out.println("发送失败");
//                        System.out.println(throwable.getMessage());
//                    }
//                });

//
////                单向发送无视结果
//                rocketMQTemplate.sendOneWay("order-topic",JSON.toJSON(orderEntity));
                flag=true;
                return flag;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        return flag;
    }

    @Override
    @Transactional
    public boolean delOrder(Integer id) {

        return true;
    }
}
