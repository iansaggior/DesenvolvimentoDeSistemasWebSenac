package br.senac.tads.dsw.exemplos;

/**
 * @author Ian S Pereira
 */

import java.net.URI;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping("/listar")
    public List<Carro> findAll() {
        return service.findAll();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/buscar")
    public List<Carro> listar(
            @RequestParam("termo") String termoBusca,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int quantidade) {
        return service.buscar(termoBusca, pagina, quantidade);
    }

    @GetMapping("/{id}")
    public Carro findyById(@PathVariable Integer id) {
        Carro carro = service.findById(id);
        if (carro != null) {
            return carro;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "****ID " + id + " não encontrado****");
        }
    }

    @GetMapping("/{id}/opt")
    public Carro findByIdComOptional(@PathVariable Integer id) {
        return service.findByIdComOptional(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "****ID " + id + " não encontrado****"));
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Validated Carro dados) {
        service.save(dados);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dados.getId()).toUri();
        System.out.println("********\n" + dados.toString() + "\n********");
        return ResponseEntity.created(location).build();

    }

}
