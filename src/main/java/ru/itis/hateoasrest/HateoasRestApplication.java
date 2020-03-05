package ru.itis.hateoasrest;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.models.Menu;
import ru.itis.hateoasrest.models.Place;
import ru.itis.hateoasrest.models.Section;
import ru.itis.hateoasrest.repositories.DishesRepository;
import ru.itis.hateoasrest.repositories.MenusRepository;
import ru.itis.hateoasrest.repositories.PlacesRepository;
import ru.itis.hateoasrest.repositories.SectionsRepository;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateoasRestApplication.class, args);
    }
    public void initializeDB(String[] args) {
        val context = SpringApplication.run(HateoasRestApplication.class, args);

        val placesRepository = context.getBean(PlacesRepository.class);
        val menusRepository = context.getBean(MenusRepository.class);
        val dishesRepository = context.getBean(DishesRepository.class);
        val sectionsRepository = context.getBean(SectionsRepository.class);

        Dish dishTatmak1 = Dish.builder()
                .name("Pizza")
                .description("Pizza with cheese")
                .cost(50L)
                .rate(100)
                .build();
        Dish dishTatmak2 = Dish.builder()
                .name("Pizza")
                .description("Pizza with mushrooms")
                .cost(70L)
                .rate(100)
                .build();
        Dish dishMak1 = Dish.builder()
                .name("BigMak")
                .description("Burger")
                .cost(150L)
                .rate(100)
                .build();
        Dish dishMak2 = Dish.builder()
                .name("BigMak BIG")
                .description("Burger")
                .cost(200L)
                .rate(100)
                .build();


        Section burgers = Section.builder()
                .name("Бургеры")
                .dishes(asList(dishMak1,dishMak2))
                .build();
        Section pizzas = Section.builder()
                .name("Пиццы")
                .dishes(asList(dishTatmak1,dishTatmak2))
                .build();
        Menu tatmakMenu = Menu.builder()
                .name("TatMakMenu")
                .sections(asList(pizzas))
                .build();
        Menu makMenu = Menu.builder()
                .name("MakMenu")
                .sections(asList(burgers))
                .build();

        Place placeTatmak = Place.builder()
                .name("ТатМак")
                .description("Фастфуд")
                .address("Казань")
                .rate(100)
                .build();
        Place placeMak = Place.builder()
                .name("Макдональс")
                .description("Фастфуд")
                .address("Казань")
                .rate(100)
                .build();

        dishesRepository.saveAll(asList(dishMak1,dishMak2,dishTatmak1,dishTatmak2));
        sectionsRepository.saveAll(asList(burgers,pizzas));
        menusRepository.saveAll(asList(makMenu,tatmakMenu));
        placeTatmak.setMenu(tatmakMenu);
        placeMak.setMenu(makMenu);
        placesRepository.saveAll(asList(placeMak,placeTatmak));
    }
}
