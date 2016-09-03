<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemUser列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemUser').datagrid({
                url:'<c:url value="/web/admin/systemUser/getSystemUserListJSON.json" />',
                title:'系统用户',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemUser',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'userId',title:'主键编号',align:'center'},
                    {field:'userName',title:'用户姓名',width:8,align:'center'},
                    {field:'userAccount',title:'登陆帐户',width:8,align:'center'},
                    {field:'userPassword',title:'登陆密码',width:8,align:'center'},
                    {field:'isSystemManager',title:'是否平台管理',width:8,align:'center',hidden:true},
                    {field:'systemRoleId',title:'用户角色',width:8,align:'center'},
                    {field:'enabled',title:'是否禁用',width:8,align:'center',hidden:true},
                    {field:'valid',title:'假删除',width:8,align:'center',hidden:true},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.userId < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/systemUser/getSystemUserViewPage.action?userId=" />" + row.userId;
                        var editUrl = "<c:url value="/web/admin/systemUser/getSystemUserEditPage.action?userId=" />" + row.userId;
                        return '&nbsp;' +
                               //'<a href="javascript:void(0)" onclick="openWindow(\'#wSystemUser\', \'' + viewUrl + '\')">详情</a>' +
                               //'&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemUser\', \'' + editUrl + '\')">编辑</a>' +
                               //'&nbsp;|&nbsp;' +
                               //'<a href="javascript:void(0)" onclick="submitRemove(\'' + row.userId + '\', \'1\')">假删除</a>' +
                               '&nbsp;|&nbsp;' +                                
                               '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.userId + '\', \'0\')">删除</a>' +
                               '&nbsp;';                                
                    }}
                ]]
            });

            formatPagination("dgSystemUser");

            vEasyUIUtil.createWindow("wSystemUser", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(userId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }
            // isFakeDelete=1表示假删除
            $.post("logicRemoveSystemUser.action", {userId: userId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemUser').datagrid('reload');
                    }
                }
            );
        }

        function submitSearch(){        
            var dgSystemUser = $('#dgSystemUser');
        
            dgSystemUser.datagrid('options').pageNumber = 1;
            dgSystemUser.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgSystemUser").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
        <div id="tbSystemUser" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wSystemUser', '<c:url value="/web/admin/systemUser/getSystemUserEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgSystemUser"></table>
    </div>

</body>
</html>