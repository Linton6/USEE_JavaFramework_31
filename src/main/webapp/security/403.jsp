<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>no permit</title>
    <style type="text/css">
	    body{
			text-align: center;
		}
		
		#sessionOut {
            margin-top: 50px;
			padding: 15px 50px;
			width: 500px;
			border: 2px solid green;
			background-color: yellow;
			text-align: center;
		}
		
		a{
			font-weight:bold;
			font-family:"宋体";
			font-size:18px;
		}

    </style>
  </head>

<body>

	<div id ="sessionOut">
		您没有权限，请重新<a href="javascript:void(0);" onclick="window.location.href='<c:url value="/pages/login.jsp" />'">登录</a>系统！
	</div>
	
	
</body>

<script type="text/javascript">
  
if (self != top){
	window.top.location = window.location;
}

</script>

</html>