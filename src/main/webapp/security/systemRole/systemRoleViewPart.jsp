<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemRole" name="" action="<c:url value="/web/admin/systemRole/executeSystemRoleEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemRole')">关闭</a>
            </td>
        </tr>
        <c:if test="${systemRole != null}">
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemRole.roleId}</p>
                <input type="hidden" name="roleId" value="${systemRole.roleId}" />
            </td>
        </tr>
        </c:if>
        <tr>
            <td class="item-name">角色名称：</td>
            <td class="item-value">
                ${systemRole.roleName}
            </td>
        </tr>
        <tr>
            <td class="item-name">角色描述：</td>
            <td class="item-value">
                ${systemRole.roleDescription}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否平台管理：</td>
            <td class="item-value">
                ${systemRole.systemManager}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                ${systemRole.enabled}
            </td>
        </tr>
    </table>
</form>

<%-- <fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd" /> --%>