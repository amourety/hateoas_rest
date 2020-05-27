package ru.itis.hateoasrest.controllers;

import lombok.val;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.hateoasrest.services.PlaceService;

@RepositoryRestController
public class CustomPlacesController {

    private final PlaceService service;

    public CustomPlacesController(PlaceService service) {
        this.service = service;
    }

    @PutMapping("/places/{place-id}/changeStatus")
    public ResponseEntity<?> changeStatus(@PathVariable("place-id") final Long placeId) {
        val place = service.changeStatus(placeId);
        return ResponseEntity.ok(new EntityModel<>(place));
    }
}
