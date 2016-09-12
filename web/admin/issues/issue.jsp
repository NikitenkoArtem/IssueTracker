<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.epam.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${param.issueId == 1}">
            <%--<jsp:forward page="/issue"/>--%>
            <%@ include file="addIssue.jspf" %>
        </c:when>
        <c:when test="${param.addIssue == 'true'}">
            <%@ include file="addIssue.jspf" %>
        </c:when>
    </c:choose>
</body>
</html>