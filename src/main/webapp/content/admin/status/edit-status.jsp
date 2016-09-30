<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="status">
    <input type="text" name="statusName" value="${status.statusName}"/>
    <input type="hidden" name="statusId" value="${status.statusId}"/>
    <input type="hidden" name="action" value="edit"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>
