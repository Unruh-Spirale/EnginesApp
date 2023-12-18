package pl.cars.authenticationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.cars.authenticationapp.domain.entity.Users;
import pl.cars.authenticationapp.repository.UserRepository;
import pl.cars.authenticationapp.service.UserService;

@SpringBootApplication
public class AuthenticationappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AuthenticationappApplication.class, args);

        UserService userService = ctx.getBean(UserService.class);
        UserRepository userRepository = ctx.getBean(UserRepository.class);

        Users admin = new Users("admin","admin");
        userRepository.save(admin);
        userService.addAdmin(admin);

        Users users = new Users("user","user");
        userRepository.save(users);
        userService.addWithDefaultRole(users);

        DataInitializer initializer = ctx.getBean(DataInitializer.class);
        initializer.initialize();

    }

}
