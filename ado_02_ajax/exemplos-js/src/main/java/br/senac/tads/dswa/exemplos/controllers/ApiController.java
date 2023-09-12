
package br.senac.tads.dswa.exemplos.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dswa.exemplos.dtos.DadosDtoIan;

/**
 * @author Ian S. Pereira
 */
@RestController
@CrossOrigin("*")
public class ApiController {

	@GetMapping("/dadosIan")
	public DadosDtoIan acessarDados() {
		DadosDtoIan dados = new DadosDtoIan(
			"IAN SAGGIORATTO PEREIRA",
			"ian.saggior.pereira@gmail.com",
			"(11)1234-5678",
			LocalDate.parse("2004-11-30"),
			"https://www.linkedin.com/in/ian-saggioratto-pereira-9966b4b7/",
			"https://github.com/iansaggior");
		return dados;
	}



}
