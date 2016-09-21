<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="type">
    <table>
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Email address</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="firstName" value="${user.firstName}"/></td>
            <td><input type="text" name="lastName" value="${user.lastName}"/></td>
            <td><input type="email" name="email" value="${user.email}"/></td>
            <td>
                <select>
                    <c:set var="userRole" value="${user.role}"/>
                    <c:forEach var="resolution" items="${roles}">
                        <c:choose>
                            <c:when test="${resolution.roleId == userRole}">
                                <option name="role" selected>${resolution.roleName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="role">${resolution.roleName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="userId" value="${user.userId}"/>
</form>
</body>
</html>