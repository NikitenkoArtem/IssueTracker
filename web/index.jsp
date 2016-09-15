<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<header>
    <%@ include file="navigation.jspf" %>
</header>
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
    <%
//        final StringBuffer issues = (StringBuffer) request.getAttribute("issues");
//        if (issues == null) {
//            request.getRequestDispatcher("/issue?action=list").forward(request, response);
//        }
//        out.print(issues);
    %>
    </tbody>
</table>
</body>
</html>
