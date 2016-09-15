<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.epam.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
//    final String action = request.getParameter("action");
//    final String issueId = request.getParameter("issueId");
    String url = null;
//    if (action.equals("add")) {
        url = "/issue?action=add";
//    } else if (action.equals("edit")) {
//        url = "/issue?issueId=" + issueId;
//    }
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
        <%--<c:if test="${param.editissue == true}">--%>
        <%--<%@ include file="editIssue.jspf" %>--%>
        <%--</c:if>--%>
        <tr>
            <td><input type="text" name="summary" required/></td>
            <td><textarea cols="15" name="desc" required></textarea></td>
            <td>
                <%
                    final ArrayList<HashMap<String, Object>> statuses = (ArrayList<HashMap<String, Object>>) request.getAttribute("statuses");
                    final ArrayList<HashMap<String, Object>> resolutions = (ArrayList<HashMap<String, Object>>) request.getAttribute("resolutions");
                    final ArrayList<HashMap<String, Object>> priorities = (ArrayList<HashMap<String, Object>>) request.getAttribute("priorities");
                    final ArrayList<HashMap<String, Object>> types = (ArrayList<HashMap<String, Object>>) request.getAttribute("types");
                %>
                <select><%
                    String openTag = "<option>";
                    String closeTag = "</option>";
                    Iterator<HashMap<String, Object>> iterator = statuses.iterator();
                    while (iterator.hasNext()) {
//                        out.print(openTag);
                        for (Object o : iterator.next().values()) {
                            out.print(openTag + o.toString() + closeTag);
                        }
//                        out.print(closeTag);
                    }
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
//                        closeTag = "</option>";
                        iterator = resolutions.iterator();
                        while (iterator.hasNext()) {
//                            out.print(openTag);
                            for (Object o : iterator.next().values()) {
                                out.print(openTag + o.toString() + closeTag);
                            }
//                            out.print(closeTag);
                        }
                    %>
                </select>
            </td>
            <td>
                <select>
                    <%
//                        openTag = "<option>";
//                        closeTag = "</option>";
                        iterator = priorities.iterator();
                        while (iterator.hasNext()) {
//                            out.print(openTag);
                            for (Object o : iterator.next().values()) {
                                out.print(openTag + o.toString() + closeTag);
                            }
//                            out.print(closeTag);
                        }
                    %>
                </select>
            </td>
            <td>
                <select>
                    <%
//                        openTag = "<option name>";
//                        closeTag = "</option>";
                        iterator = types.iterator();
                        while (iterator.hasNext()) {
//                            out.print(openTag);
                            for (Object o : iterator.next().values()) {
                                out.print(openTag + o.toString() + closeTag);
                            }
//                            out.print(closeTag);
                        }
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