package desafiotinnova.exercicio5.dto;

import java.util.Optional;

import desafiotinnova.exercicio5.model.Brand;
import desafiotinnova.exercicio5.validation.EnumValue;

public class VehicleFilterDTO {
    @EnumValue(enumClass = Brand.class, message = "Deve ser um nome de marca válido")
    private Optional<String> marca;
    private Optional<Integer> ano;
    private Optional<String> cor;

    // Getters and setters
    public Optional<String> getMarca() {
        return marca;
    }

    public void setMarca(Optional<String> marca) {
        this.marca = marca;
    }

    public Optional<Integer> getAno() {
        return ano;
    }

    public void setAno(Optional<Integer> ano) {
        this.ano = ano;
    }

    public Optional<String> getCor() {
        return cor;
    }

    public void setCor(Optional<String> cor) {
        this.cor = cor;
    }
}
