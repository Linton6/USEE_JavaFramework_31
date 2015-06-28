<%--
  Created by IntelliJ IDEA.
  User: val
  Date: 14-4-8
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.softtek.base.config.SessionKey" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>罗氏诊断 - 企业微信管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/lib/bootstrap-3.3.1-dist/css/bootstrap.min.css" />" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/dashboard.css" />"/>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<%
    Object agent = request.getSession().getAttribute(SessionKey.WEB_USER_INFO_KEY);
    if(agent == null){
        response.sendRedirect(request.getContextPath());
    }
%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">

    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="color:#cecece;">罗氏诊断 - 微信企业号管理</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/web/login/logout.action"/>">退出</a></li>
            </ul>
        </div>

        <%--<div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">Help</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <label for="qwe" style="display:none"></label>
                <input id="qwe" type="text" class="form-control" placeholder="Search..."/>
            </form>
        </div>--%>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="sidebar">

            <div class="panel-group" id="accordion">

                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" data-target="#collapse2">
                        <span class="glyphicon glyphicon-book pull-left"></span>
                        <h4 class="panel-title">&nbsp;知识库管理</h4>
                    </div>
                    <div id="collapse2" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-sidebar">
                                <li><a class="link_url" data-url="<c:url value="/web/admin/knowledgeBase/getKnowledgeBaseListPage.action"/>">知识库</a></li>
                                <li><a class="link_url" data-url="<c:url value="/web/admin/knowledgeBaseHistory/getKnowledgeBaseHistoryListPage.action"/>">知识库查询历史</a></li>
                                <li><a class="link_url" data-url="<c:url value="/web/admin/unknown/question/getUnknownQuestionListPage.action"/>">未知问题</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" data-target="#collapse1">
                        <span class="glyphicon glyphicon-cloud pull-left"></span>
                        <h4 class="panel-title">&nbsp;推送消息管理</h4>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-sidebar">
                                <li><a class="link_url" data-url="">推送消息</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

				<div class="panel panel-default">
					<div class="panel-heading" data-toggle="collapse" data-parent="#accordion" data-target="#collapse3">
						<span class="glyphicon glyphicon-user pull-left"></span>
						<h4 class="panel-title">&nbsp;基础数据管理</h4>
					</div>
					<div id="collapse3" class="panel-collapse collapse">
						<div class="panel-body">
							<ul class="nav nav-sidebar">
								<li><a class="link_url" data-url="<c:url value="/web/admin/wx/user/getWxUserListPage.action"/>">用户数据</a></li>
								<li><a class="link_url" data-url="<c:url value="/web/admin/wx/user/getWxUserSynchronousListPage.action"/>">待同步用户</a></li>
								<li><a class="link_url" data-url="<c:url value="/web/admin/wx/department/getWxDepartmentListPage.action"/>">部门管理</a></li>
								<li><a class="link_url" data-url="<c:url value="/web/admin/wx/tag/getWxTagListPage.action"/>">标签管理</a></li>                            </ul>
						</div>
					</div>
				</div>

                <!-- } collapse5 -->
                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion" data-target="#collapse4">
                        <span class="glyphicon glyphicon-asterisk pull-left"></span>
                        <h4 class="panel-title">&nbsp;管理员用户管理</h4>
                    </div>
                    <div id="collapse4" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-sidebar">
                                <li><a class="link_url" data-url="<c:url value="/web/admin/adminUser/getAdminUserListPage.action"/>">用户管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="main">
            <div style="position:absolute;top:0;right:0;bottom:0;left:0;">
                <iframe src="<c:url value="/pages/welcome.jsp"/> " class="span12" style="height:100%; width:100%; border:none;" name="mainframe" id="mainframe"></iframe>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/lib/jquery/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/lib/bootstrap-3.3.1-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var $link_url = $(".link_url");
        $link_url.on("click", function () {
            $link_url.parent("li").removeClass("active");
            $(this).parent("li").addClass("active");
            window.top.window.document.getElementById('mainframe').src = $(this).attr("data-url");
            return false;
        });
    });
</script>

</body>
</html>