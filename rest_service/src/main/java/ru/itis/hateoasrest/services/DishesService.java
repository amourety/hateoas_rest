package ru.itis.hateoasrest.services;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.hateoasrest.clients.DishClient;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.repositories.DishesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishesService {

    @Autowired
    private List<DishClient> clients;

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

    public Dish findById(final Long dishId) {
        return dishesRepository.findById(dishId).get();
    }

    @SneakyThrows
    public Dish rateUp(final Long dishId) {
        val dish = dishesRepository.findById(dishId).orElseThrow(() -> new NotFoundException("Dish with id " + dishId + " not found"));
        dish.rateUp();
        dishesRepository.save(dish);
        return dish;
    }

    public Flux<Dish> getAll() {
        List<Flux<Dish>> fluxes = clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    public void fillDataBase(int count) {
        for (int i = 0; i < count; i++) {
            Dish dish = Dish.builder().cost(100L).description("f").name("testflux").rate(100).build();
            dishesRepository.save(dish);
        }
    }

    private Flux<Dish> getAll(DishClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }
}
