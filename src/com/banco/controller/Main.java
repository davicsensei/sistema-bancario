package com.banco.controller;

import com.banco.model.*;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Maria Souza", "123.456.789-00");

        IConta contaCorrente = new ContaCorrente();
        IConta contaPoupanca = new ContaPoupanca();

        cliente.adicionarConta(contaCorrente);
        cliente.adicionarConta(contaPoupanca);

        contaCorrente.depositar(1000.0);
        contaCorrente.sacar(200.0);
        contaCorrente.transferir(300.0, (Conta) contaPoupanca);
        System.out.println("-- Extrato após operações na Conta Corrente e transferência --");
        contaCorrente.exibirExtrato();

        contaPoupanca.depositar(500.0);
        contaPoupanca.sacar(100.0);
        System.out.println("-- Extrato após operações na Conta Poupança --");
        contaPoupanca.exibirExtrato();

        System.out.println("\n-- Extratos de todas as contas do cliente --");
        cliente.exibirExtratos();
    }
}