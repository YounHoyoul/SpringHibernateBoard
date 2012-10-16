<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.24/themes/base/jquery-ui.css" type="text/css" media="all" />
<link rel="stylesheet" href="http://dev.iceburg.net/jquery/jqModal/jqModal.css" type="text/css" media="all" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.8.24/jquery-ui.min.js" type="text/javascript"></script>
<script src="http://dev.iceburg.net/jquery/jqModal/jqModal.js" type="text/javascript"></script>

<style>
    body {font-size:13px}
    .error {color:red;}
</style>

</head>
<body>
<!-- START BODY -->

<tiles:insertAttribute name="body" />

<!-- END BODY -->
</body>
</html>