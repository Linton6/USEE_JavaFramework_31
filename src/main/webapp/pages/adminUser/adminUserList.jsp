<%--
  User: val.jzp
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>AdminUser列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgAdminUser').datagrid({
                url:'<c:url value="/web/admin/adminUser/getAdminUserListJSON.json" />',
                title:'管理员列表',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbAdminUser',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'id',title:'编号',align:'center'},
                    {field:'loginName',title:'用户名',width:80,align:'center'},
                    {field:'loginPassword',title:'密码',width:80,align:'center'},
                    {field:'realName',title:'姓名',width:80,align:'center'},
                    {field:'email',title:'邮箱',width:160,align:'center'},
                    {field:'createDate',title:'创建日期',width:80,align:'center'},
                    {field:'updateDate',title:'更新日期',width:80,align:'center'},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.id < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/adminUser/getAdminUserViewPage.action?id=" />" + row.id;
                        var editUrl = "<c:url value="/web/admin/adminUser/getAdminUserEditPage.action?id=" />" + row.id;
                        return '&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wAdminUser\', \'' + viewUrl + '\')">详情</a>' +
                               '&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wAdminUser\', \'' + editUrl + '\')">编辑</a>' +
                               '&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.id + '\', \'1\')">删除</a>' +
                               '&nbsp;';                                
                    }}
                ]]
            });

            formatPagination("dgAdminUser");

            vEasyUIUtil.createWindow("wAdminUser", {
                title: '新增/编辑',
                width:'80%',
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                collapsible:false,
                minimizable:false,
                maximizable:true,
                iconCls:'icon-save'
            });
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(id, isFakeDelete){
            vEasyUIUtil.ajaxHandle({
                v_url:"<c:url value="/web/admin/adminUser/logicRemoveAdminUser.action" />",
                v_data:{
                    id: id,
                    isFakeDelete: isFakeDelete //isFakeDelete=1表示假删除
                },
                v_callback:function(){
                    $('#dgAdminUser').datagrid('reload');
                }
            });
        }

        function submitSearch(){        
            var dgAdminUser = $('#dgAdminUser');
        
            dgAdminUser.datagrid('options').pageNumber = 1;
            dgAdminUser.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgAdminUser").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false" class="mainPanel">
        <div id="tbAdminUser" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wAdminUser', '<c:url value="/web/admin/adminUser/getAdminUserEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgAdminUser"></table>
    </div>

</body>
</html>