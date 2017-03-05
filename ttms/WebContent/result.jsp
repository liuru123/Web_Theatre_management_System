<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/newadd.css" rel="stylesheet">
</head>
<body>
<div style="height:105px;line-height:75px;font-size:25px;vertical-align: middle;text-align: center" class="bg-primary">  <img src="images/横版logo.png"></div>

<div class="row" style="padding:10px 10px">

 
<div class="col-md-10" style="padding-top:10px">
    <h1>提交结果</h1><br>
     用户头像:<img src="${user.userImage}"   alt=""   width="200px"><br>
    <span> Hello   用户编号:${user.emp_no }</span>
    </div>
    <a href="index.jsp">返回</a>
    </div>
</body>
</html>
