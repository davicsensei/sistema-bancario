package com.banco.model;

public class Conta {
    private int idConta;
    private String titular;
    private String email;
    private double saldo = 0;

    public Conta (int idConta, String titular, String email){
        this.idConta = idConta;
        this.titular = titular;
        this.email = email;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getEmail() {
        return email;
    }

    public String getTitular() {
        return titular;
    }

    public int getIdConta() {
        return idConta;
    }

    public void sacar(double valor){
        if(valor <= saldo) {
            saldo -= valor;
        }
    }

    public void depositar(double valor){
        if(valor > 0){
        saldo += valor;
        }
    }

    public void transferir(double valor, Conta contaDestino){

    }


}
