package com.qyf404.learn.springboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    @Autowired
    private  RabbitTemplate rabbitTemplate;
    @Test
    public  void sendMessage() {
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "d", "Hello from RabbitMQ!");
    }

}
