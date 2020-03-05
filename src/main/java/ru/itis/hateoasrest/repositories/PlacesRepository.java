package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Place;

public interface PlacesRepository extends JpaRepository<Place, Long> {
}
