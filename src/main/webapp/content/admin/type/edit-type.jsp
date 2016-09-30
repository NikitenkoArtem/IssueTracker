<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="type">
    <input type="text" name="typeName" value="${type.typeName}"/>
    <input type="hidden" name="typeId" value="${type.typeId}"/>
    <input type="hidden" name="action" value="edit"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>
