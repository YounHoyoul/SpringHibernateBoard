<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="http://dev.iceburg.net/jquery/jqModal/jqModal.css" type="text/css" media="all" />
<script src="http://dev.iceburg.net/jquery/jqModal/jqModal.js" type="text/javascript"></script>

<script>
$(document).ready(function(){
    $("#addboard").click(function(){
        //document.location.href="/boardpage";
        $("#adddiv").show();
    });
    
    //list title click
    $("#boardtable tr").click(function(){
    	var url = $(this).attr("id");
    	//$('#jqmWindow').jqm({ajax: 'detailboard/'+boardid});
    	//$('#jqmWindow').jqmShow();
    	
    	document.location.href=url;
    	
    });
    
    //modify button click
    $("#boardtable tr td a.popup").click(function(){
        var url = $(this).attr("id");
        
        //$('#jqmWindow').jqm({ajax: url});
        //$('#jqmWindow').jqmShow();
        
        document.location.href=url;
        
        return false;
    });
    
    $("#addform").submit(function(){
    	
    	$(".error").hide();
    	var hasError = false;
    	
    	var title = $("#title").val();
    	if(title == ""){
    		hasError = true;
    		$("#title").after("<span class='error'>Please enter a title</span>");
    	}
    	
    	var content = $("#content").val();
    	if(content == ""){
    		hasError = true;
    		$("#content").after("<span class='error'>Please enter a content</span>");
    	}
    	
    	return !hasError;
    	
    });
    
    
});
</script>

<div class="jqmWindow" id="jqmWindow">Please wait...</div>

<h3><spring:message code="board.header"/></h3>
<table class="data" border="0" id="boardtable">
    <tr>
        <th width="400px"><spring:message code="boardmain.title"/></th>
        <th width="50px"><spring:message code="boardmain.hit"/></th>
        <th></th>
    </tr>
    <c:if  test="${empty boardList}">
        <tr>
            <td colspan="3"> <spring:message code="boardmain.noelements"/> </td>
        </tr>
    </c:if>
	<c:if  test="${!empty boardList}">
        <c:forEach items="${boardList}" var="board">
            <tr id="../board/detailpage/${board.id}">
                <td><a href="#">${board.title}</a></td>
                <td align="center">${board.hit}</td>
                <td>
                    <a href="#" class="popup" id="../board/modifypage/${board.id}"><spring:message code="boardmain.modify"/></a>
                    <a href="../board/delete/${board.id}" id=""><spring:message code="boardmain.delete"/></a>
                </td>
            </tr>
        </c:forEach>   
	</c:if>
</table>

<input id="addboard" type="button" value="<spring:message code="boardmain.add"/>" />

<div id="adddiv" style="display:none;">
    <form:form name="addform" id="addform" action="../board/add" method="POST">
    <table>
        <tr>
            <th width="100px"><spring:message code="boardmain.title"/></th>
            <td width="400px"><input type="text" name="title" id="title" value="" style="width:100%"></td>
        </tr>
        <tr>
            <th><spring:message code="boardmain.content"/></th>
            <td><textarea name="content" id="content" style="width:100%" rows="10"></textarea></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="<spring:message code="boardmain.add"/>"></td>
        </tr>        
    </table>
    </form:form>
</div>