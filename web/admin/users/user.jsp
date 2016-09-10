<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
    <header>
        <%@ include file="../../navigation.jspf" %>
    </header>
    <c:if test='${param.display == "true"}'
        var="text"
        scope="session">
        "Hello, world. display param"
    </c:if>
    <%@ include file="add-edit-user.jspf" %>
</body>
</html>
