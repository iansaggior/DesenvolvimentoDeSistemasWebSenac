package br.senac.tads.dsw.atividadeIndividual;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarrosController {
    @Autowired
    private CarrosService service;

    @GetMapping
    public List<Carros> findAll() {
        return service.findAll();
    }

    @GetMapping("/busca")
    public List<Carros> buscar(
            @RequestParam("termo") String termoBusca,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "50") int quantidade) {
        return service.buscar(termoBusca, pagina, quantidade);
    }

    @GetMapping("/{id}")
    public Carros findById(@PathVariable Integer id) {
        Carros carros = service.findById(id);
        if (carros != null) {
            return carros;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID (" + id + ") não foi encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Carros carros){
        System.out.println("********"+ carros.toString() +"********");
        service.save(carros);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carros.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
