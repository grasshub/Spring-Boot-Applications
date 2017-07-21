<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Order Confirmation Page</title>
	<link href="<c:url value='/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
	<div class="alert alert-success lead">
    	${success}
	</div>
	
	<span class="well floatRight">
		<a href="<c:url value='/newOrder' />">Buy More</a>
	</span>
	
	<span class="well floatRight">
		<a href="<c:url value='/checkStatus' />">Check Status</a>
	</span>
	
</div>
</body>

</html>
