<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Person Face Upload Form</h1>
	<div th:if="${message}">
		<p th:text="${message}"/>
	</div>
	<div>
		<form method="POST" enctype="multipart/form-data" th:action="@{/personface/add/} + ${groupId} + '/' + ${personId}" >
			<table>
			    <tr><td>Name:</td><td><input type="text" name="name" /></td></tr>
			    <tr><td>Count:</td><td><input type="text" name="count" size="3" /></td></tr>		
				<tr><td>Upload a Face url:</td><td><input type="file" name="file" /></td></tr>
				<tr><td><input type="submit" value="Upload" /></td></tr>
			</table>
		</form>
	</div>
	<div>
		<ul>
			<li th:each="file : ${files}">
				<a th:href="${file}" th:text="${file}" />
			</li>
		</ul>
	</div>
<br/> 
	<div th:if="${json}">
		<p th:text="${json}"/>
	</div>
	
	<script src="https://breakdown.blob.core.windows.net/public/vb.widgets.mediator.js"></script>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="js/site.js"></script>

</body>
</html>