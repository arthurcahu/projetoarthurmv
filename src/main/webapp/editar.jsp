<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bem vindos ao café da manhã MV</title>
<link rel="icon" href="imagens/cafe.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar participante</h1>
	<form name="frmAdicionar" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="caixa2"readonly value ="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"value =" <%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="number" name="cpf" class="Caixa1" value ="<%out.print(request.getAttribute("cpf"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="contribuicao" class="Caixa1" value ="<%out.print(request.getAttribute("contribuicao"));%>">
				<td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>