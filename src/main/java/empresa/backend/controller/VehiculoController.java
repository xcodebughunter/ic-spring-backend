package empresa.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import empresa.backend.model.Vehiculo;
import empresa.backend.repository.VehiculoRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoController(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping("")
    List<Vehiculo> index(){
        return this.vehiculoRepository.findAll();
    }
    /*Page<Vehiculo> index(Pageable p){
        return this.vehiculoRepository.findAll(p);
    }*/

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    Vehiculo create( @RequestBody Vehiculo vehiculo){

        if(vehiculoRepository.findVehiculoByPlacasIgnoreCase(vehiculo.getPlacas()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un Vehiculo con esas placas");
        } else {
            return vehiculoRepository.save(vehiculo);
        }
    }

    @PutMapping("{id}")
    Vehiculo update( @PathVariable String id, @RequestBody Vehiculo vehiculo){

        Vehiculo v = this.vehiculoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Vehiculo no encontrado"));

        v.setMarca(vehiculo.getMarca());
        v.setModelo(vehiculo.getModelo());
        v.setPlacas(vehiculo.getPlacas());
        v.setCliente(vehiculo.getCliente());

        return this.vehiculoRepository.save(v);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete( @PathVariable String id){

        Vehiculo v = this.vehiculoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Vehiculo no encontrado"));

        this.vehiculoRepository.delete(v);

    }

}