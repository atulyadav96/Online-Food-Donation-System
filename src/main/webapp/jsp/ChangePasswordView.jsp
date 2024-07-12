<%@page import="online.food.donation.utility.ServletUtility"%>
<%@page import="online.food.donation.controller.ChangePasswordCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="Header.jsp"%>
 <br>
  <main class="login-form">
  <div class="cotainer">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">
           Change Password
            <h6 style="color: red;"><%= ServletUtility.getErrorMessage(request) %></h6>
            <h6 style="color: green;"><%= ServletUtility.getSuccessMessage(request)%></h6>
          </div>
          <div class="card-body">
            <form action="<%=OFDView.CHANGE_PASSWORD_CTL%>" method="post">
              <div class="form-group row">
                <label for="email_address" 
                  class="col-md-4 col-form-label text-md-right">Enter Old Password<font color="red">*</font></label>
                <div class="col-md-6">
                  <input type="password" id="password"  class="form-control" placeholder="Enter Old Password"
                    name="oldpwd" value="" >
                    <font  color="red"></font>
                </div>
                </div>
                 <div class="form-group row">
                <label for="email_address" 
                  class="col-md-4 col-form-label text-md-right">Enter New Password<font color="red">*</font></label>
                <div class="col-md-6">
                  <input type="password" id="password"  class="form-control" placeholder="Enter New Password"
                    name="newPassword" value="" >
                    <font  color="red"></font>
                </div>
                </div>
                
              <div class="col-md-6 offset-md-4">
                <input type="submit" class="btn btn-primary" name="operation" value="<%=ChangePasswordCtl.OP_SAVE%>">
                
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  </main>
  <div style="margin-top: 170px">
    <%@ include file="Footer.jsp"%>
    </div>




</body>
</html>