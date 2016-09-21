<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<%@include file="/navigation.jspf" %>
<table>
    <thead>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="roleId" value="${roles.roleId}"/>
    <c:forEach var="resolution" items="${users}">
        <tr>
            <td name="email"><a href="/user?email=${resolution.email}">${resolution.email}</a></td>
            <td name="firstName">${resolution.firstName}</td>
            <td name="lastName">${resolution.lastName}</td>
            <c:if test="${resolution.role == roleId}">
                <td>${resolution.roleName}</td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>