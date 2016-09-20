<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="resolution">
    <input type="text" name="resolutionName"/>
    <input type="hidden" name="action" value="add"/>
    <input type="submit" value="Add resolution"/>
</form>
</body>
</html>
