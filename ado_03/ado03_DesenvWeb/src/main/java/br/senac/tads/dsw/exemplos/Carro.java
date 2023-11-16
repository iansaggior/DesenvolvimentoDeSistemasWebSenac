package br.senac.tads.dsw.exemplos;

/**
 * @author Ian S Pereira
 */


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Carro {
	private Integer id;
    @NotBlank
    private String modelo;
    @NotBlank
    private String fabricante;
    private int anoLancamento;

}
