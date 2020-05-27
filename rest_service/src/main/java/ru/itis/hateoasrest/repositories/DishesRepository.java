package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoasrest.models.Dish;

import java.util.List;

@RepositoryRestResource
public interface DishesRepository extends JpaRepository<Dish, Long> {

    @RestResource(path = "name", rel = "findAllByName")
    List<Dish> findAllByName(String name);

}
