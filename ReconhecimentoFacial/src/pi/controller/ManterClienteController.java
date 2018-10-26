package pi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.azure.service.FaceService;
import pi.entity.Cliente;
import pi.service.ClienteService;

@Controller
public class ManterClienteController {
	@Autowired
	private ClienteService cService;
	
	private FaceService fService;

	@RequestMapping("index")
	public String iniciar() {
		return "index";
	}

	@RequestMapping("/novo_cliente")
	public String novo(Model model, HttpSession session) {
		return "CriarCliente";
	}

	@RequestMapping("/criar_cliente")
	public String criarCliente(@Valid Cliente cliente, BindingResult erros, Model model) {
		try {
			if (!erros.hasErrors()) {
				
				cliente = cService.inserirCliente(cliente);
				fService.createPerson(cliente.getNome());
				
				model.addAttribute("cliente", cliente);

				return "EnviarFoto";
			}
			return "CriarCliente";

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		return "ListarClientes";
	}

	@RequestMapping("/listar_clientes")
	public String listarClientes(HttpSession session, Model model, String chave) {
		try {
			// HttpSession session = ((HttpServletRequest) model).getSession();

			List<Cliente> lista;
			if (chave != null && chave.length() > 0) {
				lista = cService.listarClientes(chave);
			} else {
				lista = cService.listarClientes();
			}
			session.setAttribute("lista", lista);
			return "ListarClientes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/visualizar_cliente")
	public String visualizarCliente(Cliente cliente, Model model) {
		try {
			cliente = cService.buscarCliente(cliente.getId());
			model.addAttribute("cliente", cliente);
			return "VisualizarCliente";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/excluir_cliente")
	public String excluirCliente(Cliente cliente, HttpSession session, Model model) {
		try {
			cService.excluirCliente(cliente);
			List<Cliente> clientes = (List<Cliente>) session.getAttribute("lista");
			session.setAttribute("lista", removerDaLista(cliente, clientes));
			return "ListarClientes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	private List<Cliente> removerDaLista(Cliente cliente, List<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			if (cliente.getId() == clientes.get(i).getId()) {
				clientes.remove(i);
				break;
			}
		}
		return clientes;
	}

	private List<Cliente> atualizarDaLista(Cliente cliente, List<Cliente> clientes) {
		for (int i = 0; i < clientes.size(); i++) {
			if (cliente.getId() == clientes.get(i).getId()) {
				clientes.remove(i);
				clientes.add(i, cliente);
				break;
			}
		}
		return clientes;
	}

	@RequestMapping("/alterar_cliente")
	public String atualizar(Cliente cliente, Model model, HttpSession session) {
		try {

			cliente = cService.buscarCliente(cliente.getId());
			model.addAttribute("cliente", cliente);
			return "AtualizarCliente";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/atualizar_cliente")
	public String gravarAtualizacaoEndereco(@Valid Cliente cliente, BindingResult erros, Model model,
			HttpSession session) {
		try {
			if (!erros.hasErrors()) {

				cService.atualizarCliente(cliente);

				model.addAttribute("cliente", cliente);
				List<Cliente> clientes = (List<Cliente>) session.getAttribute("lista");
				session.setAttribute("lista", atualizarDaLista(cliente, clientes));

				return "VisualizarCliente";
			} else {
				return "AtualizarCliente";
			}
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

}
