package com.agnjr.concessionaria.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Carro extends Veiculo {

    @NotNull(message = "Campo rebaixado é obrigatório")
    private Boolean rebaixado;

    @NotNull(message = "Campo leilao é obrigatório")
    private Boolean leilao;

    @NotNull(message = "Campo dePasseio é obrigatório")
    private Boolean dePasseio;

    public Carro() {
    }

    public Carro(String nome, Double km, BigDecimal valor, String fabricante, String cor, Categoria categoria,
                 boolean rebaixado, boolean leilao, boolean dePasseio) {
        super(nome, km, valor, fabricante, cor, categoria);
        this.rebaixado = rebaixado;
        this.leilao = leilao;
        this.dePasseio = dePasseio;
    }

    public boolean isRebaixado() {
        return rebaixado;
    }

    public void setRebaixado(boolean rebaixado) {
        this.rebaixado = rebaixado;
    }

    public boolean isLeilao() {
        return leilao;
    }

    public void setLeilao(boolean leilao) {
        this.leilao = leilao;
    }

    public boolean isDePasseio() {
        return dePasseio;
    }

    public void setDePasseio(boolean dePasseio) {
        this.dePasseio = dePasseio;
    }

    @Override
    public String toString() {
        return super.toString() + ", Carro{" +
                "rebaixado=" + rebaixado +
                ", leilao=" + leilao +
                ", dePasseio=" + dePasseio +
                '}';
    }
}