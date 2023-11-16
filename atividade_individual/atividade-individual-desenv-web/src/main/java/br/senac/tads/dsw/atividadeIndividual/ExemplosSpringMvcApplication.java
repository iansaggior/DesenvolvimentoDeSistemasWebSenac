package br.senac.tads.dsw.atividadeIndividual;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExemplosSpringMvcApplication implements CommandLineRunner {

	@Autowired
	private CarrosRepository carrosRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExemplosSpringMvcApplication.class, args);
	}

	private void addNewItem(Carros c) {
		Set<Carros> carros = new HashSet<>();
		carros.add(c);
		carrosRepository.save(c);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if (carrosRepository.count() == 0) {
		addNewItem(new Carros("Corolla", "Toyta", 1998));
		addNewItem(new Carros("Gol", "WV", 1980));
		addNewItem(new Carros("HB20", "Hyunday", 2012));
		addNewItem(new Carros("Lancer", "Mitsubishi", 1973));
		addNewItem(new Carros("Opala", "Chevrolet", 1968));
		}
	}

}
