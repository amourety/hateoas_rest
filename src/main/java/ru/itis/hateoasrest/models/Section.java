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
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_generator")
    @SequenceGenerator(name="section_generator", sequenceName = "section_seq")
    private Long id;

    private String name;

    @OneToMany
    private List<Dish> dishes;

}
