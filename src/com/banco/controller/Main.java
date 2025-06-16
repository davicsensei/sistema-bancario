package com.banco.controller;

import com.banco.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static List<IConta> contas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 7) {
            mostrarMenu();
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    exibirExtratos();
                    break;
                case 6:
                    aplicarJuros();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n==== MENU ====");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Exibir extratos");
        System.out.println("6 - Aplicar juros (poupança)");
        System.out.println("7 - Sair");
        System.out.print("Digite sua opção: ");
    }

    private static void criarConta() {
        System.out.println("\n-- Criar Conta --");
        System.out.print("Tipo de conta (1 - Corrente, 2 - Poupança): ");
        int tipo = sc.nextInt();
        sc.nextLine();
        Conta conta;
        if (tipo == 1) {
            System.out.print("Limite do cheque especial (padrão 500): ");
            double limite = sc.nextDouble();
            sc.nextLine();
            if(limite <= 0) limite = 500.0;
            conta = new ContaCorrente(limite);
        } else if (tipo == 2) {
            System.out.print("Taxa de juros em % (padrão 0.5): ");
            double taxa = sc.nextDouble();
            sc.nextLine();
            if(taxa <= 0) taxa = 0.5;
            conta = new ContaPoupanca(taxa / 100);
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }
        contas.add(conta);
        Conta c = conta;
        System.out.println("Conta criada com sucesso! Agência: " + c.getAgencia() + ", Número: " + c.getNumero());
    }

    private static IConta encontrarConta(int numero) {
        for (IConta c : contas) {
            if (c.getNumero() == numero) {
                return c;
            }
        }
        return null;
    }

    private static void depositar() {
        System.out.println("\n-- Depositar --");
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();
        sc.nextLine();
        IConta conta = encontrarConta(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        System.out.print("Valor a depositar: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        try {conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sacar() {
        System.out.println("\n-- Sacar --");
        System.out.print("Número da conta: ");
        int numero = sc.nextInt();
        sc.nextLine();
        IConta conta = encontrarConta(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }
        System.out.print("Valor a sacar: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        try {
            conta.sacar(valor);
            System.out.println("Saque realizado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transferir() {
        System.out.println("\n-- Transferir --");
        System.out.print("Número da conta de origem: ");
        int origemNum = sc.nextInt();
        sc.nextLine();
        IConta origem = encontrarConta(origemNum);
        if (origem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }
        System.out.print("Número da conta de destino: ");
        int destinoNum = sc.nextInt();
        sc.nextLine();
        IConta destino = encontrarConta(destinoNum);
        if (destino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }
        System.out.print("Valor a transferir: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        try {
            origem.transferir(valor, destino);
            System.out.println("Transferência realizada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibirExtratos() {
        System.out.println("\n-- Extratos de todas as contas --");
        contas.forEach(IConta::exibirExtrato);
    }

    private static void aplicarJuros() {
        System.out.println("\n-- Aplicar juros em conta poupança --");
        System.out.print("Número da conta poupança: ");
        int numero = sc.nextInt();
        sc.nextLine();
        IConta conta = encontrarConta(numero);
        if (conta instanceof ContaPoupanca poupanca) {
            poupanca.renderJuros();
            System.out.println("Juros aplicados com sucesso.");
        } else {
            System.out.println("Conta não encontrada ou não é uma poupança.");
        }
    }
}
