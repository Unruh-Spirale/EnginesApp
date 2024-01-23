package pl.cars.authenticationapp.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "engines")
//    @JoinTable(name = "car_engine",joinColumns = @JoinColumn(name = "id_engine"),inverseJoinColumns = @JoinColumn(name = "id_car"))
    private Set<Car> cars = new HashSet<>();

    public Engine(String company, String name, double volume, String fuel, String power, String transmission, String description) {
        this.company = company;
        this.name = name;
        this.volume = volume;
        this.fuel = fuel;
        this.power = power;
        this.transmission = transmission;
        this.description = description;
    }
    public void removeCar(Car car) {
        cars.remove(car);
    }

}
