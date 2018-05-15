<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemPeople" name="" action="<c:url value="/web/admin/people/executeSystemPeopleEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemPeople')">关闭</a>
            </td>
        </tr>
        <c:if test="${systemPeople != null}">
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemPeople.peopleId}</p>
                <input type="hidden" name="peopleId" value="${systemPeople.peopleId}" />
            </td>
        </tr>
        </c:if>
        <%--<tr>--%>
            <%--<td class="item-name">登陆帐户：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemPeople.peopleAccount}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">登陆密码：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemUser.userPassword}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemPeople.peopleId}</p>
                <input type="hidden" name="peopleId" value="${systemPeople.peopleId}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">姓名：</td>
            <td class="item-value">
                ${systemPeople.peopleName}
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td class="item-name">是否平台管理：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemPeople.systemManager}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">用户角色：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemUser.systemRole.roleId}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">是否禁用：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemPeople.enabled}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">假删除：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemPeople.valid}--%>
            <%--</td>--%>
        </tr>
    </table>
</form>

<%-- <fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd" /> --%>