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
            <td>${issue.statusId.statusName}</td>
            <td>${issue.resolutionId.resolutionName}</td>
            <td>${issue.typeId.typeName}</td>
            <td>${issue.priorityId.priorityName}</td>
            <td>${issue.projectId.projectName}</td>
            <td>${issue.buildId.build}</td>
            <td>${issue.assigneeId.firstName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>