package br.com.web.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.web.dao.DAO;
import br.com.web.modelos.Carro;

@ManagedBean
@ViewScoped
public class CarroBean {

	private Carro carro = new Carro();

	private long carroId;

	public void carregarCarroPelaId() {
		this.carro = new DAO<Carro>(Carro.class).buscaPorId(carroId);
	}

	public String gravar() {
		System.out.println("Salvando Carro no banco de dados " + this.carro.getModelo());

		if (this.carro.getId() == null) {
			new DAO<Carro>(Carro.class).adiciona(this.carro);
		} else {
			new DAO<Carro>(Carro.class).atualiza(this.carro);
		}

		this.carro = new Carro();

		return "carro?faces-redirect=true";
	}

	public void remover(Carro carro) {
		System.out.println("Removendo Carro do banco " + carro.getModelo());
		new DAO<Carro>(Carro.class).remove(carro);
	}

	public List<Carro> getCarros() {
		return new DAO<Carro>(Carro.class).listaTodos();
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
}
