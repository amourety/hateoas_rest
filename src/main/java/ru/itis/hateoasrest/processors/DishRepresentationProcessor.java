package ru.itis.hateoasrest.processors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoasrest.controllers.CustomDishesController;
import ru.itis.hateoasrest.models.Dish;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DishRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Dish>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Dish> process(EntityModel<Dish> model) {
        Dish dish = model.getContent();

        if(dish.getRate() < 100){
            model.add(linkTo(methodOn(CustomDishesController.class).rateUp(dish.getId())).withRel("rateUp"));
        }
        if(dish.getRate() > 0){
            model.add(linkTo(methodOn(CustomDishesController.class).rateDown(dish.getId())).withRel("rateDown"));
        }
        return model;
    }
}
