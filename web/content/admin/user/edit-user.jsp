<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
<form method="post" action="user">
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
            <td>
                <input type="email" name="email" value="${user.email}"/>
            </td>
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
            <td><input type="submit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="userId" value="${user.userId}"/>
    <input type="hidden" name="action" value="edit"/>
</form>
</body>
</html>