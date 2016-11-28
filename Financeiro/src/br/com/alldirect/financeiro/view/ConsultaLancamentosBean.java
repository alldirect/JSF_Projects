package br.com.alldirect.financeiro.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.alldirect.financeiro.model.Lancamento;
import br.com.alldirect.financeiro.repository.Lancamentos;
import br.com.alldirect.financeiro.service.CadastroLancamentos;
import br.com.alldirect.financeiro.service.NegocioException;

@Named
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentosRepository;

	private Lancamento lancamentoSelecionado;
	
	@Inject
	private CadastroLancamentos cadastro;

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	private List<Lancamento> lancamentos;

	public void consultar() {
		this.lancamentos = lancamentosRepository.todos();
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}