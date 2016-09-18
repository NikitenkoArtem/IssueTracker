<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final Object resolutionName = request.getAttribute("resolutionName");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="resolution">
    <input type="text" name="edited" value="<%=resolutionName%>"/>
    <input type="hidden" name="resolutionName" value="<%=resolutionName%>"/>
    <input type="submit" value="Apply"/>
</form>
</body>
</html>
