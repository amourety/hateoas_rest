package ru.itis.hateoasrest.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_generator")
    @SequenceGenerator(name="dish_generator", sequenceName = "dish_seq")
    private Long id;

    private String name;
    private String description;

    private Long cost;
    private Integer rate;

    public void rateUp(){
        if (this.rate < 100)
            this.rate++;
    }
    public void rateDown(){
        if (this.rate >0)
            this.rate--;


    }
}
