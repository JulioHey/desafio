package desafiotinnova.exercicio5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
// import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "veiculos")
public class Vehicle { // Renamed from Veicule

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Veiculo name cannot be empty")
    @Size(min = 2, max = 50, message = "Veiculo name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String veiculo;

    @NotNull(message = "Marca cannot be null")
    @Enumerated(EnumType.STRING)
    private Brand marca;

    @Min(value = 1886, message = "Year must be greater than or equal to 1886")
    @Max(value = 2025, message = "Year must be less than or equal to 2025")
    @Column(nullable = false)
    private int ano;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Boolean vendido; // Changed to Boolean

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created; // Changed to LocalDateTime

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated; // Changed to LocalDateTime

    // Default constructor
    public Vehicle() {}

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

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
}
