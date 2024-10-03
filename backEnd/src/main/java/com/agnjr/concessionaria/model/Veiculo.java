package com.agnjr.concessionaria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Min(value = 0, message = "Quilometragem não pode ser negativa")
    private Double km;

    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotBlank(message = "Fabricante é obrigatório")
    private String fabricante;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnoreProperties("veiculos")
    private Categoria categoria;

    public Veiculo() {
    }

    public Veiculo(String nome, Double km, BigDecimal valor, String fabricante, String cor, Categoria categoria) {
        this.nome = nome;
        this.km = km;
        this.valor = valor;
        this.fabricante = fabricante;
        this.cor = cor;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;

        return Objects.equals(id, veiculo.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", km=" + km +
                ", valor=" + valor +
                ", fabricante='" + fabricante + '\'' +
                ", cor='" + cor + '\'' +
                ", categoria=" + (categoria != null ? categoria.getNome() : "null") +
                '}';
    }

}