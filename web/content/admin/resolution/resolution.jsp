<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<table>
    <thead>
    <tr>
        <th>Resolution name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="resolution" items="${resolutions}">
        <tr>
            <td><a href="/resolution?resolutionId=${resolution.resolutionId}">${resolution.resolutionName}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>