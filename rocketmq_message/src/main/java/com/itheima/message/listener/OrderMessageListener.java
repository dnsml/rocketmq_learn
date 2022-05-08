package com.itheima.message.listener;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "order-topic",consumerGroup = "message_service_group",selectorExpression = "message")
public class OrderMessageListener implements RocketMQListener<Message> {

    @Override
    public void onMessage(Message message) {
        System.out.println("message service : "+message);
    }
}
