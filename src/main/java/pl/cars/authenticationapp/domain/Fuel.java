package pl.cars.authenticationapp.domain;

public enum Fuel {

    PETROL("PETROL"),
    DIESEL("DIESEL");

    private final String description;

    private Fuel(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
