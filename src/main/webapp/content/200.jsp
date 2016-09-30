<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf"%>
<h3>Command complete successfully</h3>
<form method="get" action="${servlet}">
    <input type="hidden" name="action" value="goBack"/>
    <input type="submit" value="OK"/>
</form>
</body>
</html>
