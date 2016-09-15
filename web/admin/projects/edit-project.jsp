<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/project">
    <table>
        <%@ include file="headerProject.jspf" %>
        <%
            final ArrayList<HashMap<String, Object>> project =
                    (ArrayList<HashMap<String, Object>>) request.getAttribute("project");
            Iterator<HashMap<String, Object>> iterator = project.iterator();
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
            manager = request.getParameter("manager");
        %>
        <tbody>
        <tr>
            <td><input type="text" name="name" required/><%=projectName%></td>
            <td><input type="text" name="desc" required/><%=description%></td>
            <td><input type="text" name="build" required/>
                <select required><%
                    final ArrayList<HashMap<String, Object>> builds =
                            (ArrayList<HashMap<String, Object>>) request.getAttribute("builds");
                    iterator = builds.iterator();
                    String openTag = "<option name='build'>";
                    String closeTag = "</option>";
                    while (iterator.hasNext()) {
                        for (Object o : iterator.next().values()) {
                            final String value = o.toString();
                            if (value.equals(builds)) {
                                out.print("<option name='build' selected>" + value + closeTag);
                            } else {
                                out.print(openTag + value + closeTag);
                            }
                        }
                    }
                %></select>
            </td>
            <td>
                <select required><%
                    final ArrayList<HashMap<String, Object>> managers =
                            (ArrayList<HashMap<String, Object>>) request.getAttribute("managers");
                    iterator = managers.iterator();
                    openTag = "<option name='manager'>";
                    while (iterator.hasNext()) {
                        for (Object o : iterator.next().values()) {
                            final String value = o.toString();
                            if (value.equals(manager)) {
                                out.print("<option name='manager' selected>" + value + closeTag);
                            } else {
                                out.print(openTag + value + closeTag);
                            }
                        }
                    }
                %></select>
            </td>
            <td><input type="submit" name="edit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
