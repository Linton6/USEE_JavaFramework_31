<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemPeople列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemUser').datagrid({
                url:'<c:url value="/web/admin/people/getSystemPeopleListJSON.json" />',
                title:'用户',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemPeople',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'peopleId',title:'编号',align:'center'},
                    {field:'peopleName',title:'姓名',width:8,align:'center'},
                    // {field:'userAccount',title:'登陆帐户',width:8,align:'center'},
                    // {field:'userPassword',title:'登陆密码',width:8,align:'center'},
                    // {field:'isSystemManager',title:'是否平台管理',width:8,align:'center',hidden:true},
                    // {field:'systemRoleId',title:'用户角色',width:8,align:'center'},
                    {field:'enabled',title:'是否禁用',width:8,align:'center',hidden:true},
                    {field:'valid',title:'假删除',width:8,align:'center',hidden:true},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                        if(row.peopleId < 0) { return ''; }
                        var viewUrl = "<c:url value="/web/admin/people/getSystemPeopleViewPage.action?peopleId=" />" + row.peopleId;
                        var editUrl = "<c:url value="/web/admin/people/getSystemPeopleEditPage.action?peopleId=" />" + row.peopleId;
                        return '&nbsp;' +
                               //'<a href="javascript:void(0)" onclick="openWindow(\'#wSystemUser\', \'' + viewUrl + '\')">详情</a>' +
                               //'&nbsp;|&nbsp;' +
                               '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemPeople\', \'' + editUrl + '\')">编辑</a>' +
                               //'&nbsp;|&nbsp;' +
                               //'<a href="javascript:void(0)" onclick="submitRemove(\'' + row.userId + '\', \'1\')">假删除</a>' +
                               '&nbsp;|&nbsp;' +                                
                               '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.userId + '\', \'0\')">删除</a>' +
                               '&nbsp;';                                
                    }}
                ]]
            });

            formatPagination("dgSystemPeople");

            vEasyUIUtil.createWindow("wSystemPeople", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(peopleId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }
            // isFakeDelete=1表示假删除
            $.post("logicRemoveSystemPeople.action", {peopleId: peopleId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemPeople').datagrid('reload');
                    }
                }
            );
        }

        function submitSearch(){        
            var dgSystemPeople = $('#dgSystemPeople');
        
            dgSystemPeople.datagrid('options').pageNumber = 1;
            dgSystemPeople.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgSystemPeople").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
        <div id="tbSystemPeople" class="toolBarPadding">
            <div style="float:left;">
                <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
                   onclick="openWindow('#wSystemPeople', '<c:url value="/web/admin/people/getSystemPeopleEditPage.action" />')">新增&nbsp;</a>
            </div>
            <div style="float:right;">
            </div>
            <div style="clear:both;"></div>
        </div>
        <table id="dgSystemPeople"></table>
    </div>

</body>
</html>