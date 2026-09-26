package org.example.model;

public class ContaPJ extends Conta {
    private String email;
    private String nomefantasia;
    private static final double TAXA_SAQUE_PJ = 5.50;

    public ContaPJ(String razaoSocial, String cnpj, String senha, String email, String nomefantasia) {
        super(razaoSocial, cnpj, senha);
        this.email = email;
        this.nomefantasia = nomefantasia;
    }

    public ContaPJ(String razaoSocial, String cnpj) {
        super(razaoSocial, cnpj);
    }

    @Override
    public double getTaxaSaque() {
        return TAXA_SAQUE_PJ;
    }

    @Override
    public boolean sacar(double valor) {
        double valorTotal = valor + TAXA_SAQUE_PJ;

        if (valor > 0 && this.saldo >= valorTotal) {
            this.saldo -= valorTotal;
            this.extrato.add(String.format("Saque PJ: -R$ %.2f (Taxa: R$ %.2f)", valor, TAXA_SAQUE_PJ));
            System.out.printf("Saque de R$ %.2f realizado com sucesso!\n", valor);
            return true;
        }
        System.out.println("Erro: Saldo insuficiente para cobrir o saque e a taxa de R$ 5.50.");
        return false;
    }

    public String getCnpj() {
        return getCpf();
    }

    public void setCnpj(String cnpj) {
        setCpf(cnpj);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    @Override
    public String toString() {
        return "--- CONTA PESSOA JURIDICA ---\n" +
                "Nome Fantasia: " + nomefantasia +
                "\nEmail: " + email +
                "\n" + super.toString();
    }
}