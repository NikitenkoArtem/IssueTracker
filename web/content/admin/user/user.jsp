<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
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
    <c:forEach var="row" items="${users}">
        <tr>
            <td name="firstName">${row.firstName}</td>
            <td name="lastName">${row.lastName}</td>
            <td name="email"><a href="/user?email=${row.email}">${row.email}</a></td>
            <c:if test="${row.role == roleId}">
                <td>${role.roleName}</td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form method="post" action="user">
    <input type="submit" name="new" value="New user"/>
</form>
</body>
</html>
