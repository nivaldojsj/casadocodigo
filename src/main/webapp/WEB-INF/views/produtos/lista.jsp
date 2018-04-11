<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e Muito mais -
	Casa Do Código</title>
</head>
<body>
	<h1>Lista de produtos</h1>
	<div>${sucesso}</div>
	
	<table>
		<tr>
			<td>Titulo</td>
			<td>Descrição</td>
			<td>Páginas</td>
		</tr>
		
		<c:forEach items="${produtos}" var="produto" >
			<tr>
				<td>       
					<a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build() }">
						${produto.titulo}
					</a>
				</td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>