<%@ page import="java.util.*" %>
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
        <th>Project name</th>
        <th>Description</th>
        <th>Manager</th>
    </tr>
    </thead>
    <tbody>
    <%
        final ArrayList<HashMap<String, Object>> projects = (ArrayList<HashMap<String, Object>>) request.getAttribute("projects");
        if (projects == null) {
//            request.getRequestDispatcher("/project?action=list").forward(request, response);
        }
        final Iterator<HashMap<String, Object>> iterator = projects.iterator();
        String openTr = "<tr>";
        String closeTr = "</tr>";
        String closeTd = "</td>";
        while (iterator.hasNext()) {
            out.print(openTr);
            final HashMap<String, Object> next = iterator.next();
            final Object projectName = next.get("project_name");
            final Object description = next.get("description");
            final Object manager = next.get("manager");
            String openTd = "<td name='" + projectName + "'>";
            out.print(openTd + "<a href='/project?projectName=" + projectName + "'>" + projectName + "</a>" + closeTd);
            openTd = "<td name='" + description + "'>";
            out.print(openTd + description + closeTd);
            openTd = "<td name='" + manager + "'>";
            out.print(openTd + manager + closeTd);
            out.print(closeTr);
        }
    %>
    </tbody>
</table>
<form method="post" action="/project">
    <input type="submit" name="newProject" value="New project"/>
</form>
</body>
</html>
