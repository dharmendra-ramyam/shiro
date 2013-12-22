<%@ page import="org.apache.shiro.session.Session" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>RECTRICTED: Hello World!</h1>
        <%@ include file="_mesg.jsp"%>
        <a href="index">INDEX</a>
    </body>
</html>

<%
    Session s = SecurityUtils.getSubject().getSession();

    s.setAttribute("foo", System.currentTimeMillis());
%>
