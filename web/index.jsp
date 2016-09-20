<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="navigation.jspf" %>
<section>
    <input type="submit" value="Submit issue"/>
    <input type="search" value="Search"/>
</section>
<section>
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
</section>
</body>
</html>
