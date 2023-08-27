package br.com.bank.model;


public class Conta {

	private double saldo;
	private String cpf;

	public Conta(String cpf, double saldo) {
		this.cpf = cpf;
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getCpf() {
		return cpf;
	}
}
