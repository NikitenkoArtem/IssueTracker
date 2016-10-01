<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf"%>
<table border="1px">
    <thead>
    <tr>
        <td>Type name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="type" items="${types}">
        <tr>
            <td><a href="/type?typeId=${type.typeId}">${type.typeName}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
