<%@ page import="com.uni.pa.constants.RequestAttributeConstants" %><%--
  Created by IntelliJ IDEA.
  User: MASOOM
  Date: 5/15/2019
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Accounting - Login page</title>
</head>
<body>
<%
    if (request.getAttribute(RequestAttributeConstants.errorFlag) != null) {
%>
<%=request.getAttribute(RequestAttributeConstants.errorFlag)%>
<%
    }%>
<form action="/authenticate" method="post">
    <input name="username" type="text" id="username" placeholder="Username"/>
    <br>
    <input name="password" type="password" id="password" placeholder="Password"/>
    <br>
    <button name="submit" type="submit">Login</button>
</form>
</body>
</html>
