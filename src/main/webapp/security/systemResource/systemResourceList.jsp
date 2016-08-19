<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemResource列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemResource').datagrid({
                url:'<c:url value="/web/admin/systemResource/getSystemResourceListJSON.json" />',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemResource',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'resourceId',title:'主键编号',width:8,align:'center'},
                    {field:'resourceName',title:'资源名称',width:8,align:'center'},
                    {field:'resourceDescription',title:'资源描述',width:8,align:'center'},
                    {field:'resourceType',title:'资源类型',width:8,align:'center'},
                    {field:'resourceString',title:'资源链接',width:8,align:'center'},
                    {field:'systemManager',title:'是否平台用户',width:8,align:'center'},
                    {field:'enabled',title:'是否禁用',width:8,align:'center'},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.resourceId < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/systemResource/getSystemResourceViewPage.action?resourceId=" />" + row.resourceId;
                        var editUrl = "<c:url value="/web/admin/systemResource/getSystemResourceEditPage.action?resourceId=" />" + row.resourceId;
                        return '&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemResource\', \'' + viewUrl + '\')">详情</a>' +
                               '&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemResource\', \'' + editUrl + '\')">编辑</a>' +
                               '&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.resourceId + '\', \'1\')">删除</a>' +
                               '&nbsp;';                                
                    }}
                ]]
            });

            formatPagination("dgSystemResource");

            vEasyUIUtil.createWindow("wSystemResource", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(resourceId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }
            // isFakeDelete=1表示假删除
            $.post("logicRemoveSystemResource.action", {resourceId: resourceId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemResource').datagrid('reload');
                    }
                }
            );
        }

        function submitSearch(){        
            var dgSystemResource = $('#dgSystemResource');
        
            dgSystemResource.datagrid('options').pageNumber = 1;
            dgSystemResource.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgSystemResource").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
        <div id="tbSystemResource" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wSystemResource', '<c:url value="/web/admin/systemResource/getSystemResourceEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgSystemResource"></table>
    </div>

</body>
</html>