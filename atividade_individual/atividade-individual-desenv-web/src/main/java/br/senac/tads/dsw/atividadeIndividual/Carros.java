package br.senac.tads.dsw.atividadeIndividual;

/**
 * @author Ian S Pereira
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Carros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20, nullable = false)
    @NotBlank(message = "Preencha o modelo do veículo")
    @Size(max = 20)
    private String modelo;
    @Column(length = 20, nullable = false)
    @NotBlank(message = "Preencha o fabricante do veículo")
    @Size(max = 20)
    private String fabricante;
    @Column(length = 4, nullable = false)
    @NotNull(message = "Preencha o ano do lançamento do veículo")
    @Min(value = 1900, message = "O ano de lançamento do veículo deve ser maior que 1900")
    private int anoLancamento;

    public Carros() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Carros(String modelo, String fabricante, int anoLancamento) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.anoLancamento = anoLancamento;
    }
    public Carros(Carros bd) {
        this.id = bd.getId();
        this.modelo = bd.getModelo();
        this.fabricante = bd.getFabricante();
        this.anoLancamento = bd.getAnoLancamento();
    }

    @Override
    public String toString() {
        return "Carros{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}
