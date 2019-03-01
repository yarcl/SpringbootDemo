<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="page" uri="http://com.xqkj/pageTag" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
<div class="product-list">

    <div class="product-title">
        <div><h4>>>产品列表</h4></div>
        <div class="razor-btn"><div class="btn-block btn-info text-center">新增</div></div>
    </div>
    <div class="product-find">
        <form action="${pageContext.request.contextPath}/myProductInfo.do" method="post">
            <div class="product-condition">
                    <div><strong>名称：</strong><input type="text" name="name" size="16"/></div>
                    <div class="special"><strong>时间：</strong><input type="text" name="starttime" size="16"/><strong>--</strong>
                        <input type="text" name="endtime" size="16"/></div>
                    <div><strong>appKey：</strong><input type="text" name="appkey" size="16"/></div>
                    <div class="img"><span class="btn btn-info">查找</span></div>
            </div>
        </form>
    </div>
    <div class="product-content">
        <div>
            <table class="table table-bordered table-hover">
                <thead>
                    <th width="8%">编号</th>
                    <th width="8%">名称</th>
                    <th width="20%">描述</th>
                    <th width="20%">时间</th>
                    <th width="20%">appKey</th>
                    <th width="34%">操作</th>
                </thead>
                <c:forEach var="item" items="${razorProductList}">
                    <tr>
                        <td>${item.userId}</td>
                        <td>${item.name}</td>
                        <td>${item.description}</td>
                        <td>${item.date}</td>
                        <td>${item.productKey}</td>
                        <td>
                            <a href="#" class="btn btn-sm btn-info">修改</a>
                            <a href="#" class="btn btn-sm btn-danger">删除</a>
                            <a href="#" class="btn btn-sm btn-warning">关闭采集</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="product-page-tag">
        <page:pageTag url="${pageContext.request.contextPath}/myProductInfo.do?" page="${page}"/>
    </div>
</div>