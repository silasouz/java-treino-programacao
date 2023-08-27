package br.com.bank.service;

import br.com.bank.model.Banco;
import br.com.bank.exceptions.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;



public class SistemaBancario {

	private Bacen bacen;

	public SistemaBancario(Bacen bacen) {
		super();
		this.bacen = bacen;
	}

	public long registrarBanco(Banco banco) {
		try {
			return bacen.cadastrarBanco(banco);
		} catch (Exception e) {
			throw new BancoNaoCadastradoException(e.getMessage());
		}
	}
}
