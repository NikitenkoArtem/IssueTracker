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
            <td><input type="text" name="summary" required/></td>
            <td><textarea cols="15" name="description" required></textarea></td>
            <td>
                <select>
                    <c:forEach var="status" items="${statuses}">
                        <option name="status">${status.statusName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="resolution" items="${resolutions}">
                        <option name="resolution">${resolution.resolutionName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="type" items="${types}">
                        <option name="type">${type.typeName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="priority" items="${priorities}">
                        <option name="priority">${priority.priorityName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="project" items="${projects}">
                        <option name="project">${project.projectName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="build" items="${builds}">
                        <option name="build">${build.build}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select>
                    <c:forEach var="resolution" items="${users}">
                        <option name="assignee">${resolution.firstName}</option>
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