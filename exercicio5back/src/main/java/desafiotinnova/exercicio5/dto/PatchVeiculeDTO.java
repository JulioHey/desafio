package desafiotinnova.exercicio5.dto;



import java.util.Optional;

import desafiotinnova.exercicio5.model.Brand;
import desafiotinnova.exercicio5.validation.EnumValue;
import desafiotinnova.exercicio5.validation.ValidAno;

public class PatchVeiculeDTO {


    private Optional<String> veiculo;
    
    @EnumValue(enumClass = Brand.class, message = "Deve ser um nome de marca válido")
    private Optional<String> marca;

    @ValidAno
    private Optional<Integer> ano;

    private Optional<String> descricao;

    private Optional<String> cor;

    private Optional<Boolean> vendido;


    public PatchVeiculeDTO() {}

    public PatchVeiculeDTO( Optional<String> veiculo, Optional<String> marca, Optional<Integer> ano, Optional<String> descricao, Optional<String> cor, Optional<Boolean> vendido) {
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.cor = cor;
    }

    public Optional<String> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Optional<String> veiculo) {
        this.veiculo = veiculo;
    }

    public Optional<String> getMarca() {
        return marca;
    }

    public void setMarca(Optional<String> marca) {
        this.marca = marca;
    }

    public  Optional<Integer>  getAno() {
        return ano;
    }

    public void setAno( Optional<Integer>  ano) {
        this.ano = ano;
    }

    public Optional<String> getDescricao() {
        return descricao;
    }

    public void setDescricao(Optional<String> descricao) {
        this.descricao = descricao;
    }

    public Optional<String> getCor() {
        return cor;
    }

    public void setCor(Optional<String> cor) {
        this.cor = cor;
    }

    public  Optional<Boolean> isVendido() {
        return vendido;
    }

    public void setVendido( Optional<Boolean> vendido) {
        this.vendido = vendido;
    }
}

