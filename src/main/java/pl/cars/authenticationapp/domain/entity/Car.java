package pl.cars.authenticationapp.domain.entity;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private long idCar;

    @NotNull
    private String mark;    //Audi

    @NotNull
    private String model;   //A5

    private String generation;  //8T or F5

    private String yearOfProduction; // 2007-2016 or 2016 - present

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "car_engine", joinColumns = @JoinColumn(name = "id_car"),inverseJoinColumns = @JoinColumn(name = "id_engine"))
    private List<Engine> engines;

    public void addEngine(Engine engine){
        if(engines == null){
            engines = new ArrayList<>();
        }
        engines.add(engine);
    }
    public void removeEngine(Engine engine){
        engines.remove(engine);
    }

    public Car() {
    }

    public Car(String mark, String model, String generation, String yearOfProduction) {
        this.mark = mark;
        this.model = model;
        this.generation = generation;
        this.yearOfProduction = yearOfProduction;
    }

    public long getIdCar() {
        return idCar;
    }

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public void setEngines(List<Engine> engines) {
        this.engines = engines;
    }
}
