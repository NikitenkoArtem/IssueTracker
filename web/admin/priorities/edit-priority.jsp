<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Object priorityName = request.getAttribute("priorityName");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="priority">
    <input type="text" name="edited" value="<%=priorityName%>"/>
    <input type="hidden" name="priorityName" value="<%=priorityName%>"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>
