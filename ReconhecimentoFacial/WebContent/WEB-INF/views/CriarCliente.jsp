<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cadastro de Pessoas</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>
<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">
		<h3 class="page-header">Novo Cliente</h3>
		<!-- Formulario para inclusao de clientes -->
		<form action="createClientePhoto" method="post"
			enctype="multipart/form-data">
			<!-- area de campos do form -->
			<div class="row">
				<div class="form-group col-md-3">
					<label for="nome">Nome</label>
					<form:errors path="cliente.nome" cssStyle="color:red" />
					<input type="text" class="form-control" name="nome" id="nome"
						maxlength="100" placeholder="nome">
				</div>
				<div class="form-group col-md-3">
					<label for="cpf">CPF</label>
					<form:errors path="cliente.cpf" cssStyle="color:red" />
					<input type="text" class="form-control" name="cpf" id="cpf"
						maxlength="12" placeholder="cpf">
				</div>
				<div class="form-group col-md-3">
					<label for="RG">RG</label>
					<form:errors path="cliente.rg" cssStyle="color:red" />
					<input type="text" class="form-control" name="rg" id="rg"
						maxlength="12" placeholder="rg">
				</div>
				<div class="form-group col-md-3">
					<label for="EMAIL">Email</label>
					<form:errors path="pessoa.email" cssStyle="color:red" />
					<input type="text" class="form-control" name="email" id="email"
						maxlength="60" placeholder="email">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
					<label for="telefone">Telefone</label>
					<form:errors path="cliente.telefone" cssStyle="color:red" />
					<input type="text" class="form-control" name="telefone"
						id="telefone" maxlength="20" placeholder="telefone">
				</div>
				<div class="form-group col-md-8">
					<label for="ENDERECO">Endereço</label>
					<form:errors path="cliente.endereco" cssStyle="color:red" />
					<input type="text" class="form-control" name="endereco"
						id="endereco" maxlength="300" placeholder="endereco">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-5">
					<label for="CEP">CEP</label>
					<form:errors path="cliente.cep" cssStyle="color:red" />
					<input type="text" class="form-control" name="cep" id="cep"
						maxlength="10" placeholder="cep">
				</div>
				<div class="form-group col-md-5">
					<label for="tipoLogradouro">Logradouro</label>
					<form:errors path="cliente.tipoLogradouro" cssStyle="color:red" />
					<input type="text" class="form-control" name="tipoLogradouro"
						id="tipoLogradouro" maxlength="10" placeholder="tipoLogradouro">
				</div>
				<div class="form-group col-md-2">
					<label for="numero">Numero</label>
					<form:errors path="cliente.numero" cssStyle="color:red" />
					<input type="text" class="form-control" name="numero" id="numero"
						maxlength="10" placeholder="numero">
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-3">
					<label for="bairro">Bairro</label>
					<form:errors path="cliente.bairro" cssStyle="color:red" />
					<input type="text" class="form-control" name="bairro" id="bairro"
						maxlength="100" placeholder="bairro">
				</div>
				<div class="form-group col-md-3">
					<label for="cidade">Cidade</label>
					<form:errors path="cliente.cidade" cssStyle="color:red" />
					<input type="text" class="form-control" name="cidade" id="cidade"
						maxlength="100" placeholder="cidade">
				</div>
				<div class="form-group col-md-3">
					<label for="estado">UF</label>
					<form:errors path="cliente.estado" cssStyle="color:red" />
					<input type="text" class="form-control" name="estado" id="estado"
						maxlength="2" placeholder="UF">
				</div>
				<div class="form-group col-md-3">
					<label for="pais">País</label>
					<form:errors path="cliente.pais" cssStyle="color:red" />
					<input type="text" class="form-control" name="pais" id="pais"
						maxlength="100" placeholder="pais">
				</div>
			</div>

			<div class="row">
				<div hidden="form-group col-md-4">
					<label for="personId">PersonId</label> <input type="text"
						class="form-control" name="cliente.personId" id="personId"
						placeholder="personId">
				</div>
			</div>

			<!-- inclusao da foto -->
			<div class="row">
				<div class="form-group col-md-3">
					<video id="video" width="300" height="300" autoplay></video>
					<input type="button" id="snap" title="Capturar Foto"
						value="Salvar Foto">
					<canvas id="canvas" width="640" height="480"></canvas>
					<script type="text/javascript">
						// Grab elements, create settings, etc.
						var video = document.getElementById('video');
						// Get access to the camera!
						if (navigator.mediaDevices
								&& navigator.mediaDevices.getUserMedia) {
							// Not adding `{ audio: true }` since we only want video now
							navigator.mediaDevices.getUserMedia({
								video : true
							}).then(function(stream) {
								video.src = window.URL.createObjectURL(stream);
								video.play();
							});
						}
						// Elements for taking the snapshot
						var canvas = document.getElementById('canvas');
						var context = canvas.getContext('2d');
						var video = document.getElementById('video');
						// Trigger photo take
						document.getElementById("snap")
								.addEventListener(
										"click",
										function() {
											context.drawImage(video, 0, 0, 300,
													300);
											var input = document
													.createElement('input');
											input.type = "hidden";
											input.name = "file";
											input.value = canvas.toDataURL();
											document.getElementById("foto")
													.appendChild(input);
										});
					</script>
				</div>
				<div id="foto">
					<form:errors path="file" cssStyle="color:red" />
				</div>
			</div>
			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary" name="acao"
						value="criar">Salvar</button>
					<a href="index" class="btn btn-default">Cancelar</a>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>