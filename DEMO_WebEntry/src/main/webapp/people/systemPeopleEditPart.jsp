<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${isPage == 1}">
<html>
<head>
    <title>SystemPeople列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
</head>
<body>
</c:if>

<form id="myFormSystemPeople" name="" method="post" enctype="multipart/form-data">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()">提交</a>
                <c:if test="${isPage != 1}">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemPeople')">取消</a>
                </c:if>
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
                <%--<input type="text" name="userAccount" value="${systemPeople.peopleAccount}" />--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="item-name">登陆密码：</td>--%>
            <%--<td class="item-value">--%>
                <%--<input type="text" name="userPassword" value="${systemUser.userPassword}" />--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="item-name">编号：</td>
            <td class="item-value">
                <p>${systemPeople.peopleId}</p>
                <input type="hidden" name="peopleId" value="${systemPeople.peopleId}" />
            </td>
        </tr>

        <tr <c:if test="${isPage == 1}">style="display:none;"</c:if>>
            <td class="item-name">姓名：</td>
            <td class="item-value">
                <input type="text" name="peopleName" value="${systemPeople.peopleName}" />
            </td>
        </tr>
        <%--<tr <c:if test="${isPage == 1}">style="display:none;"</c:if>>--%>
            <%--<td class="item-name">用户角色：</td>--%>
            <%--<td class="item-value">--%>
                <%--&lt;%&ndash;<input type="text" name="systemRole.roleId" value="${systemUser.systemRole.roleId}" />&ndash;%&gt;--%>
                <%--<select name="systemRole.roleId">--%>
                    <%--<option value="">请选择</option>--%>
                    <%--<c:forEach items="${roleList}" var="role">--%>
                        <%--<option value="${role.roleId}" <c:if test="${role.roleId==systemUser.systemRole.roleId}">selected="selected"</c:if>>--%>
                            <%--${role.roleName}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr style="display:none;">--%>
            <%--<td class="item-name">是否平台管理：</td>--%>
            <%--<td class="item-value">--%>
                <%--<input type="text" name="systemManager" value="1" />&lt;%&ndash;${systemUser.systemManager}&ndash;%&gt;--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr style="display:none;">
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                <input type="text" name="enabled" value="1" /><%--${systemUser.enabled}--%>
            </td>
        </tr>
        <tr style="display:none;">
            <td class="item-name">假删除：</td>
            <td class="item-value">
                <input type="text" name="valid" value="1" /><%--${systemUser.valid}--%>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    function submitEdit(){

        $("#myFormSystemPeople").form('submit', {
            url: '<c:url value="/web/admin/people/executeSystemPeopleEdit.action" />',
            onSubmit: function(){
                if(!$(this).form('validate')){
                    return false;
                }
                $.messager.progress({
                    title:'操作中',
                    msg:'正在操作。。。'
                });
                return true;
            },
            success: function(data){
                $.messager.progress('close');
                if(data == 1){
                    <c:if test="${isPage != 1}">
                    $("#wSystemPeople").window('close');
                    $('#dgSystemPeople').datagrid('reload');
                    </c:if>
                    <c:if test="${isPage == 1}">
                    $.messager.alert("提示", "修改成功", 'info');
                    </c:if>
                } else {
                    $.messager.alert("出错了", data, 'error');
                }
            }
        });
    }
</script>


<c:if test="${isPage == 1}">
</body>
</html>
</c:if>