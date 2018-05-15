<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemWork列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemWork').datagrid({
                url:'<c:url value="/web/admin/work/getSystemWorkListJSON.json" />',
                title:'角色',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemWork',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'workId',title:'编号',width:8,align:'center'},
                    {field:'workName',title:'名称',width:8,align:'center'},
                    // {field:'workDescription',title:'角色描述',width:8,align:'center'},
                    // {field:'systemManager',title:'是否平台管理',width:8,align:'center'},
                    // {field:'enabled',title:'是否禁用',width:8,align:'center'},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.workId < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/work/getSystemWorkViewPage.action?workId=" />" + row.workId;
                        var editUrl = "<c:url value="/web/admin/work/getSystemWorkEditPage.action?workId=" />" + row.workId;
                        <%--var updateUrl = "<c:url value="/web/admin/work/updateWorkPage.action?workId=" />" + row.workId;--%>
                        return '&nbsp;' +
                                // '<a href="javascript:void(0)" onclick="openIFrameWindow(\'#wSystemWorkFrame\', \'' + updateUrl + '\')">修改</a>' +
                                <%--/*'<a href="<c:url value="/web/admin/work/updateWorkPage.action?workId=" />' + row.workId + '">修改</a>' +*/--%>
                                // '&nbsp;|&nbsp;' +
                                // '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemWork\', \'' + viewUrl + '\')">详情</a>' +
                                // '&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemWork\', \'' + editUrl + '\')">编辑</a>' +
                                '&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.workId + '\', \'1\')">删除</a>' +
                                '&nbsp;';
                    }}
                ]]
            });

            formatPagination("dgSystemWork");

            vEasyUIUtil.createWindow("wSystemWork", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });

            // vEasyUIUtil.createWindow("wSystemWorkFrame", {
            //     title: '角色修改',
            //     width:'96%',
            //     height:vSugar.getMaxWinHeight("mainPanel", 600),
            //     modal:true,
            //     maximized:false,
            //     closed:true,
            //     collapsible:false,
            //     minimizable:false,
            //     maximizable:true,
            //     iconCls:'icon-save'/*,
            //      onClose:function(){
            //      $('#dgOrderBase').datagrid('reload');
            //      }*/
            // });
            $("#wSystemWorkFrame").window("body").css("padding","0");
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(workId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }
        //
        //     isFakeDelete=1表示假删除
            $.post("<c:url value="/web/admin/work/logicRemoveSystemWork.action" />", {workId: workId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemWork').datagrid('reload');
                    }
                    else{
                        alert("该角色正在使用中！")
                    }
                }
            );
        }

        <%--function submitSearch(){--%>
            <%--var dgSystemRole = $('#dgSystemRole');--%>

            <%--dgSystemRole.datagrid('options').pageNumber = 1;--%>
            <%--dgSystemRole.datagrid('getPager').pagination("options").pageNumber = 1;--%>
            <%--var param = {--%>
            <%--};--%>
            <%--$("#dgSystemRole").datagrid({queryParams: param}, 'reload');--%>
        <%--}--%>

        <%--function clearSearch(){--%>
        <%--}--%>
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
        <div id="tbSystemWork" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wSystemWork', '<c:url value="/web/admin/work/getSystemWorkEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgSystemWork"></table>
    </div>

</body>
</html>