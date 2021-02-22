package pl.cars.authenticationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import pl.cars.authenticationapp.domain.entity.Car;
import pl.cars.authenticationapp.domain.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query("select c from Car c order by c.model asc ")
    List<Car> getAllSortedCars();
}
