<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="project" value="${param.projectName}"/>
<c:choose>
    <c:when test="${!empty project}">
        <%@ include file="add-edit-project.jspf" %>
    </c:when>
    <c:otherwise>
        <%@ include file="projectList.jspf" %>
    </c:otherwise>
</c:choose>
</body>
</html>
