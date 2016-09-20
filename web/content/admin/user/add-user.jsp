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
            <th>Email</th>
            <th>Role</th>
            <th>Password</th>
            <th>Password confirmation</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="firstName"/></td>
            <td><input type="text" name="lastName"/></td>
            <td><input type="email" name="email"/></td>
            <td>
                <select>
                    <c:forEach var="row" items="${roles}">
                        <option name="role">${row.roleName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="password" name="password"/></td>
            <td><input type="password" name="confirm"/></td>
            <td colspan="2"><input type="submit" value="Add user"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="action" value="add"/>
</form>
</body>
</html>