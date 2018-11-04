package pi.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pi.dao.AzureDAO;
import pi.entity.Cliente;
import pi.service.ClienteService;

@Controller
public class ManterClienteController {
	@Autowired
	private ClienteService clienteService;

	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		return "ListarClientes";
	}

	@RequestMapping("/")
	public String iniciar() {
		return "index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/novo_cliente")
	public String criarCliente() {
		return "CriarCliente";
	}

	/* NÃO ALTERAR
	@Transactional
	@RequestMapping("/inserirCliente")
	public String inserirCliente(@Valid Cliente cliente, BindingResult erros, Model model) {
		try {
			if (!erros.hasErrors()) {
				// Inserindo cliente no banco
				cliente = clienteService.inserirCliente(cliente);
				// Inserindo imagens da pessoa na API
				clienteService.inserirFotoClienteFile(cliente, "C://Pessoas/Ronaldinho/ronaldinho1.jpg");
				clienteService.insertPhotoCliente(cliente, "C://Pessoas/Ronaldinho/ronaldinho2.jpg");
				clienteService.insertPhotoCliente(cliente, "C://Pessoas/Ronaldinho/ronaldinho3.jpg");
				// Treinando API após inserção de imagens
				clienteService.trainAPI();
				// Identifica pessoa a partir de uma imagem
				clienteService.identifyCliente("C://Pessoas/Ronaldinho/ronaldinho4.jpg");
				// Manda o objeto pessoa atualizado para o model
				model.addAttribute("cliente", cliente);
				return "Resultado";
			} else {
				return "CriarCliente";
			}
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	*/

	// NÃO ALTERAR
		@Transactional
		@RequestMapping("/inserirClienteFoto64")
		public String criarClienteFoto(@Valid Cliente cliente, @RequestParam(required=false,name="file") String stringFoto, BindingResult erros,
				Model model) {
			try {
				if (!erros.hasErrors()) {
					
					// Insere pessoa no banco
					cliente = clienteService.inserirCliente(cliente);
					
					// Prepara a string do base64
					String base64Image = stringFoto.split(",")[1];
					// This will decode the String which is encoded by using Base64 class
					byte[] imageByte = Base64.decodeBase64(base64Image);

					// salva imagem no servidor
					String directory = "C:\\Users\\dietk\\Desktop\\fotos" + cliente.getNome() + ".png";

					// Convertendo Tipo de file
					File foto = new File(directory);
					foto.createNewFile();
					FileOutputStream fos = new FileOutputStream(foto);
					fos.write(imageByte);
					fos.close();
					
					
					// Inserindo imagens da pessoa na API
					clienteService.inserirFotoClienteFile(cliente, foto);
					// Treinando API após inserção de imagens
					clienteService.trainAPI();
					// Manda o objeto pessoa atualizado para o model
					model.addAttribute("cliente", cliente);
					return "Resultado";
				} else {
					return "CriarCliente";
				}
			} catch (IOException e) {
				e.printStackTrace();
				model.addAttribute("erro", e);
				return "Erro";
			}
		}

	/*	// TESTE
		@RequestMapping("/pegaFoto64")
		public String pegaFoto64( Cliente cliente, @RequestParam(required=false,name="file") String string64, BindingResult erros, Model model) {
			try {
				if (!erros.hasErrors()) {
					// Prepara a string do base64
					String base64Image = string64.split(",")[1];
					// This will decode the String which is encoded by using Base64 class
					byte[] imageByte = Base64.decodeBase64(base64Image);
					// salva imagem no servidor
					String directory = "C:/teste/" + cliente.getNome() + ".png";
					// Convertendo Tipo de file
					File foto = new File(directory);
					foto.createNewFile();
					FileOutputStream fos = new FileOutputStream(foto);
					fos.write(imageByte);
					fos.close();
					clienteService.identifyCliente(foto);
					System.out.println(cliente.getNome());
					return "Resultado";
				} else {
					System.out.println(cliente.getNome());
					
					return "ruim";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("erro", e);
				return "Erro";
			}
		}

	*/
		
		@RequestMapping("/identifica")
		public String identifica() {
			return "PegaFotos";
		}

	
	/**
	 * InserirPessoa - Insere uma pessoa no banco de dados e também na Azure.
	 * Sequencialmente, acontece: 1 - É inserido o endereço da pessoa, retornando o
	 * ID 2 - Inserida uma pessoa no BD e em seguida Azure, dentro do grupo de
	 * pessoas "grupopi"
	 * 
	 * @param Pessoa, Endereço
	 * @return Objeto de pessoa em JSON
	 * @throws IOException
	 */

	@RequestMapping(method = RequestMethod.POST, value = "rest/cliente", headers = "Accept=application/json")
	public @ResponseBody Cliente insertCliente(@RequestBody Cliente cliente, Model model) throws IOException {

		// Insere a pessoa no BD e na Azure
		cliente = clienteService.inserirCliente(cliente);

		// Adiciona Pessoa como um atributo do model
		model.addAttribute("cliente", cliente);

		return cliente;
	}

	/**
	 * BuscarPessoaPorId - Busca uma pessoa no banco pelo ID
	 * 
	 * @param ID de pessoa
	 * @return Objeto de pessoa em JSON
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "rest/cliente/{id}")
	public @ResponseBody Cliente findClienteForId(@PathVariable("id") int id, Model model) throws IOException {
		try {
			Cliente cliente = clienteService.buscarCliente(id);
			model.addAttribute("cliente", cliente);
			return cliente;

		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * ListarPessoas - Lista todas as pessoas cadastradas
	 * 
	 * @param none
	 * @return Lista de Pessoas
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "rest/cliente")
	public @ResponseBody List<Cliente> listarClientes(Model model) throws IOException {
		try {
			List<Cliente> cliente = clienteService.listarClientes();
			model.addAttribute("cliente", cliente);
			return cliente;

		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * AtualizarPessoa - Altera os dados de uma pessoa
	 * 
	 * @param Objeto Pessoa
	 * @return Objeto de pessoa em JSON
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "rest/cliente", headers = "Accept=application/json")
	public @ResponseBody Cliente updateCliente(@RequestBody Cliente cliente, Model model) throws IOException {
		try {
			Cliente cliente1 = clienteService.updateCliente(cliente);
			model.addAttribute("cliente", cliente1);
			return cliente1;

		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * DeletarPessoa - Altera os dados de uma pessoa
	 * 
	 * @param Id
	 * @return Mensagem de sucesso
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "rest/cliente/{id}")
	public @ResponseBody String removerCliente(@PathVariable("id") Cliente cliente, Model model) throws IOException {
		try {
			clienteService.excluirCliente(cliente);

		} catch (IOException e) {
			throw e;
		}
		return "Cliente removido!";
	}

	/**
	 * IdentificaPessoa - Identifica uma pessoa na Azure por uma foto (URL) Detalhe:
	 * sequencialmente, acontece: 1 - O grupo de pessoas "grupopi" é treinado. 2 - É
	 * acionado o método detectaPessoaURL passando a foto como parâmetro e
	 * retornando o FaceID 3 - É acionado o método identificaPessoa que retorna as
	 * pessoas prováveis que tenham este rosto
	 * 
	 * @param Foto
	 * @return Lista de pessoas prováveis que tenham este rosto
	 * @throws IOException
	 */

	@RequestMapping(method = RequestMethod.POST, value = "rest/cliente/identifica", headers = "Accept=application/json")
	public @ResponseBody String identifyCliente(@RequestBody String photo, Model model) throws IOException {
		try {
			// Instancia o AzureDAO e aciona método que treina o grupo
			AzureDAO azureDAO = new AzureDAO();
			azureDAO.training();

			// Aqui é acionado o IdentificaPessoa que precisa de um FaceID, obtido através
			// do DetectaPessoa
			// IMPORTANTE: ajustar para receber a foto ou array de fotos, o que for
			// necessario
			String testeFoto = photo;
			String response = azureDAO.identifyCliente(azureDAO.detectClienteComUrl(testeFoto));

			return response;

		} catch (Exception e) {
			throw e;
		}

	}

}
