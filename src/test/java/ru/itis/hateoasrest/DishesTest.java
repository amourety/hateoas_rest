package ru.itis.hateoasrest;


import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoasrest.models.Dish;
import ru.itis.hateoasrest.services.DishesService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs("target/snippets")
public class DishesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DishesService dishesService;

    @BeforeEach
    public void setUp() {
        when(dishesService.rateDown(1L)).thenReturn(rateDown());
        when(dishesService.rateUp(1L)).thenReturn(rateUp());
    }

    @Test
    void dishRateDown() throws Exception {
        mockMvc.perform(put("/dishes/1/rateDown"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(rateDown().getName()))
                .andExpect(jsonPath("$.rate").value(49))
                .andDo(print())
                .andDo(document("dish_rateDown"));

    }

    @Test
    void dishRateUp() throws Exception {
        mockMvc.perform(put("/dishes/1/rateUp"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(rateDown().getName()))
                .andExpect(jsonPath("$.rate").value(51))
                .andDo(print())
                .andDo(document("dish_rateUp"));

    }

    private Dish rateDown() {
        val dish = init();
        dish.rateDown();
        return dish;
    }

    private Dish rateUp() {
        val dish = init();
        dish.rateUp();
        return dish;
    }

    private Dish init() {
        return Dish.builder()
                .id(1L)
                .name("BigMak")
                .description("Burger")
                .cost(150L)
                .rate(50)
                .build();
    }
}
