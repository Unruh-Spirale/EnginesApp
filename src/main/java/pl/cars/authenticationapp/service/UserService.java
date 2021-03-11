package pl.cars.authenticationapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.cars.authenticationapp.domain.entity.User;
import pl.cars.authenticationapp.domain.entity.UserRole;
import pl.cars.authenticationapp.repository.UserRepository;
import pl.cars.authenticationapp.repository.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void addWithDefaultRole(User user){
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

    public void addAdmin(User user){
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        UserRole adminRole = userRoleRepository.findByRole(ADMIN_ROLE);
        user.getRoles().add(defaultRole);
        user.getRoles().add(adminRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.getAllUserSorted();
        return users;
    }

    public List<User> getOnlyUsers(){
//        List<User>users = userRepository.findAll().
//                stream()
//                .filter(x -> !x.getLogin().equals("admin"))
//                .collect(Collectors.toList());
                List<User>users = userRepository.findAll().
                stream()
                .filter(x -> x.getRoles().size()<2)
                .collect(Collectors.toList());
        return users;
    }

    public void deleteUser(long id){
        User user = userRepository.findById(id).get();
        user.getRoles().clear();
        userRepository.deleteById(id);
    }
}
