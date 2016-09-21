<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Issue Tracker</title>
    <link rel="stylesheet" type="text/css" href="/style.css"/>
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