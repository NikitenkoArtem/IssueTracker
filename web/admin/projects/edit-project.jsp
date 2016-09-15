<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="by.epam.View" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="project">
    <table>
        <%@ include file="headerProject.jspf" %>
        <%
            //            final ArrayList<HashMap<String, Object>> project =
//                    (ArrayList<HashMap<String, Object>>) request.getAttribute("project");
//            Iterator<HashMap<String, Object>> iterator = project.iterator();
            Object projectName = null;
            Object description = null;
            Object manager = null;
//            while (iterator.hasNext()) {
//                final HashMap<String, Object> next = iterator.next();
//                projectName = next.get("project_name");
//                description = next.get("description");
//                manager = next.get("manager");
//            }
            projectName = request.getParameter("project_name");
            description = request.getParameter("description");
//            manager = request.getParameter("manager");
            manager = "manager";
        %>
        <tbody>
        <tr>
            <td><input type="text" name="name" value="<%=projectName%>" required/></td>
            <td><input type="text" name="desc" value="<%=description%>" required/></td>
            <td>
                <select required><%
                    final ArrayList<HashMap<String, Object>> builds =
                            (ArrayList<HashMap<String, Object>>) request.getAttribute("builds");
                    String openTag = "<option name='build'>";
                    final String s = "<option name='build' selected>";
                    StringBuffer buffer = new View().display(builds, openTag, manager.toString());
                    out.print(buffer);
                %></select>
            </td>
            <td>
                <select required><%
                    final ArrayList<HashMap<String, Object>> managers =
                            (ArrayList<HashMap<String, Object>>) request.getAttribute("managers");
                    openTag = "<option name='manager'>";
                    buffer = new View().display(managers, openTag, manager.toString());
                    out.print(buffer);
                %></select>
            </td>
            <td><input type="submit" name="edit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
