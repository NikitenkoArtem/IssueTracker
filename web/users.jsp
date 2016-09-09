<%--
  Created by IntelliJ IDEA.
  User: Price
  Date: 07.09.2016
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header>
        <%@ include file="navigation.jsp" %>
    </header>
    <form method="post" action="/user">
        <table>
            <tbody>
                <tr>
                    <td>First name</td>
                    <td><input type="text" name="first-name"/></td>
                </tr>
                <tr>
                    <td>Last name</td>
                    <td><input type="text" name="last-name"/></td>
                </tr>
                <tr>
                    <td>Email address</td>
                    <td><input type="email" name="email"/></td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td>
                        <select>
                            <option>Guest</option>
                            <option>User</option>
                            <option>Administrator</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="pwd"/></td>
                </tr>
                <tr>
                    <td>Password confirmation</td>
                    <td><input type="password" name="confirm"/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="go"/></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
