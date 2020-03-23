package ru.itis.hateoasrest.services;

import javassist.NotFoundException;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.itis.hateoasrest.models.Place;
import ru.itis.hateoasrest.repositories.PlacesRepository;

@Service
public class PlaceService {

    private final PlacesRepository placesRepository;

    public PlaceService(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    @SneakyThrows
    public Place changeStatus(Long placeId) {
        val place = placesRepository.findById(placeId).orElseThrow(() -> new NotFoundException("Place with id " + placeId + " not found"));
        place.changeStatus();
        placesRepository.save(place);
        return place;
    }
}
