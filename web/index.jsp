<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
//    PrintWriter writer = response.getWriter();
//    writer.print(request.getAttribute("str"));
%>
<header>
    <%@ include file="navigation.jspf" %>
</header>
<%--<jsp:forward page="/issue"/>--%>
    <%--<%@ include file="issueList.jspf" %>--%>
<table border="1px">
    <thead>
    <tr>
        <td>Id</td>
        <td>Priority</td>
        <td>Assignee</td>
        <td>Type</td>
        <td>Status</td>
        <td>Summary</td>
    </tr>
    </thead>
    <tbody>
    <%--<tr>--%>
    <%
        ResultSet issues = (ResultSet) request.getAttribute("issues");
        final ResultSetMetaData meta = issues.getMetaData();
        while (issues.next()) {
            out.print("<tr>");
            final String issueId = issues.getString("issue_id");
            final String priorityName = issues.getString("priority_name");
            final String assignee = issues.getString("assignee");
            final String typeName = issues.getString("type_name");
            final String statusName = issues.getString("status_name");
            final String summary = issues.getString("summary");

            out.print("<td><a href='/issue?issueId=" + issueId + "'</a>" + issueId +"</td>");
            out.print("<td>" + priorityName + "</td>");
            out.print("<td>" + assignee + "</td>");
            out.print("<td>" + typeName + "</td>");
            out.print("<td>" + statusName + "</td>");
            out.print("<td>" + summary + "</td>");
            out.print("</tr>");
        }
        issues.close();
    %>
    </tbody>
</table>
</body>
</html>
