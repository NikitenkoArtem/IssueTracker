<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
<form method="post" action="issue">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Create date</th>
            <th>Created by</th>
            <th>Modify date</th>
            <th>Modified by</th>
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
            <td><input type="text" value="${issue.issueId}" readonly/></td>
            <td><input type="date" value="${issue.createDate}" readonly/></td>
            <td><input type="text" value="${issue.createBy}" readonly/></td>
            <td><input type="text" value="${issue.modifyDate}" readonly/></td>
            <td><input type="date" value="${issue.modifiedBy}" readonly/></td>
            <td><input type="text" name="summary" value="${issue.summary}" required/></td>
            <td><textarea cols="15" name="description" required>${issue.description}</textarea></td>
            <td>
                <select>
                    <c:forEach var="status" items="${statuses}">
                        <c:choose>
                            <c:when test="${status.statusId == issue.status}">
                                <option name="status" selected>${status.statusName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="status">${status.statusName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="resolution" items="${resolutions}">
                        <c:choose>
                            <c:when test="${resolution.resolutionId == issue.resolution}">
                                <option name="resolution" selected>${resolution.resolutionName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="resolution">${resolution.resolutionName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="type" items="${types}">
                        <c:choose>
                            <c:when test="${type.typeId == issue.type}">
                                <option name="type" selected>${type.typeName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="type">${type.typeName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="priority" items="${priorities}">
                        <c:choose>
                            <c:when test="${priority.priorityId == issue.priority}">
                                <option name="priority" selected>${priority.priorityName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="priority">${priority.priorityName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="project" items="${projects}">
                        <c:choose>
                            <c:when test="${user.projectId == issue.project}">
                                <option name="project" selected>${project.projectName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="project">${project.projectName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="project" items="projects">
                        <c:forEach var="build" items="builds">
                            <c:if test="${project.projectId == issue.project}">
                                <c:if test="${project.projectId == build.project}">
                                    <option name="build">${build.build}</option>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="resolution" items="${users}">
                        <c:choose>
                            <c:when test="${resolution.userId == issue.assignee}">
                                <option name="assignee" selected>${resolution.firstName}</option>
                            </c:when>
                            <c:otherwise>
                                <option name="assignee">${resolution.firstName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td><input type="submit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>