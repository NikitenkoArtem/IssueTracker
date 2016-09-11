<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
    <header>
        <%@ include file="../../navigation.jspf" %>
    </header>
    <c:if test="${param.useradd == true}">
        <%@ include file="add-edit-user.jspf" %>
    </c:if>
</body>
</html>
