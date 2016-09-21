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
            <th>New password</th>
        </tr>
        <tr>
            <th>Confirm</th>
        </tr>
        <tr>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td><input type="password" name="confirm"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="userId" value="${user.userId}"/>
    <input type="hidden" name="action" value="pwd"/>
</form>
</body>
</html>