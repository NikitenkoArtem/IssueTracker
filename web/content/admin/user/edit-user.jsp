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
                <input type="hidden" name="oldEmail" value="${user.email}"/>
            </td>
            <td>
                <select>
                    <c:set var="roleId" value="${users.role}"/>
                    <c:forEach var="row" items="${roles}">
                        <c:choose>
                            <c:when test="${row.roleId == roleId}">
                                <option name="role" selected>${row.roleName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="role">${row.roleName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td colspan="2"><input type="submit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="action" value="edit"/>
</form>
</body>
</html>