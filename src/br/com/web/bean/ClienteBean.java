package br.com.web.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.web.dao.DAO;
import br.com.web.modelos.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean {

	private Cliente cliente = new Cliente();

	private long clienteId;

	public void carregarClientePelaId() {
		this.cliente = new DAO<Cliente>(Cliente.class).buscaPorId(clienteId);
	}

	public String gravar() {
		System.out.println("Salvando Cliente no banco de dados " + this.cliente.getNome());

		if (this.cliente.getId() == null) {
			new DAO<Cliente>(Cliente.class).adiciona(this.cliente);
		} else {
			new DAO<Cliente>(Cliente.class).atualiza(this.cliente);
		}

		this.cliente = new Cliente();

		return "cliente?faces-redirect=true";
	}

	public void remover(Cliente cliente) {
		System.out.println("Removendo Cliente do banco " + cliente.getNome());
		new DAO<Cliente>(Cliente.class).remove(cliente);
	}

	public List<Cliente> getClientes() {
		return new DAO<Cliente>(Cliente.class).listaTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
