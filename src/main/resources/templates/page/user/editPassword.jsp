<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/page/user/editPersonInfo.css" type="text/css">
<div class="person-info">
    <div class="person-info-title">
        <div><h4>>>修改密码</h4></div>
    </div>
    <div class="person-info-content">
        <table class="person-table table table-bordered table-hover">
            <thead>
            <tr>
                <th colspan="2" class="text-center">密码修改</th>
            </tr>
            </thead>
            <form action="${pageContext.request.contextPath}/user/editPassword.do" method="post">
                <input type="hidden" name="userId" value="${user.userId}">
                <tr>
                    <td width="50%">原密码:</td>
                    <td width="50%"><input type="password" name="oldPwd" value="" placeholder="原密码" required></td>
                </tr>
                <tr>
                    <td>新密码:</td>
                   <td><input type="password" name="newPwd" value="" placeholder="新密码" required></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input type="password" name="cNewPwd" value="" placeholder="确认密码" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <a href="#"><button class="btn btn-primary btn-info" type="submit">修改</button></a>
                        <%--<a href="javascript:history.back();"><button class="btn btn-primary btn-info" type="button">返回</button></a>--%>
                    </td>
                </tr>
            </form>
        </table>
    </div>
</div>
