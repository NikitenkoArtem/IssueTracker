<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<%@ include file="/navigation.jspf" %>
<section>
    <input type="submit" value="Submit issue"/>
    <input type="search" value="Search"/>
</section>
<table border="1px">
    <thead>
    <tr>
        <th>Id</th>
        <th>Priority</th>
        <th>Assignee</th>
        <th>Type</th>
        <th>Status</th>
        <th>Summary</th>
    </tr>
    </thead>
    <tbody>
    <%--<c:set var="priority" value="${priorities}"/>--%>
    <c:forEach var="issue" items="${issues}">
        <tr>
            <td>${issue.issueId}</td>
            <c:forEach var="priority" items="${priorities}">
                <c:if test="${priority.priorityId == issue.priority}">
                    <td>${priority.priorityName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="resolution" items="${users}">
                <c:if test="${resolution.userId == issue.assignee}">
                    <td>${resolution.firstName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="type" items="${types}">
                <c:if test="${type.typeId == issue.type}">
                    <td>${type.typeName}</td>
                </c:if>
            </c:forEach>
            <c:forEach var="status" items="${statuses}">
                <c:if test="${status.statusId == issue.status}">
                    <td>${status.statusName}</td>
                </c:if>
            </c:forEach>
            <td>${issue.summary}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
