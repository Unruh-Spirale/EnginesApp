package pl.cars.authenticationapp.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    public Engine() {
    }

    public Engine(String comapny, String name, double volume, String fuel, String power, String transmission, String description) {
        this.company = comapny;
        this.name = name;
        this.volume = volume;
        this.fuel = fuel;
        this.power = power;
        this.transmission = transmission;
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getIdEngine() {
        return idEngine;
    }

    public void setIdEngine(long idEngine) {
        this.idEngine = idEngine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
