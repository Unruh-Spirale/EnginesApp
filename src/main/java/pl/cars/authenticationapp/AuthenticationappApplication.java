package pl.cars.authenticationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.cars.authenticationapp.domain.Fuel;
import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Engine;
import pl.cars.authenticationapp.domain.entity.User;
import pl.cars.authenticationapp.repository.UserRepository;
import pl.cars.authenticationapp.service.CarService;
import pl.cars.authenticationapp.service.EngineService;
import pl.cars.authenticationapp.service.UserService;

@SpringBootApplication
public class AuthenticationappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AuthenticationappApplication.class, args);

        UserService userService = ctx.getBean(UserService.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        User admin = new User("admin","admin");
        userRepository.save(admin);
        userService.addAdmin(admin);

        User user = new User("user","user");
        userRepository.save(user);
        userService.addWithDefaultRole(user);

        CarService carService = ctx.getBean(CarService.class);
        EngineService engineService = ctx.getBean(EngineService.class);

        Car car1 = new Car("Audi","A5","8T","2007-2016");
        Car car2 = new Car("Audi","A4","B8","2007-2015");
        Car car3 = new Car("Audi","Q5","8R","2008-2016");
        Car car4 = new Car("Audi","Q7","4L","2005-2015");
        Car car5 = new Car("Audi","A8","D3","2002-2009");

        Car car6 = new Car("BMW","3 Series","E46","1997-2006");
        Car car7 = new Car("BMW","5 Series","E60","2003-2010");
        Car car8 = new Car("BMW","X5 Series","E53","1999-2006");

        carService.saveCar(car1);
        carService.saveCar(car2);
        carService.saveCar(car3);
        carService.saveCar(car4);
        carService.saveCar(car5);
        carService.saveCar(car6);
        carService.saveCar(car7);
        carService.saveCar(car8);

        Engine engine1 = new Engine("Audi","2.0 TDI CR",2.0, Fuel.DIESEL.getDescription(),"136/140/170/190","6M,7DSG,CVT","good durability");
        Engine engine2 = new Engine("Audi","3.0 TDI",3.0,Fuel.DIESEL.getDescription(), "204/225/240/313","6M,6A,7DSG,8A","good durability, low fuel consumption");
        Engine engine3 = new Engine("BMW","M57",3.0,Fuel.DIESEL.getDescription(),"184/197/218/235","6M,6A","good durability but high fuel consumption");

        engineService.saveEngine(engine1);
        engineService.saveEngine(engine2);
        engineService.saveEngine(engine3);

        car1.addEngine(engine1);
        car1.addEngine(engine2);

        car2.addEngine(engine1);
        car2.addEngine(engine2);

        car3.addEngine(engine1);

        car4.addEngine(engine2);

        car5.addEngine(engine2);

        car6.addEngine(engine3);
        car7.addEngine(engine3);
        car8.addEngine(engine3);

        carService.saveCar(car1);
        carService.saveCar(car2);
        carService.saveCar(car3);
        carService.saveCar(car4);
        carService.saveCar(car5);
        carService.saveCar(car6);
        carService.saveCar(car7);
        carService.saveCar(car8);


    }

}
