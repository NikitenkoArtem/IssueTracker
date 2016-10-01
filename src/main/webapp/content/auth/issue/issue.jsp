<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="">
    <input type="search" name="search" placeholder="Search"/>
    <%--<input type="submit" name="submitIssue" value="Submit issue"/>--%>
</form>
<form method="post" action="">
    <input type="hidden" name="action" value="new"/>
    <input type="submit" value="Submit issue"/>
</form>
<table border="1px">
    <thead>
    <tr>
        <th>Summary</th>
        <th>Description</th>
        <th>Status</th>
        <th>Resolution</th>
        <th>Type</th>
        <th>Priority</th>
        <th>Project</th>
        <th>Build</th>
        <th>Assignee</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="issue" items="${issues}">
        <tr>
            <td>${issue.summary}</td>
            <td>${issue.description}</td>
            <c:forEach var="status" items="${statuses}">
                <c:if test="${status.statusId == issue.statusId}">
                    <td>${status.statusName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="resolution" items="${resolutions}">
                <c:if test="${resolution.resolutionId == issue.resolution}">
                    <td>${resolution.resolutionName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="type" items="${types}">
                <c:if test="${type.typeId == issue.type}">
                    <td>${type.typeName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="priority" items="${priorities}">
                <c:if test="${priority.priorityId == issue.priority}">
                    <td>${priority.priorityName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="project" items="${projects}">
                <c:if test="${project.projectId == issue.project}">
                    <td>${project.projectName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="build" items="${builds}">
                <c:if test="${issue.build == build.buildId}">
                    <td>${build.build}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="user" items="${users}">
                <c:if test="${user.userId == issue.assignee}">
                    <td>${user.firstName}</td>
                </c:if>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>