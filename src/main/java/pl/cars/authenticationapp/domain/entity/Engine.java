package pl.cars.authenticationapp.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_engine")
    private long idEngine;

    @NotNull
    private String company;

    @NotNull
    private String name;

    @NotNull
    private double volume;

    private String fuel;

    @NotNull
    private String power;

    private String transmission; //Manual or Automatic

    private String description;

    @ManyToMany(mappedBy = "engines")
//    @JoinTable(name = "car_engine",joinColumns = @JoinColumn(name = "id_engine"),inverseJoinColumns = @JoinColumn(name = "id_car"))
    private List<Car>cars;

    public Engine(String comapny, String name, double volume, String fuel, String power, String transmission, String description) {
        this.company = comapny;
        this.name = name;
        this.volume = volume;
        this.fuel = fuel;
        this.power = power;
        this.transmission = transmission;
        this.description = description;
    }

}
