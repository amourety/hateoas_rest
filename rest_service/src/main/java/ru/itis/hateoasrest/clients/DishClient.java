package ru.itis.hateoasrest.clients;

import reactor.core.publisher.Flux;
import ru.itis.hateoasrest.models.Dish;

public interface DishClient {

    Flux<Dish> getAll();
}
