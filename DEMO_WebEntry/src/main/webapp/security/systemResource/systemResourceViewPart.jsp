<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemResource" name="" action="<c:url value="/web/admin/systemResource/executeSystemResourceEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemResource')">关闭</a>
            </td>
        </tr>
        <c:if test="${systemResource != null}">
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemResource.resourceId}</p>
                <input type="hidden" name="resourceId" value="${systemResource.resourceId}" />
            </td>
        </tr>
        </c:if>
        <tr>
            <td class="item-name">资源名称：</td>
            <td class="item-value">
                ${systemResource.resourceName}
            </td>
        </tr>
        <tr>
            <td class="item-name">资源描述：</td>
            <td class="item-value">
                ${systemResource.resourceDescription}
            </td>
        </tr>
        <tr>
            <td class="item-name">资源类型：</td>
            <td class="item-value">
                ${systemResource.resourceType}
            </td>
        </tr>
        <tr>
            <td class="item-name">资源链接：</td>
            <td class="item-value">
                ${systemResource.resourceString}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否平台用户：</td>
            <td class="item-value">
                ${systemResource.systemManager}
            </td>
        </tr>
        <tr>
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                ${systemResource.enabled}
            </td>
        </tr>
    </table>
</form>

<%-- <fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd" /> --%>