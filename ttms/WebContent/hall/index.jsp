<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="xupt.se.ttms.model.Studio"%>
<%@ page import="java.util.ArrayList"%>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>演出厅管理</title>
<style>
body {
 width: 100%
}

.table td, .table th {
 text-align: center;
 height: 30px;
}
</style>
</head>
<body>
	<div
		style="height: 105px; inline-height: 75px; font-size: 25px; vertical-align: middle; text-align: center;"
		class="bg-primary">  <img src="../images/横版logo.png"></div>

	<div class="row" style="padding: 10px 10px">
		<!-- 左导航栏 -->
		<%@ include file="../nav.jsp"%>
		<div class="col-md-10" style="padding-top: 10px">

			<!-- 查询块 -->
			<div class="search">
				<form class="form-inline" name="Myform"
					action="StudioServlet?method=searchByPage" method="post">
					<input type="text" class="form-control" name="studio_name"
						value="${search_studio_name }" /> <input type="submit"
						class="btn btn-primary" value="查询" />&nbsp;&nbsp; <input type="button"
						class="btn btn-danger" value="增加"
						onclick="javascript:window.location='add.jsp' " />
				</form>
			</div>

			<!-- 影厅信息显示 -->
			<div style="padding-top: 10px">
				<table class="table table-bordered table-hover" style="text-align: center;">
					<tr>
						<th>影厅ID</th>
						<th>影厅名称</th>
						<th>影厅行数</th>
						<th>影厅列数</th>
						<th>影厅介绍</th>
						<th>影厅标识</th>
						<th colspan=2>操作</th>
					</tr>
					<%
					    int currentPage = 1;
					    int allCount = 0;
					    int allPageCount = 0;
					    Studio studio = null;
					    if(request.getAttribute("allStudio") != null)
					    {
					        currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
					        ArrayList<Studio> list = (ArrayList<Studio>) request.getAttribute("allStudio");
					        allCount = ((Integer) request.getAttribute("allCount")).intValue();
					        allPageCount = ((Integer) request.getAttribute("allPageCount")).intValue();
					        if(list != null && list.size() > 0)
					        {
					            for(int i = 0; i < list.size(); i++)
					            {
					                if(i % 2 == 0)
					                    out.println("<tr class='success'>");
					                else
					                    out.println("<tr class='active'> ");
					%>
					<tr>
						<th><%=list.get(i).getStudio_id()%></th>
						<th><%=list.get(i).getStudio_name()%></th>
						<th><%=list.get(i).getStudio_row_count()%></th>
						<th><%=list.get(i).getStudio_col_count()%></th>
						<th><%=list.get(i).getStudio_introduction()%></th>
						<th><%=list.get(i).getStudio_flag()%></th>
						<th><a
							href="StudioServlet?method=searchById&studio_id=<%=list.get(i).getStudio_id()%>">修改</a></th>
						<th><a
							href="StudioServlet?method=delete&studio_id=<%=list.get(i).getStudio_id()%>&studio_name=${search_studio_name }&currentPage=${currentPage} ">删除</a></th>
					</tr>
					<%
					    }
					        }
					    }
					%>
				</table>
			</div>
			<!-- 分页 -->

			<div class="text-center">
				<nav>
					<ul class="pagination">
						<li><a
							href="StudioServlet?method=searchByPage&currentPage=1&studio_name=${search_studio_name }">首页</a></li>
						<li><a
							href="StudioServlet?method=searchByPage&currentPage=<%=(currentPage-1)<1?1:(currentPage-1) %>&studio_name=${search_studio_name}">上一页</a></li>
						<li><a
							href="StudioServlet?method=SearchByPage&currentPage=<%=(currentPage+1)>allPageCount? allPageCount:(currentPage+1) %>&studio_name=${search_studio_name}">下一页</a></li>
						<li><a
							href="StudioServlet?method=SearchByPage&currentPage=<%= allPageCount %>&studio_name=${search_studio_name}">末页</a></li>
					</ul>
				</nav>

			</div>
		</div>
	</div>
</body>
</html>