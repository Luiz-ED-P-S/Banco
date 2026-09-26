package org.example;

import org.example.model.Conta;
import org.example.model.ContaPF;
import org.example.model.ContaPJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.println("=== CADASTRO DA CONTA BANCARIA ===");
        System.out.println("Escolha o tipo de conta:");
        System.out.println("1 - Pessoa Fisica (PF)");
        System.out.println("2 - Pessoa Juridica (PJ)");
        System.out.print("Opcao: ");

        int tipoConta = 0;
        try {
            tipoConta = leitor.nextInt();
            leitor.nextLine();
        } catch (Exception e) {
            System.out.println("Opcao invalida. Definindo como Pessoa Fisica por padrao.");
            leitor.nextLine();
            tipoConta = 1;
        }

        Conta minhaConta = null;

        if (tipoConta == 2) {
            System.out.print("Digite a Razao Social (Titular): ");
            String razaoSocial = leitor.nextLine();

            System.out.print("Digite o Nome Fantasia: ");
            String nomeFantasia = leitor.nextLine();

            System.out.print("Digite o Email: ");
            String email = leitor.nextLine();

            String cnpj = lerDocumento(leitor, "CNPJ (14 digitos)", 14);
            String senha = lerSenha(leitor);

            minhaConta = new ContaPJ(razaoSocial, cnpj, senha, email, nomeFantasia);

        } else {
            System.out.print("Digite o seu nome (Titular): ");
            String nome = leitor.nextLine();

            String cpf = lerDocumento(leitor, "CPF (11 digitos)", 11);
            String senha = lerSenha(leitor);

            minhaConta = new ContaPF(nome, cpf, senha, true);
        }

        System.out.println("\n=== BEM VINDO AO BANCO MASTER! ===");

        List<Conta> destinatarios = new ArrayList<>();
        destinatarios.add(new ContaPF("Professor Alex", "11111111111"));
        destinatarios.add(new ContaPJ("Empresa Agiota LTDA", "22222222000199"));
        destinatarios.add(new ContaPF("Ana Silva", "33333333333"));
        destinatarios.add(new ContaPJ("Mercado Central S/A", "44444444000188"));

        int opcao = 0;
        int tentativasIncorretas = 0;

        do {
            System.out.println("\n===== MENU DE OPCOES =====");
            System.out.println("1 - Ver Dados da Conta");
            System.out.println("2 - Ver Saldo");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Ver Extrato");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = leitor.nextInt();

                if (opcao >= 3 && opcao <= 6) {
                    leitor.nextLine();
                    System.out.print("Confirme sua senha (4 digitos) para continuar: ");
                    String senhaDigitada = leitor.nextLine();

                    if (!minhaConta.validarSenha(senhaDigitada)) {
                        tentativasIncorretas++;
                        System.out.println("Senha incorreta! Tentativas restantes: " + (3 - tentativasIncorretas));

                        if (tentativasIncorretas >= 3) {
                            System.out.println("CONTA BLOQUEADA POR SEGURANCA! Sistema encerrado.");
                            break;
                        }
                        continue;
                    } else {
                        tentativasIncorretas = 0;
                    }
                }

                switch (opcao) {
                    case 1:
                        System.out.println("\n--- DADOS DA CONTA ---");
                        System.out.println(minhaConta);
                        break;

                    case 2:
                        minhaConta.exibirSaldo();
                        break;

                    case 3:
                        System.out.print("Qual valor voce deseja depositar? R$ ");
                        double valorDeposito = leitor.nextDouble();
                        minhaConta.depositar(valorDeposito);
                        break;

                    case 4:
                        System.out.println("\n--- OPERACAO DE SAQUE ---");
                        System.out.printf("AVISO: Esta operacao possui uma taxa de R$ %.2f para sua modalidade de conta.\n", minhaConta.getTaxaSaque());
                        System.out.print("Qual valor voce deseja sacar? R$ ");
                        double valorSaque = leitor.nextDouble();

                        minhaConta.sacar(valorSaque);
                        break;

                    case 5:
                        System.out.print("Qual valor deseja transferir? R$ ");
                        double valorTransf = leitor.nextDouble();

                        System.out.println("\n--- ESCOLHA O DESTINATARIO ---");
                        for (int i = 0; i < destinatarios.size(); i++) {
                            System.out.println((i + 1) + " - " + destinatarios.get(i).getTitular() + " (" + destinatarios.get(i).getCpf() + ")");
                        }
                        System.out.print("Digite o numero do destinatario: ");
                        int escolha = leitor.nextInt();

                        if (escolha >= 1 && escolha <= destinatarios.size()) {
                            Conta contaEscolhida = destinatarios.get(escolha - 1);
                            minhaConta.transferir(contaEscolhida, valorTransf);
                        } else {
                            System.out.println("Destinatario invalido! Transferencia cancelada.");
                        }
                        break;

                    case 6:
                        minhaConta.exibirExtrato();
                        break;

                    case 7:
                        System.out.println("Encerrando o sistema... Obrigado, " + minhaConta.getTitular() + "!");
                        break;

                    default:
                        System.out.println("Opcao invalida! Digite um numero de 1 a 7.");
                }

            } catch (Exception erro) {
                System.out.println("Erro: Por favor, digite APENAS NUMEROS no menu!");
                leitor.nextLine();
                opcao = 0;
            }

        } while (opcao != 7);

        leitor.close();
    }

    private static String lerDocumento(Scanner leitor, String tipoDoc, int tamanhoEsperado) {
        String doc;
        do {
            System.out.print("Digite o seu " + tipoDoc + ": ");
            doc = leitor.nextLine();
            if (doc.length() != tamanhoEsperado) {
                System.out.println("Erro: O " + tipoDoc + " deve conter exatamente " + tamanhoEsperado + " digitos.");
            }
        } while (doc.length() != tamanhoEsperado);

        return doc;
    }

    private static String lerSenha(Scanner leitor) {
        String senha;
        do {
            System.out.print("Digite a sua senha (4 digitos): ");
            senha = leitor.nextLine();
            if (senha.length() != 4) {
                System.out.println("Erro: A senha deve conter exatamente 4 digitos.");
            }
        } while (senha.length() != 4);

        return senha;
    }
}   