package ru.itis.hateoasrest.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.services.DishesService;

@Configuration
@Slf4j
public class DishRabbitMQListener {

    @Autowired
    private DishesService dishesService;

    @RabbitListener(queues = "rating_queue_up")
    public Dish replyWhenRatingUp(Dish dish) {
        log.info("[RATING UP] Received message with object [{}] from [{}]", dish, "rating_queue_up");
        dish = dishesService.rateUp(dish.getId());
        log.info("[RATING UP] Rating " + dish.getName() + " with id " + dish.getId() + " was up");
        log.info("[RATING UP RESPONSE OBJECT] " + dish);
        return dish;
    }

    @RabbitListener(queues = "rating_queue_down")
    public Dish replyWhenRatingDown(Dish dish) {
        log.info("[RATING DOWN] Received message with object [{}] from [{}]", dish, "rating_queue_down");
        dish = dishesService.rateDown(dish.getId());
        log.info("[RATING DOWN] Rating " + dish.getName() + " with id " + dish.getId() + " was down");
        log.info("[RATING DOWN RESPONSE OBJECT] " + dish);
        return dish;
    }
}
