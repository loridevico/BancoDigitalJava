package com.bancodigital;

public class Main {

    public static void main(String[] args) {
        Cliente venilton = new Cliente("Venilton");
        Cliente maria = new Cliente("Maria Silva");
        Cliente joao = new Cliente("Joao Pedro");

        Conta cc = new ContaCorrente(venilton);
        Conta poupanca = new ContaPoupanca(venilton);

        Conta ccMaria = new ContaCorrente(maria);

        Conta cpJoao = new ContaPoupanca(joao);

        System.out.println("\n=== Depósito ===");
        cc.depositar(100.00);      // Depósito válido
        poupanca.depositar(50.00); // Depósito válido
        ccMaria.depositar(1500.75); // Depósito válido
        cc.depositar(-20.00);      // Depósito inválido (negativo)
        ccMaria.depositar(0.00);   // Depósito inválido (zero)

        System.out.println("\n=== Saque ===");
        cc.sacar(20.00);           // Saque válido
        poupanca.sacar(100.00);    // Saque maior que o saldo (erro)
        ccMaria.sacar(500.00);     // Saque válido
        ccMaria.sacar(-50.00);     // Saque inválido (negativo)

        System.out.println("\n=== Transferência ===");
        cc.transferir(50.00, poupanca);

        ccMaria.transferir(200.00, cc);
        ccMaria.transferir(2000.00, cpJoao);

        cc.transferir(-10.00, poupanca);

        System.out.println("\n=== Extratos Finais ===");
        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
        ccMaria.imprimirExtrato();
        cpJoao.imprimirExtrato();

        System.out.println("\n=== Gerenciamento do Banco ===");
        Banco meuBanco = new Banco();
        meuBanco.adicionarConta(cc);
        meuBanco.adicionarConta(poupanca);
        meuBanco.adicionarConta(ccMaria);
        meuBanco.adicionarConta(cpJoao);

        meuBanco.listarContas();
    }
}