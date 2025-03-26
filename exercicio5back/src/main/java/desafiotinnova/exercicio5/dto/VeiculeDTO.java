package desafiotinnova.exercicio5.dto;

import java.time.LocalDateTime;

import desafiotinnova.exercicio5.model.Brand;
import desafiotinnova.exercicio5.validation.EnumValue;
import jakarta.validation.constraints.*;

public class VeiculeDTO {

    private Long id;

    @NotBlank(message = "Nome do veículo deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do veículo deve ter entre 2 e 50 caracteres")
    private String veiculo;
    
    @EnumValue(enumClass = Brand.class, message = "Deve ser um nome de marca válido")
    private String marca;

    @Min(value = 1886, message = "O ano deve ser maior ou igual a 1886")
    @Max(value = 2025, message = "O ano deve ser menor ou igual a 2025")
    private int ano;

    @Size(min = 5, max = 500, message = "A descrição deve ter entre 5 e 500 caracteres")
    private String descricao;

    @NotNull(message = "A cor do veículo deve ser informada")
    private String cor;

    @NotNull(message = "O status de vendido não pode ser nulo")
    private boolean vendido;

    private LocalDateTime created;
    private LocalDateTime updated;

    public VeiculeDTO() {}

    public VeiculeDTO(Long id, String veiculo, String marca, int ano, String descricao, String cor, boolean vendido, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
