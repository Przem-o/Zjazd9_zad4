package task4.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "smartphone")
public class SmartphoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "smartphoneEntities")
    private Set<ClientEntity> clientEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<ClientEntity> getClientEntities() {
        return clientEntities;
    }

    public void setClientEntities(Set<ClientEntity> clientEntities) {
        this.clientEntities = clientEntities;
    }

}
