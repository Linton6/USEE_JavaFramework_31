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
            <td class="item-name">名称1：</td>
            <td class="item-value">
                ${systemWork.workName}
            </td>
        </tr>
        <tr>
            <td class="item-name">防滑参数：</td>
            <td class="item-value">
                ${systemWork.antiskidMode}
            </td>
        </tr>
        <tr>
            <td class="item-name">防水参数：</td>
            <td class="item-value">
                ${systemWork.drainageMode}
            </td>
        </tr>
        <tr>
            <td class="item-name">防热辐射：</td>
            <td class="item-value">
                ${systemWork.antiThermalRadiation}
            </td>
        </tr>
        <tr>
            <td class="item-name">底部水幕：</td>
            <td class="item-value">
                ${systemWork.bottomWaterCurtain}
            </td>
        </tr>
        <tr>
            <td class="item-name">呼吸装置：</td>
            <td class="item-value">
                ${systemWork.breathingEquipment}
            </td>
        </tr>
        <tr>
            <td class="item-name">额定荷载：</td>
            <td class="item-value">
                ${systemWork.bucketRatedLoad}
            </td>
        </tr>
        <tr>
            <td class="item-name">创建时间：</td>
            <td class="item-value">
                ${systemWork.createTime}
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