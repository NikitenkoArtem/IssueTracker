<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/resources/styles/style.css"/>
</head>
<body>
<%@include file="/navigation.jspf" %>
    <span>Enter user email or identifier</span>
    <form method="post" action="user">
        <input type="search" name="email" placeholder="Search" required/>
        <input type="submit" value="Find user"/>
        <input type="hidden" name="action" value="find"/>
    </form>
</body>
</html>