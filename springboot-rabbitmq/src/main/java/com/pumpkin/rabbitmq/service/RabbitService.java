package com.pumpkin.rabbitmq.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author User
 */
@Service
@Slf4j
public class RabbitService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "bookQueue")
    public void bookListener1(Message message, Channel channel) throws IOException, InterruptedException {
        // 手动确认消息模式下，必须对消息进行应答
        Thread.sleep(1000);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("listener1 receive a message: {}", new String(message.getBody()));
    }

    @RabbitListener(queues = "bookQueue")
    public void bookListener2(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("listener2 receive a message: {}", new String(message.getBody()));
    }
}
