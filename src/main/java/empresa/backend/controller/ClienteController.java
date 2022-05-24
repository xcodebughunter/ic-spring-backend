package empresa.backend.controller;


import empresa.backend.model.Cliente;
import empresa.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("")
    public List<Cliente> index(){
        return clienteRepository.findByActivoIsTrue();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
        if(clienteRepository.findClienteByNit(cliente.getNit()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con este NIT");
        } else {
            return this.clienteRepository.save(cliente);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable(value = "id") String id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/clientes/nombre/{nombre}")
    public List<Cliente> getClienteByName(@PathVariable(value = "nombre") String nombre) {
        return clienteRepository.findByNombreContainsIgnoreCaseAndActivoIsTrue(nombre);

    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(  @PathVariable String id, @RequestBody Cliente cliente){
        if(this.clienteRepository.findById(id).isPresent()){
            Cliente c = this.clienteRepository.findById(id).orElse(null);
            c.setNombre( cliente.getNombre());
            c.setDireccion( cliente.getDireccion());
            c.setNit( cliente.getNit() );
            c.setTelefono( cliente.getTelefono());
            c.setEmail( cliente.getEmail() );

            this.clienteRepository.save(c);
            return ResponseEntity.ok(c);

        } else {
            return ResponseEntity.badRequest().body(cliente);
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    void delete(@PathVariable String id){
        Cliente c = this.clienteRepository.findById(id).orElseThrow(RuntimeException::new);
        this.clienteRepository.delete(c);
    }

}
