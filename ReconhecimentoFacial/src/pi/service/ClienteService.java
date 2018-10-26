package pi.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pi.dao.ClienteDAO;
import pi.entity.Cliente;

@Service
public class ClienteService {
	private ClienteDAO dao;
	
	@Autowired
	public ClienteService(ClienteDAO fdao) {
		dao = fdao;
	}
	
	public Cliente buscarCliente(int id) throws IOException{
		return dao.buscarCliente(id);
	}
	
	@Transactional
	public Cliente inserirCliente(Cliente cliente) throws IOException {
		int id = dao.inserirCliente(cliente);
		cliente.setId(id);
		return cliente;
	}
	
	@Transactional
	public void excluirCliente(Cliente cliente) throws IOException {
		dao.removerCliente(cliente);
	}
	
	@Transactional
	public void atualizarCliente(Cliente cliente) throws IOException {
		dao.atualizarCliente(cliente);
	}

	public List<Cliente> listarClientes(String chave) throws IOException{
		return dao.listarCliente(chave);
	}

	public List<Cliente> listarClientes() throws IOException{
		return dao.listarClientes();
	}

}
