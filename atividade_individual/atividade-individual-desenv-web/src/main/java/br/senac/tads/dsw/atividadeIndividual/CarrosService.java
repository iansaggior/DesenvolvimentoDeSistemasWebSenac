package br.senac.tads.dsw.atividadeIndividual;

/**
 * @author Ian S Pereira
 */

import java.util.List;
import java.util.Optional;

public interface CarrosService {

    List<Carros> findAll();

    List<Carros> buscar(String termoBusca, int pagina, int quantidade);

    Carros findById(int id);

//    Optional<CarrosDto> findByIdComOptional(int id);

    void save(Carros dadosCarro);

}
