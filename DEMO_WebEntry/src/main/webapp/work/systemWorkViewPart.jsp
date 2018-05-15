<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemWork" name="" action="<c:url value="/web/admin/work/executeSystemWorkEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemWork')">关闭</a>
            </td>
        </tr>
        <c:if test="${systemWork != null}">
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemWork.workId}</p>
                <input type="hidden" name="workId" value="${systemWork.workId}" />
            </td>
        </tr>
        </c:if>
        <tr>
            <td class="item-name">名称：</td>
            <td class="item-value">
                ${systemWork.workName}
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td class="item-name">角色描述：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemWork.workDescription}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">是否平台管理：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemWork.systemManager}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">是否禁用：</td>--%>
            <%--<td class="item-value">--%>
                <%--${systemWork.enabled}--%>
            <%--</td>--%>
        <%--</tr>--%>
    </table>
</form>

<%-- <fmt:formatDate value="${record.createDate}" pattern="yyyy-MM-dd" /> --%>