package empresa.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import empresa.backend.model.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    Optional<Vehiculo> findVehiculoByPlacasIgnoreCase(String placa);

}
