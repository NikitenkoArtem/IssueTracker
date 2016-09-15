<%@ page import="by.epam.View" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String url = null;
        url = "/issue?action=add";
//        url = "/issue?issueId=" + issueId;
%>
<form method="post" action="<%=url%>">
    <table>
        <thead>
        <tr>
            <td>Summary</td>
            <td>Description</td>
            <td>Status</td>
            <td>Resolution</td>
            <td>Type</td>
            <td>Priority</td>
            <td>Project</td>
            <td>Build found</td>
            <td>Assignee</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="summary" required/></td>
            <td><textarea cols="15" name="desc" required></textarea></td>
            <td>
                <select><%
                    final ArrayList<HashMap<String, Object>> statuses = (ArrayList<HashMap<String, Object>>) request.getAttribute("statuses");
                    String openTag = "<option>";
//                    openTag = "<option name='manager'>";
                    StringBuffer buffer = new View().display(statuses, openTag);
                    out.print(buffer);
//                    out.print(openTd + priorityName + closeTd);
//                    out.print(openTd + "<a href='/issue?issueId=" + issueId + "'>" + issueId + "</a>" + closeTd);
//                    out.print(openTd + priorityName + closeTd);
//                    out.print(openTd + assignee + closeTd);
//                    out.print(openTd + typeName + closeTd);
//                    out.print(openTd + statusName + closeTd);
//                    out.print(openTd + summary + closeTd);
                %></select>
            </td>
            <td>
                <select>
                    <%
//                        openTag = "<option>";
                        final ArrayList<HashMap<String, Object>> resolutions = (ArrayList<HashMap<String, Object>>) request.getAttribute("resolutions");
                        buffer = new View().display(resolutions, openTag);
                        out.print(buffer);
                    %>
                </select>
            </td>
            <td>
                <select>
                    <%
//                        openTag = "<option>";
                        final ArrayList<HashMap<String, Object>> priorities = (ArrayList<HashMap<String, Object>>) request.getAttribute("priorities");
                        buffer = new View().display(priorities, openTag);
                        out.print(buffer);
                    %>
                </select>
            </td>
            <td>
                <select>
                    <%
//                        openTag = "<option name>";
                        final ArrayList<HashMap<String, Object>> types = (ArrayList<HashMap<String, Object>>) request.getAttribute("types");
                        buffer = new View().display(types, openTag);
                        out.print(buffer);
                    %>
                </select>
            </td>
            <td>
                <select>
                    <option name="project" required></option>
                </select>
            </td>
            <td>
                <select>
                    <option name="build" required></option>
                </select>
            </td>
            <td>
                <select>
                    <option name="assignee"></option>
                </select>
            </td>
            <td><input type="submit" value="Add issue"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>