<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 上面的是第一个是协议名称，第二个是服务器所在地，第三个是端口地址，后面是站点地址 -->
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<!-- 访问employee/下文件时，过滤器会跳回到首页，路径错乱 -->
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登陆</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
</head>
<body>
<div class="signin">
	<div class="signin-head" >
		<img src="images/横版logo.png" >
	</div>
	<form class="form-signin" role="form" action="LoginServlet" method="post">
		<input type="text" class="form-control" placeholder="用户名" 
		           name="emp_no"  required autofocus/>
		<input type="password" class="form-control" name="emp_pass"  placeholder="密码" />
		<input class="btn btn-lg btn-warning btn-block" type="submit"></input>
		<label class="control-label"><br><div id="flag" style="color:red">${desc}</div></label>
	</form>
</div>
</body>
</html>
