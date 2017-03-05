<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="xupt.se.ttms.model.Studio" %>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>修改影厅信息</title>
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
        class="bg-primary">    <img src="../images/横版logo.png"></div>
    <div class="row" style="padding: 10px 10px">
        <!-- 左导航栏 -->
        <%@ include file="../nav.jsp"%>

        <!-- 修改影厅信息 -->
        <div class="col-md-10" style="padding-top: 10px">
            <form class="form-horizontal" role="form" action="StudioServlet?method=update"
                method="post">
                <input type="hidden" name="studio_id"  value="${studio.studio_id }" >
                
                <div class="form-group">
                    <label for="studio_name" class="col-sm-2 control-label">影厅名称</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="studio_name"
                            name="studio_name" placeholder="请输入影厅的名称" pattern="[\u4e00-\u9fa5]{2,20}"
                            required="required" oninvalid="setCustomValidity(''请输入影厅的名称)"
                            oninput="setCustomValidity('')"   value="${studio.studio_name }" />
                    </div>
                    </div>
                    <div class="form-group">
                        <label for="studio_row_count" class="col-sm-2 control-label">影厅座位行</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="studio_row_count"
                                name="studio_row_count" placeholder="请输入影厅的座位行" pattern="[\d]{1,2}"
                                required="required" oninvalid="setCustomValidity(''请输入影厅的座位行')"
                                oninput="setCustomvalidity('')"  value="${studio.studio_row_count }" />
                        </div>
                        </div>
                        <div class="form-group">
                            <label for="studio_col_count" class="col-sm-2 control-label">
                                影厅座位列</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="studio_col_count"
                                    name="studio_col_count" placeholder="请输入影厅座位列数" pattern="[\d]{1,2}"
                                    required="required" oninvalid="setCustomvalidity(''请输入影厅的座位列')"
                                    oninput="setCustomValidity('')"   value="${studio.studio_col_count}"/>
                            </div>
                            </div>
                            <div class="form-group">
                                <label for="studio_introduction" class="col-sm-2 control-label">
                                    影厅介绍</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="studio_introduction"
                                        name="studio_introduction" placeholder="请输入影厅介绍"
                                        pattern="[\u4e00-\u9fa5]{10,30}" required="required"
                                        oninvalid="setCustomvalidity(''请输入影厅的介绍')"
                                        oninput="setCustomValidity('')"   value="${studio.studio_introduction}"/>
                                </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="studio_flag" class="col-sm-2 control-label"> 影厅标识</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="studio_flag"
                                            name="studio_flag" placeholder="请输入影厅的状态" pattern="[\d]{1}"
                                            required="required" oninvalid="setCustomvalidity(''请输入影厅的状态(0,1)')"
                                            oninput="setCustomValidity('')"  value="${studio.studio_flag }"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-2 control-label" 
                                        style="color: red; font-weight: bold">${result }</div>
                                    <div class="col-sm-6">
                                        <button type="submit" class="btn btn-primary">修 改</button>
                                    </div>
                                </div>
            </form>
        </div>
    </div>
</body>
</html>
