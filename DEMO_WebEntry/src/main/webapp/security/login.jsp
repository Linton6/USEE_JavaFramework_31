<%--
  Created by IntelliJ IDEA.
  User: val
  Date: 14/11/25
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>WMS</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/lib/bootstrap-3.3.1-dist/css/bootstrap.min.css" />" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        #container {
            width: 858px;
            height: 400px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -429px;
            margin-top: -190px;
        }
        .form-group{margin-bottom:6px;}
        form{margin-bottom:0;}
        label{margin-bottom:2px;}
    </style>
</head>
<body style="background:#2E3E4E;">

<div id="container">
    <div></div>
    <div class="panel panel-default" style="border:none;">
        <div class="panel-body">
            <div class="pull-left">
                <img src="<c:url value="/resources/images/login/bg480.jpg" />" title="" style="width:480px;height:350px;" />
            </div>
            <div class="pull-right" style="width:320px;">
                <div style="margin:0 0 15px;font-size:40px;padding:20px 0 0 10px;"><%----%>
                    <%--<img src="<c:url value="/resources/images/login/logo1120.png" />" style="width:320px;margin-left:-30px;" title="" />--%>
                    USEE_Project
                </div>
                <form action="<c:url value="/j_spring_security_check" />" method="post">
                    <div class="form-group">
                        <label for="agentName">用户名：</label>
                        <input type="text" class="form-control" name="j_username" id="agentName" placeholder="请输入用户名" value="admin">
                    </div>
                    <div class="form-group">
                        <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                        <input type="password" class="form-control" name="j_password" id="password" placeholder="请输入密码" value="admin">
                    </div>
                    <div class="form-group">
                        <button type="submit" id="sub" class="btn btn-primary" style="width:100%;margin-top:10px;">登录</button>
                    </div>
                    <c:if test="${param.error}"><span style="color:red;">用户名密码输入错误,请重新输入！</span></c:if>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/lib/jquery/jquery-1.11.0.min.js" />"></script>
<script src="<c:url value="/resources/lib/bootstrap-3.3.1-dist/js/bootstrap.min.js" />"></script>
<script type="text/javascript">
    $(document).on("keydown", function(event) {
        if (event.keyCode == 13) {
            $("#sub").click();
        }
    });

    if (self != top){
        window.top.location = window.location;
    }
</script>

</body>
</html>