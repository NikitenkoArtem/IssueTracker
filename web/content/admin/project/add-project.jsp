<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.epam.View" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/project">
    <table>
        <%@ include file="headerProject.jspf" %>
        <tbody>
        <tr>
            <td><input type="text" name="name" required/></td>
            <td><input type="text" name="desc" required/></td>
            <td><input type="text" name="build" required/></td>
            <td><input type="text" name="manager" required/>
                <select>
                    <c:forEach var="row" items="${managers}">
                        <option name="manager">${row.email}</option>
                    </c:forEach>
                    <%
//                        final ArrayList<HashMap<String, Object>> managers =
//                                (ArrayList<HashMap<String, Object>>) request.getAttribute("managers");
//                        String openTag = "<option>";
//                        final StringBuffer buffer = new View().display(managers, openTag);
//                        out.print(buffer);
                    %>
                </select>
            </td>
            <td><input type="submit" name="add" value="Add project"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
