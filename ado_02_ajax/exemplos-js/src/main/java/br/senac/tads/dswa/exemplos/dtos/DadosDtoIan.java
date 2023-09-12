package br.senac.tads.dswa.exemplos.dtos;

import java.time.LocalDate;
import lombok.*;

/**
 * @author Ian S. Pereira
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosDtoIan {
    
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String linkedInUrl;
    private String gitHubUrl;

}
