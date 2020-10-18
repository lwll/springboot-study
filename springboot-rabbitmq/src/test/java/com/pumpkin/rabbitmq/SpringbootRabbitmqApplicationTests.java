package com.pumpkin.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void testDirectExchange() {
        for (int i = 0; i < 200; i++) {
            rabbitTemplate.convertAndSend("book.direct","book", "book" + i);
        }
    }

    @Test
    void testMandatory() {
        rabbitTemplate.convertAndSend("book.direct", "movie", "movie");
    }
}
