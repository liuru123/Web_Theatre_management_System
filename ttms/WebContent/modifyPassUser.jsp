<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    request.setAttribute("contextPath", contextPath);
%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改密码上传头像</title>
<style>
.table th, .table td {
    text-align: center;
    height: 30px;
}
</style>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/newadd.css" rel="stylesheet">
</head>
<body>

<div style="height:105px;line-height:75px;font-size:25px;vertical-align: middle;text-align: center" class="bg-primary">  <img src="images/横版logo.png"></div>

    <div class="row" style="padding:10px 10px">

       
        <!-- 增加普通用户的信息 -->
        <div class="col-md-10" style="padding-top:10px">
         <form class="form-horizontal" role="form" action="modifyPassUser"  enctype="multipart/form-data"  method="post">
               
            <div class="form-group">
                    <label for="emp_no" class="col-sm-2 control-label">用户编号</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="emp_no" name="emp_no" 
                            pattern="[0-9]{6,20}" 
                            required="required" oninvalid="setCustomValidity('请输入数字,长度6-20位!')" 
                            oninput="setCustomValidity('')"  value="${user.emp_no }">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="emp_pass" class="col-sm-2 control-label">旧密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control"  id="emp_pass"  name="emp_pass"
                            pattern="[0-9A-Za-z]{6,13}" 
                            required="required" oninvalid="setCustomValidity('请输入用户密码!')"
                            oninput="setCustomValidity('')"  value="${user.emp_pass }">
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="emp_password" class="col-sm-2 control-label">新密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="emp_password" name="emp_password"
                          pattern="[0-9A-Za-z]{6,13}" 
                            required="required" oninvalid="setCustomValidity('请输入用户密码!')"
                            oninput="setCustomValidity('')">
                    </div>
                </div>
                
                
                <div class="form-group">
                    <label for="user_type" class="col-sm-2 control-label">用户类型</label>
                    <div class="col-sm-6">
                        <input type="text"  disabled="disabled"    class="form-control" id="type" name="user_type"  value ="0">
                    </div>
                </div>
              <div class="form-group">
             <input type="file"  name="userImage">
              </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label" style="color:red;font-weight: bold;">${result}</div>
                    <div class="col-sm-6">
                        <!-- <input type="hidden" name="method" value="add"> -->
                        <button type="submit" class="btn btn-primary">提  交</button>                     
                    </div>
                </div>
                <a href="index">返回</a>
            </form>
    
        </div>
    </div>


<!-- 用户用来修改密码与上传头像的界面 -->
</body>
</html>