<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Project name</th>
        <th>Description</th>
        <th>Manager</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${project == null}">
            <h1>No projects found</h1>
        </c:when>
        <c:otherwise>
            <c:forEach var="row" items="${projects}">
                <tr>
                    <td><a href="/project?projectId=${row.projectId}">${row.projectName}</a></td>
                    <td>${row.description}</td>
                    <td>
                        <c:forEach var="mgr" items="${managers}">
                            <c:if test="${row.manager == mgr.managerId}">
                                ${mgr.email}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
<form method="post" action="project">
    <input type="submit" name="newProject" value="New project"/>
</form>
</body>
</html>
