<%@ page import="by.epam.View" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.entity.Priority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>Priority name</td>
    </tr>
    </thead>
    <tbody>
    <%
//        final ArrayList<HashMap<String, Object>> priorities = (ArrayList<HashMap<String, Object>>) request.getAttribute("priorities");
//        final StringBuffer buffer = new View().displayTableRow(priorities, "/priority?priorityName=");
//        out.print(buffer);
        final ArrayList<Priority> priorities = (ArrayList<Priority>) request.getAttribute("priorities");
        final StringBuffer buffer = new View().displayTableRow(priorities, "/priority?priorityName=");
        out.print(buffer);
    %>
    </tbody>
</table>
<form method="post" action="priority">
    <input type="submit" name="addPriority" value="New priority"/>
</form>
</body>
</html>
