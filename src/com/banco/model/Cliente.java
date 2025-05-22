package com.banco.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private List<IConta> contas = new ArrayList<>();

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void adicionarConta(IConta conta) {
        contas.add(conta);
    }

    public List<IConta> getContas() {
        return contas;
    }

    public void exibirExtratos() {
        contas.forEach(IConta::exibirExtrato);
    }
}
