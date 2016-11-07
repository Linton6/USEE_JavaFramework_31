<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemRole列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemRole').datagrid({
                url:'<c:url value="/web/admin/systemRole/getSystemRoleListJSON.json" />',
                title:'系统角色',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemRole',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'roleId',title:'主键编号',width:8,align:'center'},
                    {field:'roleName',title:'角色名称',width:8,align:'center'},
                    {field:'roleDescription',title:'角色描述',width:8,align:'center'},
                    {field:'systemManager',title:'是否平台管理',width:8,align:'center'},
                    {field:'enabled',title:'是否禁用',width:8,align:'center'},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.roleId < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/systemRole/getSystemRoleViewPage.action?roleId=" />" + row.roleId;
                        var editUrl = "<c:url value="/web/admin/systemRole/getSystemRoleEditPage.action?roleId=" />" + row.roleId;
                        var updateUrl = "<c:url value="/web/admin/systemRole/updateRolePage.action?roleId=" />" + row.roleId;
                        return '&nbsp;' +
                                '<a href="javascript:void(0)" onclick="openIFrameWindow(\'#wSystemRoleFrame\', \'' + updateUrl + '\')">修改</a>' +
                                /*'<a href="<c:url value="/web/admin/systemRole/updateRolePage.action?roleId=" />' + row.roleId + '">修改</a>' +*/
                                '&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemRole\', \'' + viewUrl + '\')">详情</a>' +
                                '&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemRole\', \'' + editUrl + '\')">编辑</a>' +
                                '&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.roleId + '\', \'1\')">删除</a>' +
                                '&nbsp;';
                    }}
                ]]
            });

            formatPagination("dgSystemRole");

            vEasyUIUtil.createWindow("wSystemRole", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });

            vEasyUIUtil.createWindow("wSystemRoleFrame", {
                title: '角色修改',
                width:'96%',
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                maximized:false,
                closed:true,
                collapsible:false,
                minimizable:false,
                maximizable:true,
                iconCls:'icon-save'/*,
                 onClose:function(){
                 $('#dgOrderBase').datagrid('reload');
                 }*/
            });
            $("#wSystemRoleFrame").window("body").css("padding","0");
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(roleId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }

            // isFakeDelete=1表示假删除
            $.post("<c:url value="/web/admin/systemRole/logicRemoveSystemRole.action" />", {roleId: roleId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemRole').datagrid('reload');
                    }
                    else{
                        alert("该角色正在使用中！")
                    }
                }
            );
        }

        function submitSearch(){
            var dgSystemRole = $('#dgSystemRole');

            dgSystemRole.datagrid('options').pageNumber = 1;
            dgSystemRole.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgSystemRole").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
        <div id="tbSystemRole" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wSystemRole', '<c:url value="/web/admin/systemRole/getSystemRoleEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgSystemRole"></table>
    </div>

</body>
</html>