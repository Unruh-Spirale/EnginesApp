package pl.cars.authenticationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.cars.authenticationapp.domain.entity.Users;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Users findByLogin(String login);

    @Query("select u from Users u order by u.login")
    List<Users> getAllUserSorted();

}
