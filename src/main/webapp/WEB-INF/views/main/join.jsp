<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    .short {color:red;font-size:13px;}
    .weak {color:yellow;font-size:13px;}
    .good {color:blue;font-size:13px;}
    .strong {color:green;font-size:13px;}
</style>
<script>
$(document).ready(function(){
    $("#joinform").submit(function(){
        
        $(".error").hide();
        
        var hasError = false;
        
        //Email Validation.
        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        var emailblockReg =
            /^([\w-\.]+@(?!gmail.com)(?!yahoo.com)(?!hotmail.com)([\w-]+\.)+[\w-]{2,4})?$/;
        
        var emailaddressVal = $("#email").val();
        if(emailaddressVal == '') {
            $("#email").after('<span class="error">Please enter your email address.</span>');
            hasError = true;
        }else if(!emailReg.test(emailaddressVal)) {
            $("#email").after('<span class="error">Enter a valid email address.</span>');
            hasError = true;
        }else if(!emailblockReg.test(emailaddressVal)) {
            $("#email").after('<span class="error">No yahoo, gmail or hotmail emails.</span>');
            hasError = true;
        } 
        
        //Password Validation.
        var passwordVal = $("#password").val();
        var checkVal = $("#password-check").val();
        
        if (passwordVal == '') {
            $("#password").after('<span class="error">Please enter a password.</span>');
            hasError = true;
        } else if (checkVal == '') {
            $("#password-check").after('<span class="error">Please re-enter your password.</span>');
            hasError = true;
        } else if (passwordVal != checkVal ) {
            $("#password-check").after('<span class="error">Passwords do not match.</span>');
            hasError = true;
        }
        
        //Name Validation.
        var username = $("#username").val();
        if(username == ''){
            $("#username").after('<span class="error">Please enter a name.</span>');
            hasError = true;
        }
        
        if(hasError == true) { return false; }
    });
    
    $('#password').keyup(function(){
        $('#result').html(checkStrength($('#password').val()));
    });
    
    function checkStrength(password){
         
        //initial strength
        var strength = 0;
     
        //if the password length is less than 6, return message.
        if (password.length < 6) {
            $('#result').removeClass();
            $('#result').addClass('short');
            return 'Too short';
        }
     
        //length is ok, lets continue.
     
        //if length is 8 characters or more, increase strength value
        if (password.length > 7) strength += 1;
     
        //if password contains both lower and uppercase characters, increase strength value
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1;
     
        //if it has numbers and characters, increase strength value
        if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/))  strength += 1;
     
        //if it has one special character, increase strength value
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1;
     
        //if it has two special characters, increase strength value
        if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,",%,&,@,#,$,^,*,?,_,~])/)) strength += 1;
     
        //now we have calculated strength value, we can return messages
     
        //if value is less than 2
        if (strength < 2 ) {
            $('#result').removeClass();
            $('#result').addClass('weak');
            return 'Weak';
        } else if (strength == 2 ) {
            $('#result').removeClass();
            $('#result').addClass('good');
            return 'Good';
        } else {
            $('#result').removeClass();
            $('#result').addClass('strong');
            return 'Strong';
        }
    }
});
</script>

<form:form id="joinform" method="post" action="main/join" commandName="user">
<table>
    <tr>
        <td><form:label path="email"> <spring:message code="login.email" /> </form:label> </td>
        <td><form:input path="email" id="email"/> </td>
    <tr>
    <tr>
        <td><form:label path="password"> <spring:message code="join.password" /> </form:label> </td>
        <td><form:input path="password" id="password" type="password"/><span id="result" class="error"></span></td>
    <tr>
    <tr>
        <td><spring:message code="join.confirmPassword" /></td>
        <td><input type="password" id="password-check"/></td>
    <tr>    
    <tr>
        <td><form:label path="name"> <spring:message code="join.name" /> </form:label> </td>
        <td><form:input path="name" id="username"/> </td>
    <tr>
    <tr>
        <td colspan="2" align="center">
            <input id="submit" type="submit" value="<spring:message code="join.submit"/>" />
        </td>
    </tr>        
</table>    
</form:form>

</body>
</html>