package br.senac.tads.dsw.exemplos;

/**
 * @author Ian S Pereira
 */

import java.util.List;
import java.util.Optional;

public interface CarroService {
	List<Carro> findAll();

	List<Carro> buscar(String termoBusca, int pagina, int quantidade);

	Carro findById(int id);

	Optional<Carro> findByIdComOptional(int id);

	void save(Carro dados);

}
