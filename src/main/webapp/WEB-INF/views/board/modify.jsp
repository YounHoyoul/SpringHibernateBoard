<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
$(document).ready(function(){
	$("#back").click(function(){
		document.location.href="../../board/listpage";
	});
	
});

</script>

<form:form commandName="board" method="post" action="../../board/modify/${board.id}">
<form:hidden path="id"/>
<form:hidden path="userId"/>
<form:hidden path="hit"/>
<table width="500px">
    <tr>
        <th width="100px"><form:label path="title"><spring:message code="boardmain.title"/></form:label></th>
        <td width="400px"><form:input path="title" style="width:100%"/></td>
    </tr>
    <tr>
        <th><form:label path="content"><spring:message code="boardmain.content"/></form:label></th>
        <td><form:textarea path="content" style="width:100%" rows="10"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="boardmain.modify"/>">
            <input type="button" id="back" value="<spring:message code="boardmain.back"/>">
        </td>
    </tr>       
    
</table>
</form:form>

<!-- <a href="#" class="jqmClose">Click me</a> -->
