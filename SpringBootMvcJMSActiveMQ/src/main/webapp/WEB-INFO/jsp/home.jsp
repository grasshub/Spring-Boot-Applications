<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Product Order System</title>
	<link href="<c:url value='/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/css/app.css' />" rel="stylesheet"></link>
</head>

<body>

	<div class="generic-container">
		<div class="well lead">Welcome to ActiveMQ [With JMS Magic]</div>
		<span class="well floatRight">
			<a href="<c:url value='/newOrder' />">Place an order</a>
		</span>
	</div>

</body>

</html>
