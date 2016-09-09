<%@ page language="java" import="by.epam.DBConnection" %>
<%@ page language="java" import="java.sql.ResultSet" %>

<%
    DBConnection conn = (DBConnection) DBConnection.getConnection();
    final ResultSet rs = conn.executeQuery("");
%>
