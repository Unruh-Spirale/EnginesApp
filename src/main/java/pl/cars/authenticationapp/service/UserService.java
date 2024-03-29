package pl.cars.authenticationapp.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cars.authenticationapp.domain.entity.UserRole;
import pl.cars.authenticationapp.domain.entity.Users;
import pl.cars.authenticationapp.repository.UserRepository;
import pl.cars.authenticationapp.repository.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public void addWithDefaultRole(Users users){
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        users.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(users.getPassword());
        users.setPassword(passwordHash);
        userRepository.save(users);
    }

    public void addAdmin(Users users){
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        UserRole adminRole = userRoleRepository.findByRole(ADMIN_ROLE);
        users.getRoles().add(defaultRole);
        users.getRoles().add(adminRole);
        String passwordHash = passwordEncoder.encode(users.getPassword());
        users.setPassword(passwordHash);
        userRepository.save(users);
    }

    public List<Users> getAllUsers(){
        List<Users> users = userRepository.getAllUserSorted();
        return users;
    }

    public List<Users> getOnlyUsers(){
//        List<User>users = userRepository.findAll().
//                stream()
//                .filter(x -> !x.getLogin().equals("admin"))
//                .collect(Collectors.toList());
                List<Users>users = userRepository.findAll().
                stream()
                .filter(x -> x.getRoles().size()<2)
                .collect(Collectors.toList());
        return users;
    }

    public void deleteUser(long id){
        Users users = userRepository.findById(id).get();
        users.getRoles().clear();
        userRepository.deleteById(id);
    }
}
