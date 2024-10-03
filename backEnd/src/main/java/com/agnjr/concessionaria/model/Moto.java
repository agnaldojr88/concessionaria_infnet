package com.agnjr.concessionaria.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Moto extends Veiculo {

    @NotNull(message = "Cilindradas são obrigatórias")
    @Min(value = 50, message = "Cilindradas devem ser maior que 50cc")
    private int cilindradas;

    public Moto() {
    }

    public Moto(String nome, Double km, BigDecimal valor, String fabricante, String cor, Categoria categoria,
                int cilindradas) {
        super(nome, km, valor, fabricante, cor, categoria);
        this.cilindradas = cilindradas;
    }

    public int getCilindradas() {
        return cilindradas;
    }

    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Moto{" +
                "cilindradas=" + cilindradas +
                '}';
    }
}