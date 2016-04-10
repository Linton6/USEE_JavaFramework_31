<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemUser" name="" action="<c:url value="/web/admin/systemUser/executeSystemUserEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemUser')">关闭</a>
            </td>
        </tr>
        <c:if test="${systemUser != null}">
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemUser.userId}</p>
                <input type="hidden" name="userId" value="${systemUser.userId}" />
            </td>
        </tr>
        </c:if>
        <tr>
            <td class="item-name">登陆帐户：</td>
            <td class="item-value">
                ${systemUser.userAccount}
            </td>
        </tr>
        <tr>
            <td class="item-name">登陆密码：</td>
            <td class="item-value">
                ${systemUser.userPassword}
            </td>
        </tr>
        <tr>
            <td class="item-name">用户姓名：</td>
            <td class="item-value">
                ${systemUser.userName}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否平台管理：</td>
            <td class="item-value">
                ${systemUser.systemManager}
            </td>
        </tr>
        <tr>
            <td class="item-name">用户角色：</td>
            <td class="item-value">
                ${systemUser.systemRole.roleId}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                ${systemUser.enabled}
            </td>
        </tr>
        <tr>
            <td class="item-name">假删除：</td>
            <td class="item-value">
                ${systemUser.valid}
            </td>
        </tr>
    </table>
</form>

<%-- <fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd" /> --%>