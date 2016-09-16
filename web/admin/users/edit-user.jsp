<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Object typeName = request.getAttribute("typeName");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
<form method="post" action="type">
    <table>
        <thead>
        <tr>
            <td>First name</td>
            <td>Last name</td>
            <td>Email address</td>
            <td>Role</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="firstName"/></td>
            <td><input type="text" name="lastName"/></td>
            <td><input type="email" name="email"/></td>
            <td>
                <select>
                    <option>Guest</option>
                    <option>User</option>
                    <option>Administrator</option>
                </select>
            </td>
            <td colspan="2"><input type="submit" value="Apply"/></td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="oldFirstName" value="<%=typeName%>"/>
    <input type="hidden" name="oldLastName" value="<%=typeName%>"/>
    <input type="hidden" name="oldEmail" value="<%=typeName%>"/>
    <input type="hidden" name="oldRole" value="<%=typeName%>"/>
</form>
</body>
</html>
