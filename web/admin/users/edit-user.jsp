<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Object firstName = request.getAttribute("firstName");
    final Object lastName = request.getAttribute("lastName");
    final Object email = request.getAttribute("email");
    final Object oldFirstName = request.getAttribute("oldFirstName");
    final Object oldLastName = request.getAttribute("oldLastName");
    final Object oldEmail = request.getAttribute("oldEmail");
    final Object oldRole = request.getAttribute("oldRole");
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
            <td><input type="text" name="firstName"/><%=firstName%></td>
            <td><input type="text" name="lastName"/><%=lastName%></td>
            <td><input type="email" name="email"/><%=email%></td>
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
    <input type="hidden" name="oldFirstName" value="<%=oldFirstName%>"/>
    <input type="hidden" name="oldLastName" value="<%=oldLastName%>"/>
    <input type="hidden" name="oldEmail" value="<%=oldEmail%>"/>
    <input type="hidden" name="oldRole" value="<%=oldRole%>"/>
</form>
</body>
</html>
