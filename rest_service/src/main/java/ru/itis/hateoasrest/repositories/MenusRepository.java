package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Menu;

public interface MenusRepository extends JpaRepository<Menu,Long> {
}
