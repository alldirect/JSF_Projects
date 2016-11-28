package br.com.alldirect.financeiro.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alldirect.financeiro.model.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	private String nomeUsuario;
	private String senha;

	public String login() {

		System.out.print("login(): " + this.nomeUsuario);
		System.out.println(" - " + this.senha);

		FacesContext context = FacesContext.getCurrentInstance();
		if ("admin".equals(this.nomeUsuario) && "admin".equals(this.senha)) {
			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());
			return "/ConsultaLancamentos?faces-redirect=true";
		} else {
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
		System.out.print("setNome: " + nomeUsuario + " ");
		System.out.println("this: " + this.nomeUsuario);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
		System.out.print("setSenha: " + senha + " ");
		System.out.println("this: " + this.senha);
	}
}