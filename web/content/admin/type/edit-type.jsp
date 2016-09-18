<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Object typeName = request.getAttribute("typeName");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="type">
    <input type="text" name="edited" value="<%=typeName%>"/>
    <input type="hidden" name="typeName" value="<%=typeName%>"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>
