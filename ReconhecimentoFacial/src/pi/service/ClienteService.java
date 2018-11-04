package pi.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pi.dao.AzureDAO;
import pi.dao.ClienteDAO;
import pi.entity.Cliente;

@Service
public class ClienteService {
	ClienteDAO clienteDAO = new ClienteDAO();
	AzureDAO azureDAO = new AzureDAO();
	
	@Autowired
	public ClienteService(ClienteDAO cliDAO)
	{
		clienteDAO = cliDAO;
	}

	/**
	 * InserirPessoa - Esse método insere a pessoa no azure, recebe o codAzure e
	 * insere os dados da pessoa no banco.
	 * 
	 * @param pessoa
	 *            - recebe um objeto pessoa da view.
	 * @return pessoa - retorna um objeto pessoa atualizado com o cod azure
	 * @throws IOException
	 */
	@Transactional
	public Cliente inserirCliente(Cliente cliente) throws IOException
	{
		cliente.setPersonId(azureDAO.insertCliente(cliente.getNome()));
		System.out.println("Código de pessoa no Azure: " + cliente.getPersonId());
		int id = clienteDAO.inserirCliente(cliente);
		cliente.setId(id);
		return cliente;
	}


	/**
	 * inserirFotoPessoa - Esse método recebe 1 foto (endereço em string) e envia
	 * para o azure
	 * 
	 * @param pessoa
	 * @param foto
	 *            - Endereço da foto
	 * @throws IOException
	 */
	public void inserirFotoClienteFile (Cliente cliente, File foto) throws IOException{
		azureDAO.inserirClienteFotoFile(cliente.getPersonId(), (cliente.getNome()), foto);;
	}
	


	/**
	 * treinarAPI - Esse método faz a API da azure treinar. Reconhece as fotos já
	 * carregadas e aumenta a precisão da identificação.
	 */
	public void trainAPI()
	{
		azureDAO.training();
	}

	/**
	 * identificarPessoa - Esse método recebe o endereço de uma foto. Chama o
	 * detecta pessoa que realiza a detecção da foto e retorna um ID. O ID é
	 * utilizado no indentify para reconhecer as pessoas parecidas em um grupo.
	 * 
	 * @param urlFoto
	 *            - Foto selecionada para fazer a identificação.	
	 */
	public void identifyCliente(String urlFoto)
	{
		azureDAO.identifyCliente(azureDAO.detectCliente(urlFoto));

	}

	@Transactional
	public Cliente updateCliente(Cliente cliente) throws IOException
	{
		clienteDAO.atualizarCliente(cliente);
		return cliente;
	}

	@Transactional
	public void excluirCliente(Cliente cliente) throws IOException
	{
		clienteDAO.removerCliente(cliente);
	}

	public Cliente buscarCliente(int id) throws IOException
	{
		return clienteDAO.buscarCliente(id);
	}

	public List<Cliente> listarCliente(String chave) throws IOException
	{
		return clienteDAO.listarCliente(chave);
	}

	public List<Cliente> listarClientes() throws IOException
	{
		return clienteDAO.listarClientes();
	}


}
