<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemAuthority列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemAuthority').datagrid({
                url:'<c:url value="/web/admin/systemAuthority/getSystemAuthorityListJSON.json" />',
                title:'系统权限',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemAuthority',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'authorityId',title:'主键编号',width:8,align:'center'},
                    {field:'authorityName',title:'权限名称',width:8,align:'center'},
                    {field:'authorityDescription',title:'权限描述',width:8,align:'center'},
                    {field:'systemManager',title:'是否平台管理',width:8,align:'center'},
                    {field:'enabled',title:'是否禁用',width:8,align:'center'},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.authorityId < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/systemAuthority/getSystemAuthorityViewPage.action?authorityId=" />" + row.authorityId;
                        var editUrl = "<c:url value="/web/admin/systemAuthority/getSystemAuthorityEditPage.action?authorityId=" />" + row.authorityId;
                        return '&nbsp;' +
                               //'<a href="<c:url value="/web/admin/systemAuthority/updateAuthPage.action?authId=" />' + row.authorityId + '">修改</a>' +
                               //'&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemAuthority\', \'' + viewUrl + '\')">详情</a>' +
                               '&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemAuthority\', \'' + editUrl + '\')">编辑</a>' +
                               '&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.authorityId + '\', \'1\')">删除</a>' +
                               '&nbsp;';                                
                    }}
                ]]
            });

            formatPagination("dgSystemAuthority");

            vEasyUIUtil.createWindow("wSystemAuthority", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(authorityId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }
            // isFakeDelete=1表示假删除
            $.post("<c:url value="/web/admin/systemAuthority/logicRemoveSystemAuthority.json" />", {authorityId: authorityId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemAuthority').datagrid('reload');
                    }
                    else{
                        alert("该权限正在使用中！")
                    }
                }
            );
        }

        function submitSearch(){        
            var dgSystemAuthority = $('#dgSystemAuthority');
        
            dgSystemAuthority.datagrid('options').pageNumber = 1;
            dgSystemAuthority.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgSystemAuthority").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
        <div id="tbSystemAuthority" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wSystemAuthority', '<c:url value="/web/admin/systemAuthority/getSystemAuthorityEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgSystemAuthority"></table>
    </div>

</body>
</html>