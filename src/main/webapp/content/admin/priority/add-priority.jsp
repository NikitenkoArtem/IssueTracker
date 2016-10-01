<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/style.css">
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="priority">
    <input type="text" name="priorityName"/>
    <input type="hidden" name="action" value="add"/>
    <input type="submit" value="Add priority"/>
</form>
</body>
</html>
