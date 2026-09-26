package org.example.model;

public class ContaPF extends Conta {
    private boolean contaAtiva;
    private static final double TAXA_SAQUE_PF = 1.50;

    public ContaPF(String titular, String cpf, String senha, boolean contaAtiva) {
        super(titular, cpf, senha);
        this.contaAtiva = contaAtiva;
    }

    public ContaPF(String titular, String cpf) {
        super(titular, cpf);
        this.contaAtiva = true;
    }

    @Override
    public double getTaxaSaque() {
        return TAXA_SAQUE_PF;
    }

    @Override
    public boolean sacar(double valor) {
        double valorTotal = valor + TAXA_SAQUE_PF;

        if (valor > 0 && this.saldo >= valorTotal) {
            this.saldo -= valorTotal;
            this.extrato.add(String.format("Saque PF: -R$ %.2f (Taxa: R$ %.2f)", valor, TAXA_SAQUE_PF));
            System.out.printf("Saque de R$ %.2f realizado com sucesso!\n", valor);
            return true;
        }
        System.out.println("Erro: Saldo insuficiente para cobrir o saque e a taxa de R$ 1.50.");
        return false;
    }

    public boolean isContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(boolean contaAtiva) {
        this.contaAtiva = contaAtiva;
    }

    @Override
    public String toString() {
        return "--- CONTA PESSOA FISICA ---\n" +
                super.toString() +
                "\nConta Ativa: " + contaAtiva;
    }
}