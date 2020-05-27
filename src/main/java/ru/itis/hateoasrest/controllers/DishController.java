package ru.itis.hateoasrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.services.DishesService;

@RestController
public class DishController {

    @Autowired
    private DishesService service;

    @GetMapping(value = "/webflux/filldb/{count}")
    public ResponseEntity<String> fillDb(@PathVariable("count") final String count) {
        service.fillDataBase(Integer.parseInt(count));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/webflux/dish", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish> getAll() {
        return service.getAll();
    }
}
