package testando.retorno;

import pi.entity.Cliente;

public class TestandoRetorno {
	public static void main(String[] args) {
		
		// Fiz essa classe apenas para retornar o teste de PersonIds
		Cliente cCliente = new Cliente();
		System.out.println(cCliente.getPersonId());
		
		// Vai retornar nulo porque é o campo do formulário que está vazio.
		System.out.println(cCliente.getNome());
	}

}
