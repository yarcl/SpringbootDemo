<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="page" uri="http://com.xqkj/pageTag" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/menu/menuInfo.css" type="text/css">
<div class="menu-list">

    <div class="menu-title">
        <div><h4>>>菜单列表</h4></div>
        <div class="razor-btn"><div class="btn-block btn-info text-center">新增</div></div>
    </div>
    <div class="menu-find">
        <form action="${pageContext.request.contextPath}/menu/allMenuInfo.do" method="post">
            <div class="menu-condition">
                <div><strong>名称：</strong><input type="text" name="name" size="16"/></div>
                <div class="special"><strong>url路径：</strong><input type="text" name="starttime" size="16"/></div>
                <div></div>
                <div class="img"><span class="btn btn-info">查找</span></div>
            </div>
        </form>
    </div>
    <div class="menu-content">
        <table class="table table-bordered table-hover">
            <thead>
                <th width="8%">ID</th>
                <th width="13%">名称</th>
                <th width="25%">URL路径</th>
                <th width="8%">状态</th>
                <th width="10%">是否删除</th>
                <th width="8%">父ID</th>
                <th width="28%">操作</th>
            </thead>
            <c:forEach var="item" items="${menuList}">
                <tr>
                    <td>${item.menuId}</td>
                    <td>${item.menuName}</td>
                    <td>${item.menuUrl}</td>
                    <td>${item.isActive}</td>
                    <td>${item.isDelete}</td>
                    <td>${item.parentId}</td>
                    <td>
                        <a href="#" class="btn btn-sm btn-info">修改</a>
                        <a href="#" class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="product-page-tag">
        <page:pageTag url="${pageContext.request.contextPath}/menu/allMenuInfo.do?" page="${page}"/>
    </div>
</div>