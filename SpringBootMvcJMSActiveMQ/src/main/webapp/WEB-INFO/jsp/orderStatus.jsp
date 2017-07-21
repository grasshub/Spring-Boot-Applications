<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Product Order Status</title>
	<link href="<c:url value='/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

 	<div class="generic-container">
		<div class="well lead">Product Order Status</div>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Order Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.productName}</td>
					<td>${order.orderStatus}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<span class="well floatRight">
			<a href="<c:url value='/newOrder' />">Buy More</a>
		</span>
	</div>
</body>
</html>
