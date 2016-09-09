<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="dbconnect.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <header>
        <%@ include file="navigation.jsp" %>
    </header>
  <table border="1px">
      <thead>
        <tr>
            <td>Id</td>
            <td>Priority</td>
            <td>Assignee</td>
            <td>Type</td>
            <td>Status</td>
            <td>Summary</td>
        </tr>
      </thead>
      <tbody></tbody>
  </table>
  </body>
</html>
