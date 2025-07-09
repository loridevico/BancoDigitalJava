package com.bancodigital;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("ERRO: O valor do saque deve ser positivo.");
        } else if (this.saldo < valor) {
            System.out.println("ERRO: Saldo insuficiente. Saldo atual: R$ " + String.format("%.2f", this.saldo));
        } else {
            saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado. Novo saldo: R$ %.2f%n", valor, this.saldo);
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("ERRO: O valor do depósito deve ser positivo.");
        } else {
            saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado. Novo saldo: R$ %.2f%n", valor, this.saldo);
        }
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (valor <= 0) {
            System.out.println("ERRO: O valor da transferência deve ser positivo.");
            return;
        }

        if (this.saldo < valor) {
            System.out.println("ERRO: Saldo insuficiente para transferência. Saldo atual: R$ " + String.format("%.2f", this.saldo));
            return;
        }

        this.sacar(valor);
        contaDestino.depositar(valor);
        System.out.printf("Transferência de R$%.2f para conta %d (%s) realizada com sucesso.%n",
                valor, ((Conta)contaDestino).getNumero(), ((Conta)contaDestino).getCliente().getNome());
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
    }
}