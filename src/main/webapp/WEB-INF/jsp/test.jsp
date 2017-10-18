<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="createTweet" method="post">
		firstName:<input name="firstName" type="text"/> 
		<br/>
		lastName:<input name="lastName" type="text"/> 
		<input type="submit" value="Submit" />
	</form>
	<c:out value="${secondTestObject}"/>
	<c:out value="${sessionObj1}"/>
	

</body>
</html>