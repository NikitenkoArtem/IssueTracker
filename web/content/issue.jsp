<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<table>
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
    <tr>
        <c:forEach var="issue" items="issues">
            <td>${issue.summary}</td>
            <td>${issue.description}</td>
            <c:forEach var="status" items="statuses">
                <c:if test="${status.statusId == issue.status}">
                    <td>${status.statusName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="resolution" items="resolutions">
                <c:if test="${resolution.resolutionId == issue.resolution}">
                    <td>${resolution.resolutionName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="type" items="types">
                <c:if test="${type.typeId == issue.type}">
                    <td>${type.typeName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="priority" items="priorities">
                <c:if test="${priority.priorityId == issue.priority}">
                    <td>${priority.priorityName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="project" items="projects">
                <c:if test="${project.projectId == issue.project}">
                    <td>${project.projectName}</td>
                </c:if>
                <c:forEach var="build" items="builds">
                    <c:if test="${project.projectId == build.project}">
                        <td>${build.build}</td>
                    </c:if>
                </c:forEach>
            </c:forEach>
            <c:forEach var="resolution" items="users">
                <c:if test="${resolution.userId == issue.assignee}">
                    <td>${resolution.firstName}</td>
                </c:if>
            </c:forEach>
        </c:forEach>
    </tr>
    </tbody>
</table>
</body>
</html>