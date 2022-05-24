package empresa.backend.repository;


import empresa.backend.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
    List<Cliente> findByActivoIsTrue();

    List<Cliente> findByNombreContainsIgnoreCaseAndActivoIsTrue(String nombre);

    Optional<Cliente> findClienteByNit(String nit);

}
