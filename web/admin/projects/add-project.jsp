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
        <tbody>
        <tr>
            <td><input type="text" name="name" required/></td>
            <td><input type="text" name="desc" required/></td>
            <td><input type="text" name="build" required/></td>
            <td><input type="text" name="manager" required/>
                <select><%
                    final ArrayList<HashMap<String, Object>> managers =
                            (ArrayList<HashMap<String, Object>>) request.getAttribute("managers");
                    Iterator<HashMap<String, Object>> iterator = managers.iterator();
                    String openTag = "<option>";
                    String closeTag = "</option>";
                    while (iterator.hasNext()) {
                        for (Object o : iterator.next().values()) {
                            out.print(openTag + o.toString() + closeTag);
                        }
                    }
                %></select>
            </td>
            <td><input type="submit" name="add" value="Add project"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
