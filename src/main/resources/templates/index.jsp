<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/18
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>ProdutList's Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
</head>
<body>
    <div style="width: 1280px;margin: 0 auto; background-color: rgba(0,0,0,0.1)">
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="left.jsp"></jsp:include>
    </div>
</body>
</html>
