<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/style.css">
</head>
<body>
<%@include file="/navigation.jspf" %>
<table border="1px">
    <thead>
    <tr>
        <td>Priority name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="priority" items="${priorities}">
        <tr>
            <td><a href="/priority?priorityId=${priority.priorityId}">${priority.priorityName}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
