package com.banco.model;

public interface IConta {

     void sacar(double valor);
     void depositar(double valor);
     void transferir(double valor, IConta destino);
     void exibirExtrato();
     int getNumero();

}
