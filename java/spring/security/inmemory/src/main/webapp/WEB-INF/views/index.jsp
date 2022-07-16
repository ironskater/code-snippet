<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Main page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<link rel="stylesheet"
				type="text/css"
				href="${pageContext.request.contextPath}/resources/css/index.css">
		<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
	</head>
	<body>
		<p>${greeting}</p>
        <a href="<c:url value="/logout" />">Logout</a><br>
		<a href="hello/showForm">Hello world form</a><br>
		<input type="buttom" onclick="doSomeWork()" value="Click me plz"><br>

		<!-- ${pageContext.request.contextPath} is JSP expression,
			which access the correct root directory for my web application -->
		<img src="${pageContext.request.contextPath}/resources/image/panda.jpg"
			width="20%"
			height="20%">
	</body>
</html>