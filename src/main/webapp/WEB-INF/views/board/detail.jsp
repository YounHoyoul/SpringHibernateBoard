<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    #board_table {width:500px;}
    #comment_table {width:500px;}
    #comment_input_table {width:500px;}
    #comment {width:500px;}
    th {font-weight:bold;}
</style>

<script>
	$(document).ready(function(){
		$("#btn_back").click(function(){
			document.location.href="../../board/listpage";
		});
		
		$("#comment_form").submit(function(){
			
			$(".error").hide();
	        var hasError = false;
	        
	        var comment = $("#comment").val();
	        if(comment == ''){
	        	hasError = true;
	        	$("#comment").after("<span class='error'>please enter a comment.</span>");
	        }
	        
	        var password = $("#password").val();
            if(password == ''){
                hasError = true;
                $("#password").after("<span class='error'>please enter a password.</span>");
            }
            
	        return !hasError;
	    });
	
	});
</script>

<input type="button" id="btn_back" value="<spring:message code="boardmain.back"/>">

<table id="board_table">
    <tr><th width="50px"><spring:message code="boardmain.title"/></th>
        <td>${board.title}</td>
    </tr>
    <tr><th><spring:message code="boardmain.content"/></th>
        <td>${board.content}</td>
    </tr>
    <tr><th><spring:message code="boardmain.hit"/></th>
        <td>${board.hit}</td>
    </tr>
    <tr><th><spring:message code="boardmain.writer"/></th>
        <td>${user.name}</td>
    </tr>
</table>

Comments
<hr>
<c:if test="${empty comments}">
No Comments
</c:if>

<c:if test="${!empty comments}">
	<table id="comment_table">
	    <c:forEach items="${comments}" var="comment">
	        <tr>
	            <td>${comment.comment}</td>
	            <td>${comment.user.name}</td>
	        </tr>
	    </c:forEach>
	</table>
</c:if>

<form name="comment_form" id="comment_form" action="../addcomment" method="POST">
    <input type="hidden" name="boardid" value="${board.id}" />
	<table id="comment_input_table">
	    <tr>
	        <td colspan="2"><textarea name="comment" id="comment"></textarea></td>
	    </tr>
	    <tr>    
	        <td><input type="password" name="password" id="password" /></td>
	        <td><input type="submit" id="btn_add_comment" value="<spring:message code="boardmain.add"/>"></td>
	    </tr>
	</table>
</form>