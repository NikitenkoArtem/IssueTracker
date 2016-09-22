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
<form method="post" action="user">
    <table>
        <tr>
            <th>First name</th>
            <td><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <th>Last name</th>
            <td><input type="text" name="lastName"/></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><input type="email" name="email"/></td>
        </tr>
        <tr>
            <th>Role</th>
            <td>
                <select>
                    <c:forEach var="resolution" items="${roles}">
                        <option name="role">${resolution.roleName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Password</th>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <th>Password confirmation</th>
            <td><input type="password" name="confirm"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Add user"/></td>
        </tr>
    </table>
    <input type="hidden" name="action" value="add"/>
</form>
</body>
</html>