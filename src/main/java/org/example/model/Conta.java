package org.example.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    protected String titular;
    protected String cpf;
    protected String senha;
    protected double saldo;
    protected List<String> extrato;

    public Conta() {
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public Conta(String titular, String cpf) {
        this.titular = titular;
        this.cpf = cpf;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public Conta(String titular, String cpf, String senha) {
        this.titular = titular;
        this.cpf = cpf;
        this.senha = senha;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
        this.extrato.add("Conta criada com sucesso - Saldo inicial: R$ 0.00");
    }

    public boolean validarSenha(String senhaDigitada) {
        return this.senha != null && this.senha.equals(senhaDigitada);
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            this.extrato.add(String.format("Deposito: +R$ %.2f", valor));
            System.out.printf("Deposito de R$ %.2f realizado com sucesso!\n", valor);
        } else {
            System.out.println("Erro: Valor invalido.");
        }
    }

    public abstract double getTaxaSaque();

    public abstract boolean sacar(double valor);

    public boolean transferir(Conta contaDestino, double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            contaDestino.saldo += valor;
            this.extrato.add(String.format("Transferencia para %s: -R$ %.2f", contaDestino.getTitular(), valor));
            contaDestino.extrato.add(String.format("Transferencia recebida de %s: +R$ %.2f", this.titular, valor));
            System.out.printf("Transferencia de R$ %.2f para %s concluida!\n", valor, contaDestino.getTitular());
            return true;
        }
        System.out.println("Erro: Saldo insuficiente para transferencia.");
        return false;
    }

    public void exibirSaldo() {
        System.out.printf("Saldo atual: R$ %.2f\n", this.saldo);
    }

    public void exibirExtrato() {
        System.out.println("\n=== EXTRATO BANCARIO ===");
        for (String transacao : extrato) {
            System.out.println(transacao);
        }
        System.out.println("------------------------");
        System.out.printf("Saldo atual: R$ %.2f\n", this.saldo);
        System.out.println("========================");
    }

    @Override
    public String toString() {
        return "Titular: " + titular +
                "\nCPF/CNPJ: " + cpf +
                "\nSaldo: R$ " + String.format("%.2f", saldo);
    }
}