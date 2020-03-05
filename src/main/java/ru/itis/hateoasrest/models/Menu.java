package ru.itis.hateoasrest.models;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_generator")
    @SequenceGenerator(name="menu_generator", sequenceName = "menu_seq")
    private Long id;

    private String name;

    @OneToMany
    private List<Section> sections;

}
