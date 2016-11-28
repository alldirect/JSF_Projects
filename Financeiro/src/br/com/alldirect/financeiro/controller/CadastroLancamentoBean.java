package br.com.alldirect.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alldirect.financeiro.model.Lancamento;
import br.com.alldirect.financeiro.model.Pessoa;
import br.com.alldirect.financeiro.model.TipoLancamento;
import br.com.alldirect.financeiro.repository.Pessoas;
import br.com.alldirect.financeiro.service.CadastroLancamentos;
import br.com.alldirect.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {
			this.todasPessoas = this.pessoas.todas();
			if(this.lancamento == null){
				this.lancamento = new Lancamento();
			}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	
	public TipoLancamento[] getTiposLancamentos(){
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
}