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
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_generator")
    @SequenceGenerator(name = "place_generator", sequenceName = "place_seq")
    private Long id;

    private String name;
    private String description;
    private String address;
    private Integer rate;

    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public void changeStatus() {
        if (this.status.equals("CLOSED"))
            this.status = "OPEN";
        else {
            if (this.status.equals("OPEN"))
                this.status = "CLOSED";
            else this.status = "UNEXPECTED";
        }
    }
}
