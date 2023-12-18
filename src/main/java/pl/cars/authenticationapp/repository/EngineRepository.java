package pl.cars.authenticationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.cars.authenticationapp.domain.entity.Engine;

import java.util.List;
import java.util.Optional;

@Repository
public interface EngineRepository extends JpaRepository<Engine,Long> {

    @Query("select e from Engine e order by e.company asc ")
    List<Engine> getAllSortedEngines();

    Optional<Engine> findByNameIgnoreCase(String name);

}
