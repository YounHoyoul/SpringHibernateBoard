<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(document).ready(function(){
	$("#join").click(function(){
		document.location.href="joinpage";
	});
	
	$("#loginform").submit(function(){
		
		$(".error").hide();
		var hasError = false;
		
		var email = $("#email").val();
		if(email == ''){
			hasError = true;
			$("#email").after("<span class='error'>please enter your email</span>");
		}
		
		var password = $("#password").val();
        if(password == ''){
            hasError = true;
            $("#password").after("<span class='error'>please enter your password</span>");
        }
        
		return !hasError;
	});
});
</script>
<form:form id="loginform" method="post" action="main/login" commandName="user">
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