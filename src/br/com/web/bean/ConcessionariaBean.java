package br.com.web.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.web.dao.DAO;
import br.com.web.modelos.Concessionaria;

@ManagedBean
@ViewScoped
public class ConcessionariaBean {

	private Concessionaria concessionaria = new Concessionaria();

	private long concessionariaId;

	public void carregarConcessionariaPelaId() {
		this.concessionaria = new DAO<Concessionaria>(Concessionaria.class).buscaPorId(concessionariaId);
	}

	public String gravar() {
		System.out.println("Salvando Concessionaria no banco de dados " + this.concessionaria.getNome());

		if (this.concessionaria.getId() == null) {
			new DAO<Concessionaria>(Concessionaria.class).adiciona(this.concessionaria);
		} else {
			new DAO<Concessionaria>(Concessionaria.class).atualiza(this.concessionaria);
		}

		this.concessionaria = new Concessionaria();

		return "concessionaria?faces-redirect=true";
	}

	public void remover(Concessionaria concessionaria) {
		System.out.println("Removendo Concessionaria do banco " + concessionaria.getNome());
		new DAO<Concessionaria>(Concessionaria.class).remove(concessionaria);
	}

	public List<Concessionaria> getConcessionarias() {
		return new DAO<Concessionaria>(Concessionaria.class).listaTodos();
	}

	public Concessionaria getConcessionaria() {
		return concessionaria;
	}

	public void setConcessionaria(Concessionaria concessionaria) {
		this.concessionaria = concessionaria;
	}
	
}
