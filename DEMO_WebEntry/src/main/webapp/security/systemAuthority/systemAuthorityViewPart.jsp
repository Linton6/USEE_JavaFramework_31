<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemAuthority" name="" action="<c:url value="/web/admin/systemAuthority/executeSystemAuthorityEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemAuthority')">关闭</a>
            </td>
        </tr>
        <c:if test="${systemAuthority != null}">
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemAuthority.authorityId}</p>
                <input type="hidden" name="authorityId" value="${systemAuthority.authorityId}" />
            </td>
        </tr>
        </c:if>
        <tr>
            <td class="item-name">权限名称：</td>
            <td class="item-value">
                ${systemAuthority.authorityName}
            </td>
        </tr>
        <tr>
            <td class="item-name">权限描述：</td>
            <td class="item-value">
                ${systemAuthority.authorityDescription}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否平台管理：</td>
            <td class="item-value">
                ${systemAuthority.systemManager}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                ${systemAuthority.enabled}
            </td>
        </tr>
    </table>
</form>

<%-- <fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd" /> --%>