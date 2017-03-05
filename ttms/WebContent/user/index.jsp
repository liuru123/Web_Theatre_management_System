<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="xupt.se.ttms.model.User" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登陆信息管理</title>
<style>
body {width:100%}
.table th, .table td {  
text-align: center;
height:30px;
} 
</style>
</head>
<body>

<div style="height:105px;line-height:75px;font-size:25px;vertical-align: middle;text-align: center" class="bg-primary">  <img src="../images/横版logo.png"></div>

<div class="row" style="padding:10px 10px">

	<!-- 左导航栏 -->
	<%@include file="../nav.jsp" %>
    
	<div class="col-md-10" style="padding-top:10px">
	
		<!-- 查询块 -->
		<div class="search" >
			<form class="form-inline" name="myForm" action="UserServlet?method=search" method="post">
				<!-- <input type="hidden" name="method" value="search"/> -->
				<input type="text" class="form-control" name="emp_no" value="${search_emp_no}"/>
				<input type="submit" class="btn btn-primary" value="查   询" />&nbsp;&nbsp;
				<input type="button" class="btn btn-danger" value="增   加" onclick="javascript:window.location='add.jsp'"/>
			</form>
		</div>
		
	
		<!-- 员工信息显示-->
		<div style="padding-top:10px;">
			<table class="table table-bordered table-hover" style="text-align:center;">
		   		<tr>
		   			<th>编号</th>
		   			<th>密码</th>
		   			<th>用户类型</th>
		   			<th colspan=2>操作</th>
		   		</tr>
		   		<%
		   		 ArrayList <User> list=(ArrayList<User>) request.getAttribute("list");
		   		if(list!=null){
		   		   for(int i =0;i<list.size();i++){
		   		       
		   		 
		   		%>
		   		<tr>
		   			<th><%=list.get(i).getEmp_no()%></th>
		   			<th><%=list.get(i).getEmp_pass() %></th>
		   			<th><%=list.get(i).getUser_type()==1?"管理员":"普通用户" %></th>
		   			<th><a href="UserServlet?method=searchById&emp_no=<%=list.get(i).getEmp_no() %>&user_type=<%=list.get(i).getUser_type()%>">修改</a></th><!-- 管理员的修改应该是修改公司员工的相关信息和普通用户的相关信息 -->
		   			<th><a href="UserServlet?method=delete&emp_no=<%=list.get(i).getEmp_no()%>">删除</a></th>      <!-- 管理员的删除应该是修改管理员的状态 -->
		   		</tr>
		   		<%	
		   		  }
		   		}
		   	 %>

			</table>
		</div>	
	</div>
</div>
</body>
</html>