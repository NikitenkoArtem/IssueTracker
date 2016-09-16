<%@ page import="by.epam.View" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
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
        <td>Resolution name</td>
    </tr>
    </thead>
    <tbody>
    <%
        final ArrayList<HashMap<String, Object>> resolutions = (ArrayList<HashMap<String, Object>>) request.getAttribute("resolutions");
        final StringBuffer buffer = new View().displayTableRow(resolutions, "/resolution?resolutionName=");
        out.print(buffer);
    %>
    </tbody>
</table>
<form method="post" action="resolution">
    <input type="submit" name="addResolution" value="New resolution"/>
</form>
</body>
</html>
