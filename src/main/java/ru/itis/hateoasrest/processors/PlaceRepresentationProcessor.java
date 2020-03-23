package ru.itis.hateoasrest.processors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoasrest.controllers.CustomPlacesController;
import ru.itis.hateoasrest.models.Place;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlaceRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Place>> {

    @Override
    public EntityModel<Place> process(EntityModel<Place> model) {

        Place place = model.getContent();

        if (!place.getStatus().equals("UNEXPECTED")) {
            model.add(linkTo(methodOn(CustomPlacesController.class).changeStatus(place.getId())).withRel("changeStatus"));
        }
        return model;
    }
}
