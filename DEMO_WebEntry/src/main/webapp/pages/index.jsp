<%--
  Created by IntelliJ IDEA.
  User: val
  Date: 14-4-8
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>BaseProject</title>
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

<%--
<%
    Object agent = request.getSession().getAttribute(ConstantKeySession.WEB_USER_INFO_KEY);
    if(agent == null){
        response.sendRedirect(request.getContextPath() + "/security/login.jsp");
    }
%>
--%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">

    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" style="color:#cecece;">BaseProject</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<c:url value="/logout.action"/>">退出</a></li>
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

                <sec:authorize access="hasAnyAuthority('系统用户','系统角色','系统权限')">
                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse" data-parent="#accordion-auth" data-target="#collapse-auth">
                        <span class="glyphicon glyphicon-asterisk pull-left"></span>
                        <h4 class="panel-title">&nbsp;系统设置</h4>
                    </div>
                    <div id="collapse-auth" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ul class="nav nav-sidebar">
                                <sec:authorize access="hasAnyAuthority('系统用户')">
                                    <li><a class="link_url" target="mainframe" href="<c:url value="/web/admin/systemUser/getSystemUserListPage.action" />"><span>系统用户</span></a></li>
                                </sec:authorize>

                                <sec:authorize access="hasAnyAuthority('系统角色')">
                                    <li><a class="link_url" target="mainframe" href="<c:url value="/web/admin/systemRole/getSystemRoleListPage.action" />"><span>系统角色</span></a></li>
                                </sec:authorize>

                                <sec:authorize access="hasAnyAuthority('系统权限')">
                                    <li><a class="link_url" target="mainframe" href="<c:url value="/web/admin/systemAuthority/getSystemAuthorityListPage.action" />"><span>系统权限</span></a></li>
                                </sec:authorize>
                            </ul>
                        </div>
                    </div>
                </div>
                </sec:authorize>

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
            return true;
        });
    });

    //window.top.window.document.getElementById('mainframe').src = $(this).attr("data-url");
</script>

</body>
</html>


<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="refresh" content="0; url=pages/login.jsp" />
</head>
</html>
--%>