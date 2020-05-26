package ru.itis.hateoasrest.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.repositories.DishesRepository;

import java.util.List;

@Component
public class DishDatabaseClient implements DishClient {
    @Autowired
    private DishesRepository repository;

    @Override
    public Flux<Dish> getAll() {
        List<Dish> records = repository.findAll();
        return Flux.fromIterable(records);
    }
}
