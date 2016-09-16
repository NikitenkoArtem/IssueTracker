<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
<form method="post" action="user">
    <table>
        <thead>
        <tr>
            <td>First name</td>
            <td>Last name</td>
            <td>Email address</td>
            <td>Role</td>
            <td>Password</td>
            <td>Password confirmation</td>
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
            <td><input type="password" name="password"/></td>
            <td><input type="password" name="confirm"/></td>
            <td colspan="2"><input type="submit" value="Add user"/></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>