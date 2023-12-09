package br.senac.tads.dsw.atividadeIndividual;

import jakarta.validation.constraints.*;

public class CarrosDto {

    private Integer id;
    @NotBlank(message = "Preencha o modelo do veículo")
    @Size(max = 20)
    private String modelo;
    @NotBlank(message = "Preencha o fabricante do veículo")
    @Size(max = 20)
    private String fabricante;
    @NotNull(message = "Preencha o ano do lançamento do veículo")
    @Min(value = 1900, message = "O ano de lançamento do veículo deve ser maior que 1900")
    private int anoLancamento;

    public CarrosDto() {
    }

    public CarrosDto(Integer id, String modelo, String fabricante, int anoLancamento) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.anoLancamento = anoLancamento;
    }

    public CarrosDto(String modelo, String fabricante, int anoLancamento) {
        this(null, modelo, fabricante, anoLancamento);
    }

    public CarrosDto(Carros bd) {
        this.id = bd.getId();
        this.modelo = bd.getModelo();
        this.fabricante = bd.getFabricante();
        this.anoLancamento = bd.getAnoLancamento();
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

    @Override
    public String toString() {
        return "CarrosDto{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}
