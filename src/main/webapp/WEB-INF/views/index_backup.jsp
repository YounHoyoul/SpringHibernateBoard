<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#join").click(function(){
		document.location.href="joinpage";
	});
});
</script>
</head>
<body>

<form:form method="post" action="login" commandName="user">
<table>
	<tr>
		<td><form:label path="email"><spring:message code="login.email"/></form:label></td>
		<td><form:input path="email" id="email"/></td>
	</tr>
	<tr>
		<td><form:label path="password"><spring:message code="login.password"/></form:label></td>
		<td><form:input path="password" id="password" type="password"/></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input id="submit" type="submit" value="<spring:message code="login.submit"/>" />
			<input id="join" type="button" value="<spring:message code="login.join"/>" />
		</td>
	</tr>
</table>

</form:form>

</body>
</html>