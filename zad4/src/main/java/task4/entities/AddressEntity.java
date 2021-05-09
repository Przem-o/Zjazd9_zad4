package task4.entities;

import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;
@Data
@Builder
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
    @MapsId
    @JoinColumn(name = "id")
    private ClientEntity clientEntity;

}
