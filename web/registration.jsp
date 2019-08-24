<%@ page import="com.uni.pa.constants.RequestAttributeConstants" %><%--
  Created by IntelliJ IDEA.
  User: MASOOM
  Date: 5/17/2019
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Accounting - Registration page</title>
</head>
<body>
<%
    if (request.getAttribute(RequestAttributeConstants.errorFlag) != null) {
%>
<%=request.getAttribute(RequestAttributeConstants.errorFlag)%>
<%
    }%>

<form action="/registration" method="post">
    <input name="f_Name" type="text" id="firstname" placeholder="Firstname"/>
    <br>
    <input name="l_Name" type="text" id="lastname" placeholder="Lastname"/>
    <br>
    <input name="subject" type="text" id="subject" placeholder="Subject"/>
    <br>
    <input name="credit" type="number" id="credit" placeholder="Credit"/>
    <br>
    <input name="username" type="text" id="username" placeholder="Username"/>
    <br>
    <input name="password" type="password" id="password" placeholder="Password"/>
    <br>
    <input name="role_id" type="number" id="role_id" placeholder="RoleId"/>
    <br>
    <button name="submit" type="submit">Register</button>
</form>
</body>
</html>