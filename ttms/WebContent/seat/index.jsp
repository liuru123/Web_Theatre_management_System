<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@  page import="xupt.se.ttms.model.Seat"%>
<%@ page import="xupt.se.ttms.model.Studio"%>
<%@ page import="java.util.ArrayList"%>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>
<html>
<head>
<link href="../css/seat.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>座位管理</title>
<style>
body {
 width: 100%
}

.table th, .table td {
 text-align: center;
 height: 30px;
}
</style>
</head>
<body>
	<div
		style="height: 105px; inline-height: 75px; font-size: 25px; vertical-align: middle; text-align: center;"
		class="bg-primary">
		<img src="../images/横版logo.png">
	</div>

	<div class="row" style="padding: 10px 10px">
		<!-- 左导航栏 -->
		<%@ include file="../nav.jsp"%>
		<div class="col-md-10" style="padding-top: 10px">

			<!-- 查询块 -->
			<form class="form-inline" name="MyForm"
				action="SeatServlet?method=searchByStudio" method="post">
				<input type="text" class="form-control" name="studio_id"
					value="${search_studio_id}" /> <input type="submit" class="btn btn-danger"
					value="查 询" />&nbsp;&nbsp;
			</form>
		</div>
		<!-- 显示影厅的座位信息 -->
		<div style="padding-top: 10px">
			<form class="center" name="MyForm"
				action="SeatServlet?method=update&studio_id=${search_studio_id }"
				method="post">
				<div>
					<%
					    ArrayList<Seat> list = (ArrayList<Seat>) request.getAttribute("list");
					   Studio  studio =(Studio) request.getAttribute("studio");
					    if(list != null && list.size() > 0)
					    {

					        for(int i = 0; i < list.size(); i++)
					        {
					            if(list.get(i).getSeat_status() == 1)
					            {
					%>

					<input type="submit" style="border: none" class="back" name="seat"
						value="<%=list.get(i).getSeat_id()%>">
					<%
					    }
					            else
					            {
					%>

					<input type="submit" style="border: none" class="back1" name="seat"
						value="<%=list.get(i).getSeat_id()%>">
					<%
					    }
					            if((i + 1) % studio.getStudio_col_count()==0)
					            {
					                out.print("<br>");
					                out.print("<br>");

					            }
					        }
					    }
					%>
					<div class="bord"  style="text-align:center">
					<div  class="label1">
						<label>可用</label> <input type="text"
							value=<%=(request.getAttribute("use") == null) ? "" : request.getAttribute("use")%>>
							</div>
							<div  class="label2">
						<label > 不可用</label> <input type="text"
							value=<%=(request.getAttribute("unuse") == null) ? "" : request.getAttribute("unuse")%>>
							</div>
					</div>
				</div>
			</form>

		</div>
	</div>
</body>
</html>