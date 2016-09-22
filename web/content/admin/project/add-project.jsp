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
<form method="post" action="project">
    <table>
        <tr>
            <th>Project name</th>
            <td><input type="text" name="projectName" required/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><input type="text" name="description" required/></td>
        </tr>
        <tr>
            <th>Build</th>
            <td><input type="text" name="build" required/></td>
        </tr>
        <tr>
            <th>Manager</th>
            <td>
                <select>
                    <c:forEach var="mgr" items="${managers}">
                        <c:forEach var="resolution" items="${users}">
                            <c:if test="${resolution.userId == mgr.user}">
                                <option name="manager">${resolution.firstName}</option>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="add" value="Add project"/></td>
        </tr>
    </table>
    <input type="hidden" name="action" value="add"/>
</form>
</body>
</html>
