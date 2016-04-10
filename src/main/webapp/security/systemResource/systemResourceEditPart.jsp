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
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemResource')">取消</a>
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
                <input type="text" name="resourceName" value="${systemResource.resourceName}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">资源描述：</td>
            <td class="item-value">
                <input type="text" name="resourceDescription" value="${systemResource.resourceDescription}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">资源类型：</td>
            <td class="item-value">
                <input type="text" name="resourceType" value="${systemResource.resourceType}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">资源链接：</td>
            <td class="item-value">
                <input type="text" name="resourceString" value="${systemResource.resourceString}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">是否平台用户：</td>
            <td class="item-value">
                <input type="text" name="systemManager" value="${systemResource.systemManager}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                <input type="text" name="enabled" value="${systemResource.enabled}" />
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    function submitEdit(){
        var flag = true;
        $(".validateNeed").each(function(){
            if(!$(this).validatebox("isValid")){
                flag = false;
            }
        });

        if(!flag){
            return false;
        }

        $("#wSystemResource").window('close');
        $("#myFormSystemResource").ajaxSubmit({
            success : function(html, status) {
                $('#dgSystemResource').datagrid('reload');
            }
        });
    }
</script>