package desafiotinnova.exercicio5.dto;

import desafiotinnova.exercicio5.model.Brand;

import java.util.Date;

public class VeiculeDTO {

    private Long id;
    private String veiculo;
    private Brand marca;
    private int ano;
    private String descricao;
    private boolean vendido;
    private Date created;
    private Date updated;

    // Default constructor
    public VeiculeDTO() {}

    // Parameterized constructor
    public VeiculeDTO(Long id, String veiculo, Brand marca, int ano, String descricao, boolean vendido, Date created, Date updated) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
    }

    // Getters and Setters
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

    public Brand getMarca() {
        return marca;
    }

    public void setMarca(Brand marca) {
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

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}
