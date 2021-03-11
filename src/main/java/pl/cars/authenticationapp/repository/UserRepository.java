package pl.cars.authenticationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.cars.authenticationapp.domain.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);

    @Query("select u from User u order by u.login")
    List<User> getAllUserSorted();

}
