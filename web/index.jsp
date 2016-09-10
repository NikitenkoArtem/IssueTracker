<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
    PrintWriter writer = response.getWriter();
    writer.print(request.getAttribute("str"));
%>
<header>
    <%@ include file="navigation.jspf" %>
</header>
<%@ include file="issueList.jspf" %>
</body>
</html>
