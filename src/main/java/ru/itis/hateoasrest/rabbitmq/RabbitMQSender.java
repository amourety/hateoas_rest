package ru.itis.hateoasrest.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.services.DishesService;


@Service
@Slf4j
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private DishesService service;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey1}")
    private String routingKey1;
    @Value("${rabbitmq.routingKey2}")
    private String routingKey2;

    public Dish sendMessageAboutDishRatingUp(Long dishId) {
        val dish = service.findById(dishId);
        log.info("[MESSAGE SENDER] Message will be sent with object to consumer");
        log.info("[MESSAGE SENDER] Object: " + dish);
        return (Dish) rabbitTemplate.convertSendAndReceive(exchange, routingKey1, dish);
    }

    public Dish sendMessageAboutDishRatingDown(Long dishId) {
        val dish = service.findById(dishId);
        log.info("[MESSAGE SENDER] Message will be sent with object to consumer");
        log.info("[MESSAGE SENDER] Object: " + dish);
        return (Dish) rabbitTemplate.convertSendAndReceive(exchange, routingKey2, dish);
    }
}