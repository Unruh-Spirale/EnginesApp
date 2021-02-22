package pl.cars.authenticationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cars.authenticationapp.domain.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole findByRole(String role);
}
