<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="register" method="post">
		<input name="username" type="text"> <input type="submit"
			value="Submit" />

	</form>
	<form action="login" method="post">
		<input name="username" type="text"> <input type="submit"
			value="Submit" />

	</form>
	<c:out value="${user.id}" />
	<c:out value="${user.name}" />
	

</body>
</html>