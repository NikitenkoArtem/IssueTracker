<%--
  Created by IntelliJ IDEA.
  User: Price
  Date: 09.09.2016
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/loginCheck">
        <table>
            <tbody>
                <tr>
                    <td>User name</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Login"/></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
