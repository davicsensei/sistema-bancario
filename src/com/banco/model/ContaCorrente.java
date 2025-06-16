package com.banco.model;

public class ContaCorrente extends Conta{

    private double limiteChequeEspecial = 500.0;

    public ContaCorrente() {
        super();
    }

    public ContaCorrente(double limiteChequeEspecial) {
        this();
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        if (limiteChequeEspecial < 0) {
            throw new IllegalArgumentException("Limite deve ser positivo");
        }
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        if (valor > saldo + limiteChequeEspecial) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo -= valor;
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.printf("Limite cheque especial: R$ %.2f%n", limiteChequeEspecial);
    }
}
