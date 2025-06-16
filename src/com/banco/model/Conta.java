package com.banco.model;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    private int agencia;
    private  int numero;
    private double saldo;

    public Conta(){
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo");
        }
    }
    @Override
    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente ou valor inválido");
        }
    }

    @Override
    public void transferir(double valor, IConta destino){
        this.sacar(valor);
        destino.depositar(valor);
    }

    @Override
    public void exibirExtrato() {
        System.out.println("\n=== Extrato Conta " + this.getClass().getSimpleName() +" ===");
        imprimirInfosComuns();
    }


    public int getAgencia() {
        return agencia;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns(){
        System.out.printf("Agencia: %d%n",this.agencia);
        System.out.printf("Número: %d%n",this.numero);
        System.out.printf("Saldo: R$ %.2f%n", this.saldo);
    }
}



