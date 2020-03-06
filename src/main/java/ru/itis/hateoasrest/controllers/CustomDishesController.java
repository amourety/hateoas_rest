package ru.itis.hateoasrest.controllers;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.repositories.DishesRepository;

@RepositoryRestController
public class CustomDishesController {

    private final DishesRepository dishesRepository;

    public CustomDishesController(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    @PostMapping("/dishes/{dish-id}/rateDown")
    @SneakyThrows
    public ResponseEntity<Dish> rateDown(@PathVariable("dish-id") final Long dishId){
        val dish = dishesRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish with id " + dishId + " not found"));
        dish.rateDown();
        dishesRepository.save(dish);
        return ResponseEntity.ok(dish);

    }
    @PostMapping("/dishes/{dish-id}/rateUp")
    @SneakyThrows
    public ResponseEntity<Dish> rateUp(@PathVariable("dish-id") final Long dishId){
        val dish = dishesRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish with id " + dishId + " not found"));
        dish.rateUp();
        dishesRepository.save(dish);
        return ResponseEntity.ok(dish);

    }
}
