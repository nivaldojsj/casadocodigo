<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Livros de Java, Android, iPhone, PHP, Ruby e Muito mais -
	Casa Do Código</title>
</head>
<body>

	<form:form action="${s:mvcUrl('PC#gravar').build() }" method="post"
		modelAttribute="produto" enctype="multipart/form-data">
		<div>
			<label>Titulo</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />
		</div>

		<div>
			<label>Descrição</label>
			<form:textarea cols="20" rows="10" path="descricao" />
			<form:errors path="descricao" />
		</div>

		<div>
			<label>Páginas</label>
			<form:input path="paginas" cols="20" rows="10" />
			<form:errors path="paginas" />
		</div>

        <div>
            <label>Data de lançamento</label>
             <form:input path="dataLancamento" />
            <form:errors path="dataLancamento" />
        </div>


        <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
            <div>
                <label>${tipoPreco}</label> 
                <form:input
                    path="precos[${status.index}].valor" />
                 <form:input type="hidden"
                    path="precos[${status.index}].tipo" value="${tipoPreco}" />
            </div>
        </c:forEach>
 
 		<div>
 			<label>Sumário</label>
 			<input name="sumario" type="file">
 		</div>
 
		<button type="submit">Cadastrar</button>
	</form:form>

</body>
</html>