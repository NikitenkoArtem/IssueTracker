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
<form method="post" action="project">
    <table>
        <tr>
            <th>Project name</th>
            <td><input type="text" name="projectName" value="${project.projectName}" required/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><input type="text" name="description" value="${project.description}" required/></td>
        </tr>
        <tr>
            <th>Build</th>
            <td>
                <select required>
                    <c:forEach var="build" items="${builds}">
                        <c:if test="${build.project == project.projectId}">
                            <option name='build'>${build.build}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Manager</th>
            <td>
                <select required>
                    <%--<c:forEach var="project" items="${projects}">--%>
                    <c:forEach var="mgr" items="${managers}">
                        <%--<c:if test="${project.manager == mgr.managerId}">--%>
                        <c:forEach var="user" items="${users}">
                            <c:if test="${user.userId == mgr.user}">
                                <option name="manager">${user.firstName}</option>
                            </c:if>
                        </c:forEach>
                        <%--</c:if>--%>
                    </c:forEach>
                    <%--</c:forEach>--%>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="edit" value="Apply"/></td>
        </tr>
    </table>
    <input type="hidden" name="projectId" value="${project.projectId}"/>
</form>
</body>
</html>
