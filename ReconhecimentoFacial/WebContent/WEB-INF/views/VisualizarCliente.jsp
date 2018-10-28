<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <!DOCTYPE html>
        <html lang="pt-br">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Visualizar Cliente</title>

            <link href="css/bootstrap.min.css" rel="stylesheet">
            <link href="css/style.css" rel="stylesheet">
        </head>

        <body>
                <!-- Modal -->
                <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="modalLabel">Excluir Cliente</h4>
                            </div>
                            <div class="modal-body">
                                Deseja realmente excluir este cliente?
                            </div>
                            <div class="modal-footer">
                                <form action="excluir_cliente" method="post">
                                    <input type="hidden" name="id" value="${cliente.id}" />
                                    <button type="submit" class="btn btn-primary" name="acao" value="Excluir">Sim</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.modal -->
                <!-- Barra superior com os menus de navegação -->
				<c:import url="Menu.jsp"/>
                <!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">${cliente.id} - ${cliente.nome }</h3>
		<div class="row">
			
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-12">
						<p><strong>CPF:&nbsp;</strong>${cliente.cpf }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>RG:&nbsp;</strong>${cliente.rg }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>E-Mail:&nbsp;</strong>${cliente.email }</p>
					</div>
				</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>ID IMAGEM:&nbsp;</strong>${cliente.idImagem }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Endereço:&nbsp;</strong>${cliente.endereco }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>CEP:&nbsp;</strong>${cliente.cep }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Tipo Logradouro:&nbsp;</strong>${cliente.tipoLogradouro }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Número:&nbsp;</strong>${cliente.numero }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Bairro:&nbsp;</strong>${cliente.bairro }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Cidade:&nbsp;</strong>${cliente.cidade }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Estado:&nbsp;</strong>${cliente.estado }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>País:&nbsp;</strong>${cliente.pais }</p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<p><strong>Telefone:&nbsp;</strong>${cliente.telefone }</p>
					</div>
				</div>
			</div>
		</div>
		<hr />
		<div id="actions" class="row">
			<div class="col-md-12">
				<a href="alterar_cliente?id=${cliente.id}" class="btn btn-primary">Editar</a> 
				<a href="#" class="btn btn-danger" data-toggle="modal" data-target="#delete-modal">Excluir</a> 
				<a href="listar_clientes" class="btn btn-default">Voltar</a>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>