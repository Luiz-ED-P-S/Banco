# 🏦 Sistema Bancário em Java

> Projeto desenvolvido para praticar conceitos de Programação Orientada a Objetos (POO) em Java, implementando um modelo de contas bancárias para Pessoa Física e Pessoa Jurídica.

---

## 🚀 Tecnologias Utilizadas
* **Java** (versão 17 ou superior recomendada)
* **Maven** (Gerenciador de dependências)
* **IntelliJ IDEA** (Ambiente de desenvolvimento)

---

## 📂 Estrutura do Projeto
O projeto segue a arquitetura padrão Maven, organizado em pacotes:
```text
Banco-master/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── model/
│                   │   ├── Conta.java      (Classe base abstrata)
│                   │   ├── ContaPF.java    (Conta para Pessoa Física)
│                   │   └── ContaPJ.java    (Conta para Pessoa Jurídica)
│                   └── Main.java           (Classe principal de execução)
├── pom.xml
└── README.md
