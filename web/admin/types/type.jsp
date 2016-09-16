<%@ page import="by.epam.View" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
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
        <td>Type name</td>
    </tr>
    </thead>
    <tbody>
    <%
        final ArrayList<HashMap<String, Object>> types = (ArrayList<HashMap<String, Object>>) request.getAttribute("types");
        final StringBuffer buffer = new View().displayTableRow(types, "/type?typeName=");
        out.print(buffer);
    %>
    </tbody>
</table>
<form method="post" action="type">
    <input type="submit" name="addType" value="New type"/>
</form>
</body>
</html>
