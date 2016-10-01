<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<table border="1px">
    <thead>
    <tr>
        <th>Status name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="status" items="${statuses}">
        <tr>
            <td><a href="/status?statusId=${status.statusId}">${status.statusName}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
