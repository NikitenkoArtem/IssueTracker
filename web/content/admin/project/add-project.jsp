<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="project">
    <table>
        <thead>
        <tr>
            <th>Project name</th>
        </tr>
        <tr>
            <th>Description</th>
        </tr>
        <tr>
            <th>Build</th>
        </tr>
        <tr>
            <th>Manager</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="projectName" required/></td>
        </tr>
        <tr>
            <td><input type="text" name="description" required/></td>
        </tr>
        <tr>
            <td><input type="text" name="build" required/></td>
            </tr>
        <tr>
            <td><input type="text" name="manager" required/>
                </td>
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
            <td><input type="submit" name="add" value="Add project"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="action" value="add"/>
</form>
</body>
</html>
