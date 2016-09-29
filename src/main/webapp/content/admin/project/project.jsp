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
<table border="1px">
    <thead>
    <tr>
        <th>Project name</th>
        <th>Description</th>
        <th>Manager</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${projects == null}">
            <h1>No projects found</h1>
        </c:when>
        <c:otherwise>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td><a href="/project?projectId=${project.projectId}">${project.projectName}</a></td>
                    <td>${project.description}</td>
                    <td>
                        <c:forEach var="mgr" items="${managers}">
                            <c:if test="${project.manager == mgr.managerId}">
                                <c:forEach var="user" items="${users}">
                                    <c:if test="${user.userId == mgr.user}">
                                        ${user.firstName}
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
</body>
</html>
