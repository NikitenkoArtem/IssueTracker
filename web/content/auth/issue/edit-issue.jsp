<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String url = null;
    url = "/issue?action=add";
//        url = "/issue?issueId=" + issueId;
%>
<form method="post" action="<%=url%>">
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
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="summary" value="${issue.summary}" required/></td>
            <td><textarea cols="15" name="description" required>${issue.description}</textarea></td>
            <td>
                <select>
                    <c:set var="statusId" value="${issue.status}"/>
                    <c:forEach var="row" items="${statuses}">
                        <c:choose>
                            <c:when test="${row.statusId == statusId}">
                                <option name="status" selected>${row.statusName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="status">${row.statusName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:set var="resolutionId" value="${issue.resolution}"/>
                    <c:forEach var="row" items="${resolutions}">
                        <c:choose>
                            <c:when test="${row.resolutionId == resolutionId}">
                                <option name="resolution" selected>${row.resolutionName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="resolution">${row.resolutionName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:set var="typeId" value="${issue.type}"/>
                    <c:forEach var="row" items="${types}">
                        <c:choose>
                            <c:when test="${row.typeId == typeId}">
                                <option name="type" selected>${row.typeName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="type">${row.typeName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:set var="priorityId" value="${issue.priority}"/>
                    <c:forEach var="row" items="${priorities}">
                        <c:choose>
                            <c:when test="${row.priorityId == priorityId}">
                                <option name="priority" selected>${row.priorityName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="priority">${row.priorityName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:set var="projectId" value="${issue.project}"/>
                    <c:forEach var="row" items="${projects}">
                        <c:choose>
                            <c:when test="${row.projectId == projectId}">
                                <option name="project" selected>${row.projectName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="project">${row.projectName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="row" items="${builds}">
                        <option name="build">${row.build}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:set var="assignee" value="${issue.assignee}"/>
                    <c:forEach var="row" items="${users}">
                        <c:choose>
                            <c:when test="${row.email == assignee}">
                                <option name="assignee" selected>${row.email}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="assignee">${row.email}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="Add issue"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>