<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/18
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<div class="razor-nav navbar">

    <div class="logo"><img src="img/global.logo"/><span class="logo-title">Yarcl的个人博客</span></div>
    <div class="nav-right">
        <ul>
            <li><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;<a href="#">${user.name}</a></li>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <li><a href="${pageContext.request.contextPath}/loginOut.do">退出</a></li>
        </ul>
    </div>
</div>

