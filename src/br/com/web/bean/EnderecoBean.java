package br.com.web.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.web.dao.DAO;
import br.com.web.modelos.Endereco;

@ManagedBean
@ViewScoped
public class EnderecoBean {

	private Endereco endereco = new Endereco();

	private long enderecoId;

	public void carregarEnderecoPelaId() {
		this.endereco = new DAO<Endereco>(Endereco.class).buscaPorId(enderecoId);
	}

	public String gravar() {
		System.out.println(
				"Salvando Endereco no banco de dados " + this.endereco.getRua() + "," + this.endereco.getNumero() + "\n"
						+ this.endereco.getBairro() + ", " + this.endereco.getCidade() + " - " + this.endereco.getUf());

		if (this.endereco.getId() == null) {
			new DAO<Endereco>(Endereco.class).adiciona(this.endereco);
		} else {
			new DAO<Endereco>(Endereco.class).atualiza(this.endereco);
		}

		this.endereco = new Endereco();

		return "endereco?faces-redirect=true";
	}

	public void remover(Endereco endereco) {
		System.out.println(
				"Salvando Endereco no banco de dados " + this.endereco.getRua() + "," + this.endereco.getNumero() + "\n"
						+ this.endereco.getBairro() + ", " + this.endereco.getCidade() + " - " + this.endereco.getUf());
		new DAO<Endereco>(Endereco.class).remove(endereco);
	}

	public List<Endereco> getEnderecos() {
		return new DAO<Endereco>(Endereco.class).listaTodos();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
