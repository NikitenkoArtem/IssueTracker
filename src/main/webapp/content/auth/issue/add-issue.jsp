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
<form method="post" action="issue">
    <table>
        <tr>
            <th>Summary</th>
            <td><input type="text" name="summary" required/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><textarea cols="15" name="description" required></textarea></td>
        </tr>
        <tr>
            <th>Status</th>
            <td>
                <select>
                    <c:forEach var="status" items="${statuses}">
                        <option name="status">${status.statusName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Resolution</th>
            <td>
                <select>
                    <c:forEach var="resolution" items="${resolutions}">
                        <option name="resolution">${resolution.resolutionName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Type</th>
            <td>
                <select>
                    <c:forEach var="type" items="${types}">
                        <option name="type">${type.typeName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Priority</th>
            <td>
                <select>
                    <c:forEach var="priority" items="${priorities}">
                        <option name="priority">${priority.priorityName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Project</th>
            <td>
                <select>
                    <c:forEach var="project" items="${projects}">
                        <option name="project">${project.projectName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Build</th>
            <td>
                <select>
                    <c:forEach var="build" items="${builds}">
                        <option name="build">${build.build}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Assignee</th>
            <td>
                <select>
                    <c:forEach var="resolution" items="${users}">
                        <option name="assignee">${resolution.firstName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Add issue"/></td>
        </tr>
    </table>
</form>
</body>
</html>