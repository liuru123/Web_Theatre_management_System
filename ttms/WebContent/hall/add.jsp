<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/bootstrap.min.css" rel="stylesheet" />

<title>添加影厅</title>
<style>
.table th, .table td {
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

		<!-- 增加影厅信息 -->
		<div class="col-md-10" style="padding-top: 10px">
			<form class="form-horizontal" role="form" action="StudioServlet?method=add"
				method="post">
				
				
				<div class="form-group">
                    <label for="studio_id" class="col-sm-2 control-label">影厅ID</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="studio_id"
                            name="studio_id" placeholder="请输入影厅的id" pattern="[\d]{1,2}"
                            required="required" oninvalid="setCustomValidity(''请输入影厅的名称)"
                            oninput="setCustomValidity('')" />
                    </div>
                    </div>
				<div class="form-group">
					<label for="studio_name" class="col-sm-2 control-label">影厅名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="studio_name"
							name="studio_name" placeholder="请输入影厅的名称" pattern="[\u4e00-\u9fa5]{2,20}"
							required="required" oninvalid="setCustomValidity(''请输入影厅的名称)"
							oninput="setCustomValidity('')" />
					</div>
					</div>
					<div class="form-group">
						<label for="studio_row_count" class="col-sm-2 control-label">影厅座位行</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="studio_row_count"
								name="studio_row_count" placeholder="请输入影厅的座位行" pattern="[\d]{1,2}"
								required="required" oninvalid="setCustomValidity(''请输入影厅的座位行')"
								oninput="setCustomvalidity('')" />
						</div>
                        </div>
						<div class="form-group">
							<label for="studio_col_count" class="col-sm-2 control-label">
								影厅座位列</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="studio_col_count"
									name="studio_col_count" placeholder="请输入影厅座位列数" pattern="[\d]{1,2}"
									required="required" oninvalid="setCustomvalidity(''请输入影厅的座位列')"
									oninput="setCustomValidity('')" />
							</div>
                            </div>
							<div class="form-group">
								<label for="studio_introduction" class="col-sm-2 control-label">
									影厅简介</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="studio_introduction"
										name="studio_introduction" placeholder="请输入影厅介绍"
										pattern="[\u4e00-\u9fa5]{10,30}" required="required"
										oninvalid="setCustomvalidity(''请输入影厅的介绍')"
										oninput="setCustomValidity('')" />
								</div>
                                </div>
                                
								<div class="form-group">
									<label for="studio_flag" class="col-sm-2 control-label"> 影厅状态</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="studio_flag"
											name="studio_flag" placeholder="请输入影厅的状态" pattern="[\d]{1}"
											required="required" oninvalid="setCustomvalidity(''请输入影厅的状态(0,1)')"
											oninput="setCustomValidity('')" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2 control-label"
										style="color: red; font-weight: bold">${result }</div>
									<div class="col-sm-6">
										<button type="submit" class="btn btn-primary">提交</button>
									</div>
								</div>
			</form>
		</div>
	</div>
</body>
</html>
