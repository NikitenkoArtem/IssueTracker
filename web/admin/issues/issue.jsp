<%@ page import="by.epam.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Connection conn = DBConnection.getConnection();
    String status = "SELECT status_name FROM statuses";
    String resolution = "SELECT resolution_name FROM resolutions";
    String priority = "SELECT priority_name FROM priorities";
    String type = "SELECT type_name FROM types";
    final ResultSet statuses = conn.prepareStatement(status).executeQuery();
    final ResultSet resolutions = conn.prepareStatement(resolution).executeQuery();
    final ResultSet priorities = conn.prepareStatement(priority).executeQuery();
    final ResultSet types = conn.prepareStatement(type).executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--<%@ include file="editIssue.jspf" %>--%>
    <%@ include file="addIssue.jspf" %>
</body>
</html>