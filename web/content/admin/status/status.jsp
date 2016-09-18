<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="by.epam.View" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>Status name</td>
    </tr>
    </thead>
    <tbody>
    <%
        final ArrayList<HashMap<String, Object>> statuses = (ArrayList<HashMap<String, Object>>) request.getAttribute("statuses");
        final StringBuffer buffer = new View().displayTableRow(statuses, "/status?statusName=");
        out.print(buffer);
    %>
    </tbody>
</table>
</body>
</html>
