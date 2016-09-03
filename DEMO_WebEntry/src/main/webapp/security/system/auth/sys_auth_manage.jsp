<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/security/resources/lib/bootstrap-3.3.1-dist/css/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/security/resources/stylesheets/theme.css" />">
    <link rel="stylesheet" href="<c:url value="/security/resources/lib/font-awesome/css/font-awesome.css" />">
    <script type="text/javascript" src="<c:url value="/security/resources/lib/jquery/jquery-1.11.0.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/security/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <link href="<c:url value="/security/resources/EasyValidator/css/validate.css"/>" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value="/security/resources/EasyValidator/js/validate.pack.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/security/resources/stylesheets/adjust.css"/>">
    <script src="<c:url value="/security/resources/js/alertMsg.js"/>" type="text/javascript"></script>
<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}

.security-item-table {
	width: 100%;
	text-align: left;
}

.security-item-table tr {
	border-top: 1px solid rgb(221, 221, 221);
}

.security-item-table td {
	padding: 2px;
}
img.arr {
	cursor: pointer;
}
</style>

<script type="text/javascript">

	var authId="${auth.authorityId}";
	function removeRes(resId){
		var trId="#res"+resId;
		$.post("<c:url value="/web/admin/systemAuthority/removeResFromAuth.action" />",
				{"authId":authId,"resId":resId},
				function(data){	
					if(data.result=="1"){
						$(trId).remove();
						//alert("移除成功");
						infomsg('资源已移除!');
						var res=data.res;
						var tr='<tr id="res'+res.resourceId+'">';
						tr+='<td><img class="arr" src="<c:url value="/security/resources/images/arr-left.jpg"/>" onclick="addRes('+res.resourceId+')"></td>';
						tr+='<td>'+res.resourceId+'</td>';
						tr+='<td>'+res.resourceName +'</td>';
						tr+='<td>'+res.resourceDesc+'</td>';
						//tr+='<td>'+res.resourceString+'</td>';
						tr+='</tr>';
						$("#orres_table").append(tr);		
					}
				});
	
	}
	
	function addRes(resId){
		var trId="#res"+resId;
		$.post("<c:url value="/web/admin/systemAuthority/addResToAuth.action" />",
				{"authId":authId,"resId":resId},
				function(data){	
					if(data.result=="1"){
						$(trId).remove();
						//alert("添加成功");
						infomsg('资源已添加!');
						var res=data.res;
						var tr='<tr id="res'+res.resourceId+'" >';
						tr+='<td>'+res.resourceId +'</td>';
						tr+='<td>'+res.resourceName +'</td>';
						tr+='<td>'+res.resourceDesc +'</td>';
						//tr+='<td>'+res.resourceString +'</td>';
						tr+='<td><img class="arr" src="<c:url value="/security/resources/images/arr-right.jpg"/>" onclick="removeRes('+res.resourceId+')"></td>';
						tr+='</tr>';
						$("#ores_table").append(tr);	
					}
				});
	}
	
	//ajaxFrom
	$(document).ready(function() { 
        $('#auth_form').ajaxForm(function(data) {
        	if(data.result=="1"){
            	//alert("信息修改成功！");
            	infomsg('信息修改成功!');
        	}else{
        		//alert("系统繁忙..."); 
        		wrongmsg('系统忙...!');
        	}
        }); 
    }); 
	
</script>
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<div class="header">

		<h1 class="page-title">系统管理</h1>
	</div>

	<ul class="breadcrumb">
		<li><a href="<c:url value="/web/admin/systemAuthority/getSystemAuthorityListPage.action" />">权限管理</a> <span class="divider">/</span></li>
		<li class="active">权限修改</li>
	</ul>

	<div class="container-fluid">
		<div class="row-fluid">
			<!-- 主题内容 -->

			<div class="dialog" style="display:none;">
				<div class="block">
					<p class="block-heading">权限修改</p>
					<div class="block-body">
						<form id="auth_form"
							action="<c:url value="/system/auth/updateauthinfoact.action" />"
							method="post">
							<input type="hidden" name="authId" value="${auth.authorityId }">
							<label>权限名</label> <input name="authName" type="text"
								class="span12" value="${auth.authorityName }" reg="^\d+|\w+|[\u4e00-\u9fa5]+|\d+|\w+$"><br/><span name="easyTip">(权限名不能为空!)</span> <label>描述</label>
							<input value="${auth.authorityDescription}" name="authDesc" type="text"
								class="span12" reg="^\d+|\w+|[\u4e00-\u9fa5]+|\d+|\w+$"><br/><span name="easyTip">(权限描述不能为空!)</span> <a href="javascript:;"
								class="btn btn-primary pull-right"
								onclick="$('#auth_form').submit()">更改</a>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
			</div>

			<div class="row-fluid">
				<div class="block span6">
					<p class="block-heading">拥有资源</p>
					<div class="block-body gallery">
						<table  id="ores_table" class="security-item-table">
							<tr  class="security-tr-th" style="border-top: 0px">
								<th>#</th>
								<th>资源名</th>
								<th>描述</th>
								<!-- <th>链接</th> -->
								<th></th>
							</tr>
							<c:forEach items="${ownResource}" var="ores">
								<tr id="res${ores.resourceId}" >
									<td>${ores.resourceId }</td>
									<td>${ores.resourceName }</td>
									<td>${ores.resourceDescription}</td>
									<%-- <td>${ores.resourceString}</td> --%>
									<td>
									<img class="arr" src="<c:url value="/security/resources/images/arr-right.jpg"/>" onclick="removeRes('${ores.resourceId}')">
									</td>
								</tr>
							</c:forEach>
						</table>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="block span6">
					<p class="block-heading">未拥有资源</p>
					<div class="block-body gallery">
						<table id="orres_table" class="security-item-table">
							<tr class="security-tr-th" style="border-top: 0px">
								<th></th>
								<th>#</th>
								<th>资源名</th>
								<th>描述</th>
								<!-- <th>链接</th> -->
							</tr>
							<c:forEach items="${otherResource}" var="orres">
								<tr id="res${orres.resourceId}">
									<td>
									<img class="arr" src="<c:url value="/security/resources/images/arr-left.jpg"/>" onclick="addRes('${orres.resourceId}')">
									</td>
									<td>${orres.resourceId }</td>
									<td>${orres.resourceName }</td>
									<td>${orres.resourceDescription }</td>
									<%-- <td>${orres.resourceString }</td> --%>
								</tr>
							</c:forEach>
						</table>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>

			<!-- 主题内容 end-->
			<jsp:include page="../footer.jsp"></jsp:include>

		</div>
	</div>
    <script type="text/javascript" src="<c:url value="/security/resources/lib/bootstrap-3.3.1-dist/js/bootstrap.min.js" />"></script>
</body>
</html>