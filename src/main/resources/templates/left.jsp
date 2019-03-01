<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/18
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/left.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <ul id="main-nav" class="nav nav-tabs nav-stacked">
                <li class="active">
                    <a href="#">
                        <i class="glyphicon glyphicon-th-large"></i>
                        首页
                    </a>
                </li>
                <li>
                    <a href="#systemSetting1" class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-tasks"></i>
                        应用管理
                        <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <ul id="systemSetting1" class="nav nav-list collapse secondmenu" style="height: 0px;">
                        <li><a href="${pageContext.request.contextPath}/myProductInfo.do?userId=${user.userId}" target="product-main">
                            <i class="glyphicon glyphicon-list-alt"></i>
                            我的应用
                        </a></li>
                    </ul>
                </li>
                <%--<li>
                    <a href="#">
                        <i class="glyphicon glyphicon-credit-card"></i>
                        物料管理
                    </a>
                </li>

                <li>
                    <a href="#">
                        <i class="glyphicon glyphicon-globe"></i>
                        分发配置
                        <span class="label label-warning pull-right">5</span>
                    </a>
                </li>--%>

                <%--<li>
                    <a href="#">
                        <i class="glyphicon glyphicon-calendar"></i>
                        图表统计
                    </a>
                </li>--%>
                <li>
                    <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-cog"></i>
                        系统管理
                        <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
                        <li><a href="${pageContext.request.contextPath}/admin/allAdminUsers.do" target="product-main"><i class="glyphicon glyphicon-user"></i>用户管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/menu/allMenuInfo.do" target="product-main"><i class="glyphicon glyphicon-th-list"></i>菜单管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/role/allRoleInfo.do" target="product-main"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
                        <%--<li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>--%>
                    </ul>
                </li>
                <li>
                    <a href="#systemSetting2" class="nav-header collapsed" data-toggle="collapse">
                        <i class="glyphicon glyphicon-tag"></i>
                        个人中心
                        <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                    </a>
                    <ul id="systemSetting2" class="nav nav-list collapse secondmenu" style="height: 0px;">
                        <li><a href="${pageContext.request.contextPath}/page/user/personInfo.jsp" target="product-main">
                            <i class="glyphicon glyphicon-user"></i>个人信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/user/editPassword.jsp" target="product-main"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
                        <%--<li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>--%>
                    </ul>

                </li>
                <li>
                    <%--<a href="${pageContext.request.contextPath}/system/systemInfo.do">
                        <i class="glyphicon glyphicon-fire"></i>
                        关于系统
                    </a>--%>
                    <a href="${pageContext.request.contextPath}/page/system/systemInfo.jsp" target="product-main">
                        <i class="glyphicon glyphicon-fire"></i>
                        关于系统
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <iframe src="page/user/personInfo.jsp" name="product-main" frameborder="0"  width="100%" height="600" style="border-left: solid cornsilk"/>
        </div>
    </div>
</div>

