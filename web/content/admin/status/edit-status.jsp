<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Object statusName = request.getAttribute("statusName");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="status">
    <input type="text" name="newStatus" value="<%=statusName%>"/>
    <input type="hidden" name="statusName" value="<%=statusName%>"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>