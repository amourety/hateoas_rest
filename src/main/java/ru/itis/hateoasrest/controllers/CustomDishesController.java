package ru.itis.hateoasrest.controllers;

import lombok.val;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.hateoasrest.services.DishesService;

@RepositoryRestController
public class CustomDishesController {

    private final DishesService service;

    public CustomDishesController(DishesService service) {
        this.service = service;
    }

    @PutMapping("/dishes/{dish-id}/rateDown")
    public ResponseEntity<?> rateDown(@PathVariable("dish-id") final Long dishId) {
        val dish = service.rateDown(dishId);
        return ResponseEntity.ok(new EntityModel<>(dish));

    }

    @PutMapping("/dishes/{dish-id}/rateUp")
    public ResponseEntity<?> rateUp(@PathVariable("dish-id") final Long dishId){
        val dish = service.rateUp(dishId);
        return ResponseEntity.ok(new EntityModel<>(dish));

    }
}
