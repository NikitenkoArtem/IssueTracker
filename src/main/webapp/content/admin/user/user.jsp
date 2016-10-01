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
<table border="1px">
    <thead>
    <tr>
        <th>UserID</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td name="userId"><a href="/user?userId=${user.userId}">${user.userId}</a></td>
            <td name="firstName">${user.firstName}</td>
            <td name="lastName">${user.lastName}</td>
            <td name="email">${user.email}</td>
            <c:forEach var="role" items="${roles}">
            <c:if test="${user.role == role.roleId}">
                <td>${role.roleName}</td>
            </c:if>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>