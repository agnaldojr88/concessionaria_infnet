package com.agnjr.concessionaria.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Caminhao extends Veiculo {

    @NotNull(message = "Rodado é obrigatório")
    @Min(value = 1, message = "Rodado deve ser pelo menos 4")
    private Integer rodado;

    @NotNull(message = "TipoCabine é obrigatório")
    @Min(value = 1, message = "TipoCabine deve ser maior que 1")
    private Integer tipoCabine;

    public Caminhao() {
    }

    public Caminhao(String nome, Double km, BigDecimal valor, String fabricante, String cor, Categoria categoria,
                    int rodado, int tipoCabine) {
        super(nome, km, valor, fabricante, cor, categoria);
        this.rodado = rodado;
        this.tipoCabine = tipoCabine;
    }

    public int getRodado() {
        return rodado;
    }

    public void setRodado(int rodado) {
        this.rodado = rodado;
    }

    public int getTipoCabine() {
        return tipoCabine;
    }

    public void setTipoCabine(int tipoCabine) {
        this.tipoCabine = tipoCabine;
    }

    @Override
    public String toString() {
        return super.toString() +", Caminhao{" +
                "rodado=" + rodado +
                ", tipoCabine=" + tipoCabine +
                '}';
    }
}