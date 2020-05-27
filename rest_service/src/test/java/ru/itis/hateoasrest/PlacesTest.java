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
import ru.itis.hateoasrest.models.Place;
import ru.itis.hateoasrest.services.PlaceService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs("target/snippets")
public class PlacesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaceService placeService;

    @BeforeEach
    public void setUp() {
        when(placeService.changeStatus(1L)).thenReturn(changeStatusPlace());
    }

    @Test
    void openPlace() throws Exception {
        mockMvc.perform(put("/places/1/changeStatus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(changeStatusPlace().getName()))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andDo(print())
                .andDo(document("change_status_place",
                        responseFields(
                                subsectionWithPath("_links").description("Линки"),
                                fieldWithPath("name").description("Название заведения"),
                                fieldWithPath("description").description("Описание заведения"),
                                fieldWithPath("address").description("Адрес заведения"),
                                fieldWithPath("rate").description("Рейтинг заведения"),
                                fieldWithPath("status").description("Статус заведения (Открыто/Закрыто)"))
                ));

    }

    private Place changeStatusPlace() {
        val place = Place.builder()
                .id(1L)
                .name("Макдональс")
                .description("Фастфуд")
                .address("Казань")
                .rate(100)
                .status("CLOSED")
                .build();
        place.changeStatus();
        return place;
    }
}
