package br.com.alldirect.financeiro.util;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.alldirect.financeiro.model.Lancamento;
import br.com.alldirect.financeiro.model.Pessoa;
import br.com.alldirect.financeiro.model.TipoLancamento;

public class CriaLancamentos {
	public static void main(String[] args) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2013,10,1,0,0,0);
		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2013,12,1,0,0,0);
		
		Pessoa cliente = new Pessoa();
		cliente.setNome("WWW Industria de Alimentos");
		Pessoa fornecedor = new Pessoa();
		fornecedor.setNome("SoftBRAX Treinamentos");
		
		Lancamento lancamento1 = new Lancamento();
		lancamento1.setDescricao("Venda de Licença de Software");
		lancamento1.setPessoa(cliente);
		lancamento1.setDataVencimento(dataVencimento1.getTime());
		lancamento1.setDataPagamento(dataVencimento1.getTime());
		lancamento1.setValor(new BigDecimal(103_000));
		lancamento1.setTipo(TipoLancamento.RECEITA);
		
		Lancamento lancamento2 = new Lancamento();
		lancamento2.setDescricao("Venda de suporte anual");
		lancamento2.setPessoa(cliente);
		lancamento2.setDataVencimento(dataVencimento1.getTime());
		lancamento2.setDataPagamento(dataVencimento1.getTime());
		lancamento2.setValor(new BigDecimal(15_000));
		lancamento2.setTipo(TipoLancamento.RECEITA);
		
		Lancamento lancamento3 = new Lancamento();
		lancamento3.setDescricao("Treinamento de equipe");
		lancamento3.setPessoa(fornecedor);
		lancamento3.setDataVencimento(dataVencimento1.getTime());
		lancamento3.setValor(new BigDecimal(68_000));
		lancamento3.setTipo(TipoLancamento.DESPESA);
		
		manager.persist(cliente);
		manager.persist(fornecedor);
		manager.persist(lancamento1);
		manager.persist(lancamento2);
		manager.persist(lancamento3);
		
		trx.commit();
		manager.close();
	}
}