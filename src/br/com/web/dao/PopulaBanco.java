package br.com.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.web.modelos.Carro;
import br.com.web.modelos.Cliente;
import br.com.web.modelos.Concessionaria;
import br.com.web.modelos.Endereco;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Endereco enderecoCli = geraEndereco("Rua floriano", "200", "Centenario", "Campina Grande", "PB");
		Endereco enderecoCon = geraEndereco("Avenida brasilia", "4512", "Catole", "Campina Grande", "PB");
		em.persist(enderecoCli);
		em.persist(enderecoCon);

		Cliente cliente1 = geraCliente("Wagner", "12345678910", enderecoCli);
		em.persist(cliente1);

		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);

		Carro carro1 = geraCarro("Corsa classic", 2000, 13000.00);
		em.persist(carro1);
		Carro carro2 = geraCarro("SW4", 2017, 80000.00);
		em.persist(carro2);
		Carro carro3 = geraCarro("New Civic", 2016, 50000.00);
		em.persist(carro3);

		List<Carro> carros = new ArrayList<Carro>();
		carros.add(carro1);
		carros.add(carro2);
		carros.add(carro3);

		Concessionaria concessionaria1 = geraConcessionaria("WDL car", enderecoCon, carros, clientes);
		em.persist(concessionaria1);

		em.getTransaction().commit();
		em.close();

	}

	private static Cliente geraCliente(String nome, String cpf, Endereco endereco) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		return cliente;
	}

	private static Carro geraCarro(String modelo, int ano, double valor) {
		Carro carro = new Carro();
		carro.setModelo(modelo);
		carro.setAno(ano);
		carro.setValor(valor);
		return carro;
	}

	private static Endereco geraEndereco(String rua, String numero, String bairro, String cidade, String uf) {
		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setUf(uf);
		return endereco;
	}

	private static Concessionaria geraConcessionaria(String nome, Endereco endereco, List<Carro> carros,
			List<Cliente> clientes) {
		Concessionaria concessionaria = new Concessionaria();
		concessionaria.setNome(nome);
		concessionaria.setEndereco(endereco);
		concessionaria.setCarros(carros);
		concessionaria.setClientes(clientes);
		return concessionaria;
	}
}