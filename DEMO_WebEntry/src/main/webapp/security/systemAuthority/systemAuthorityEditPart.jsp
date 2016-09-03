<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemAuthority" name="" method="post" enctype="multipart/form-data">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemAuthority')">取消</a>
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
                <input type="text" name="authorityName" value="${systemAuthority.authorityName}" />
            </td>
        </tr>
        <tr>
            <td class="item-name">权限描述：</td>
            <td class="item-value">
                <input type="text" name="authorityDescription" value="${systemAuthority.authorityDescription}" />
            </td>
        </tr>
        <tr style="display:none;">
            <td class="item-name">是否平台管理：</td>
            <td class="item-value">
                <input type="text" name="systemManager" value="1" /><%--${systemAuthority.systemManager}--%>
            </td>
        </tr>
        <tr style="display:none;">
            <td class="item-name">是否禁用：</td>
            <td class="item-value">
                <input type="text" name="enabled" value="1" /><%--${systemAuthority.enabled}--%>
            </td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    function submitEdit(){
        $("#myFormSystemAuthority").form('submit', {
            url: '<c:url value="/web/admin/systemAuthority/executeSystemAuthorityEdit.action" />',
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
                    $("#wSystemAuthority").window('close');
                    $('#dgSystemAuthority').datagrid('reload');
                } else {
                    $.messager.alert("出错了", data, 'error');
                }
            }
        });
    }


</script>