package ru.itis.hateoasrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.services.DishesService;

@RestController
public class DishController {

    @Autowired
    private DishesService service;

    @GetMapping(value = "/webflux/dish", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> getAll() {
        return service.getAll();
    }
}
