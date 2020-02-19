<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Corsi</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="corsi2.css">
</head>
<body>
	<div class="row container-fluid">
		<h1>
			<a href="index.html">Green School</a>
		</h1>
	</div>
	<div class="row container-fluid">
		<div class="col-lg-2">
			<h2>...</h2>
		</div>
		<div class="col-lg-10">
			<table>
				<tr>
					<th>Titolo</th>
					<th>Durata</th>
					<th>Numero Partecipanti</th>
					<th>Costo</th>
					<th>Descrizione</th>
				</tr>
				<c:forEach var="course" items="${courses}">
					<tr>
						<td>${course.titolo}</td>
						<td>${course.durata}</td>
						<td>${course.maxPartecipanti}</td>
						<td>${course.costo}</td>
						<td>${course.descrizione}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>