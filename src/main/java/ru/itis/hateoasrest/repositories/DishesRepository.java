package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Dish;

public interface DishesRepository extends JpaRepository<Dish, Long> {
}
