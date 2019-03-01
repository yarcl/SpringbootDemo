<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="page" uri="http://com.xqkj/pageTag" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/role/roleInfo.css" type="text/css">
<div class="role-list">

    <div class="role-title">
        <div><h4>>>角色列表</h4></div>
        <div class="razor-btn"><div class="btn-block btn-info text-center">新增</div></div>
    </div>
    <div class="role-find">
        <form action="${pageContext.request.contextPath}/role/allRoleInfo.do" method="post">
            <div class="role-condition">
                <div><strong>名称：</strong><input type="text" name="name" size="16"/></div>
                <div class="special"></div>
                <div><strong>状态：</strong><input type="text" name="appkey" size="16"/></div>
                <div class="img"><span class="btn btn-info">查找</span></div>
            </div>
        </form>
    </div>
    <div class="role-content">
        <table class="table table-bordered table-hover">
            <thead>
            <th width="25%">ID</th>
            <th width="25%">角色名称</th>
            <th width="25%">状态</th>
            <th width="25%">操作</th>
            </thead>
            <c:forEach var="item" items="${roleList}">
                <tr>
                    <td>${item.roleId}</td>
                    <td>${item.roleName}</td>
                    <td>${item.isActive}</td>
                    <td>
                        <%--<c:if test="${user.roleId==0}">
                            <a href="#" class="btn btn-sm btn-default">密码</a>
                        </c:if>--%>
                        <a href="#" class="btn btn-sm btn-info">修改</a>
                        <a href="#" class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="product-page-tag">
        <page:pageTag url="${pageContext.request.contextPath}/role/allRoleInfo.do?" page="${page}"/>
    </div>
</div>