<%--
  Created by IntelliJ IDEA.
  User: Price
  Date: 07.09.2016
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="">
        <table>
            <tbody>
            <tr>
                <td>Summary</td>
                <td><input type="text" name="summary"/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><textarea rows="10" cols="15" name="desc"></textarea></td>
            </tr>
            <tr>
                <td>Status</td>
                <td>
                    <select>
                        <option name="status">???</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Type</td>
                <td>
                    <select>
                        <option name="type">???</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Priority</td>
                <td>
                    <select>
                        <option name="priority">???</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Project</td>
                <td>
                    <select>
                        <option name="project">???</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Build found</td>
                <td>
                    <select>
                        <option name="build_found">???</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Assignee</td>
                <td>
                    <select>
                        <option name="assignee">???</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
