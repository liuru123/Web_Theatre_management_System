<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加普通的用户</title>
<style>
.table th, .table td {
    text-align: center;
    height: 30px;
}
</style>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/newadd.css" rel="stylesheet">
</head>
<body>
<div style="height:105px;line-height:75px;font-size:25px;vertical-align: middle;text-align: center" class="bg-primary">  <img src="../images/横版logo.png"></div>

    <div class="row" style="padding:10px 10px">

        <!-- 左导航栏 -->
        <%@include file="../nav.jsp"%>

        <!-- 增加普通用户的信息 -->
        <div class="col-md-10" style="padding-top:10px">
            <form class="form-horizontal" role="form" action="UserServlet?method=add" method="post">
                <div class="form-group">
                    <label for="emp_no" class="col-sm-2 control-label">用户编号</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="emp_no" name="emp_no" 
                            placeholder="请输入大小写字母和数字,长度6-20位" pattern="[0-9]{6,20}" 
                            required="required" oninvalid="setCustomValidity('请输入数字,长度6-20位!')" 
                            oninput="setCustomValidity('')" >
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="emp_pass" class="col-sm-2 control-label">用户密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="emp_pass" name="emp_pass"
                            placeholder="请输入用户密码" pattern="[0-9A-Za-z]{6,13}" 
                            required="required" oninvalid="setCustomValidity('请输入用户密码!')"
                            oninput="setCustomValidity('')">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="user_type" class="col-sm-2 control-label">用户类型</label>
                    <div class="col-sm-6">
                        <input type="text"  disabled="disabled"    class="form-control" id="type" name="user_type"  value =<%=request.getAttribute("user_type")==null?0:request.getAttribute("user_type") %>>
                    </div>
                </div>
              
                <div class="form-group">
                    <div class="col-sm-2 control-label" style="color:red;font-weight: bold;">${result}</div>
                    <div class="col-sm-6">
                        <!-- <input type="hidden" name="method" value="add"> -->
                        <button type="submit" class="btn btn-primary">提  交</button>                     
                    </div>
                </div>
            </form>

        </div>
    </div>
</body>
</html>