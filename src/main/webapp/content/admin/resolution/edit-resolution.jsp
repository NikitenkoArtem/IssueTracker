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
    <table>

    </table>
    <input type="text" name="resolutionName" value="${resolution.resolutionName}"/>
    <input type="hidden" name="resolutionId" value="${resolution.resolutionId}"/>
    <input type="hidden" name="action" value="edit"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>