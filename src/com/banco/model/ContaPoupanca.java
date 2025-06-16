package com.banco.model;

public class ContaPoupanca extends Conta {

    private double taxaJuros = 0.005; // 0.5%

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(double taxaJuros) {
        this();
        this.taxaJuros = taxaJuros;
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(double taxaJuros) {
        if (taxaJuros < 0) {
            throw new IllegalArgumentException("Taxa deve ser positiva");
        }
        this.taxaJuros = taxaJuros;
    }

    public void renderJuros() {
        saldo += saldo * taxaJuros;
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.printf("Taxa de juros: %.2f%%%n", taxaJuros * 100);
    }
}
