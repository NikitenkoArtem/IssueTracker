<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf"%>
<table>
    <thead>
    <tr>
        <td>Type name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="resolution" items="types">
        <tr>
            <td><a href="/type?typeId=${resolution.typeId}">${resolution.typeName}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
