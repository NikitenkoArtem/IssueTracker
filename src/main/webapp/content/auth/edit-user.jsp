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
<form method="post" action="user">
    <table>
        <thead>
        <tr>
            <th>First name</th>
            <td><input type="text" name="firstName" value="${user.firstName}"/></td>
        </tr>
        <tr>
            <th>Last name</th>
            <td><input type="text" name="lastName" value="${user.lastName}"/></td>
        </tr>
        <tr>
            <th>Email address</th>
            <td><input type="email" name="email" value="${user.email}"/></td>
        </tr>
        <tr>
            <th>Role</th>
            <td>
                <select>
                    <c:set var="roleId" value="${user.role}"/>
                    <c:forEach var="resolution" items="${roles}">
                        <c:choose>
                            <c:when test="${resolution.roleId == roleId}">
                                <option name="role" selected>${resolution.roleName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="role">${resolution.roleName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Apply"/></td>
        </tr>
    </table>
    <input type="hidden" name="userId" value="${user.userId}"/>
    <input type="hidden" name="action" value="edit"/>
</form>
</body>
</html>