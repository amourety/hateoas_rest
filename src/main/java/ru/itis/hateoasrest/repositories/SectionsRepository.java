package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Section;

public interface SectionsRepository extends JpaRepository<Section, Long> {
}
