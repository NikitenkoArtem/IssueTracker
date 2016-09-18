<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${failed}
    <form method="post" action="login">
        <table>
            <tbody>
                <tr>
                    <td>User name</td>
                    <td><input type="text" name="username" required/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" required/></td>
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
