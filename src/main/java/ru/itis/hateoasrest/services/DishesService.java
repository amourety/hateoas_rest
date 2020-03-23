package ru.itis.hateoasrest.services;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.repositories.DishesRepository;

@Service
public class DishesService {

    private final DishesRepository dishesRepository;

    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    @SneakyThrows
    public Dish rateDown(final Long dishId) {
        val dish = dishesRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish with id " + dishId + " not found"));
        dish.rateDown();
        dishesRepository.save(dish);
        return dish;
    }

    @SneakyThrows
    public Dish rateUp(final Long dishId) {
        val dish = dishesRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish with id " + dishId + " not found"));
        dish.rateUp();
        dishesRepository.save(dish);
        return dish;
    }
}
