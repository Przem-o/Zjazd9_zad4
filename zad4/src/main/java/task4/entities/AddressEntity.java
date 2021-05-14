package task4.entities;

import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;
@Data
@Builder /// adnotacja do tworzenia obiektów za pomocą .builder.build  zamiast new Address...
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    @OneToOne
    @MapsId // oznacza że wartosci klucza podstawowego zostaną skopiowane z klasy ClientEntity, w tej klasie nie ma pola id
    @JoinColumn(name = "id") // ta adnotacje dajemy tylko po 1 stronie
    private ClientEntity clientEntity;

}
