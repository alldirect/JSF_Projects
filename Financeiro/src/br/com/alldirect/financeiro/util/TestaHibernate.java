package br.com.alldirect.financeiro.util;

import java.util.List;

import javax.inject.Inject;

import br.com.alldirect.financeiro.model.Lancamento;
import br.com.alldirect.financeiro.repository.Lancamentos;

public class TestaHibernate {
	
	@Inject
	private Lancamentos lancamentosRepository;
	
	private static List<Lancamento> lancamentos;

	public static void main(String[] args) {
		for (Lancamento l : lancamentos) {
			System.out.println(l.getDescricao());
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public Lancamentos getLancamentosRepository() {
		return lancamentosRepository;
	}
}