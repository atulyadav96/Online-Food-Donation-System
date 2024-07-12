<%@page import="online.food.donation.controller.UserCtl"%>
<%@page import="online.food.donation.utility.HTMLUtility"%>
<%@page import="online.food.donation.controller.UserRegistrationCtl"%>
<%@page import="online.food.donation.utility.ServletUtility"%>
<%@page import="online.food.donation.utility.DataUtility"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Update</title>


</head>
<body>
<div class="site-wrap">
<%@ include file="Header.jsp" %>
 <div class="bg-light py-3">
      <div class="container">
        <div class="row">
          <div class="col-md-12 mb-0"> <strong class="text-black">User Update</strong></div>
        </div>
      </div>
    </div>  

    <div class="site-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h2 class="h3 mb-3 text-black">User Update</h2>
            
          </div>
          <div class="col-md-7">
			
            <form action="<%=OFDView.USER_CTL%>" method="post">
            
            <jsp:useBean id="bean" class="online.food.donation.bean.UserBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> 
              
              <div class="p-3 p-lg-5 border">
               <center><b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
               <b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
               </center>
               
               
                <div class="form-group row">
                  <div class="col-md-6">
                    <label for="c_fname" class="text-black">First Name <span class="text-danger">*</span></label>
                    <input type="text" class="form-control"  name="firstName" placeholder="Enter First Name" 
                    value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                    <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
                  </div>
                  <div class="col-md-6">
                    <label for="c_lname" class="text-black">Last Name <span class="text-danger">*</span></label>
                    <input type="text" class="form-control"  name="lastName" placeholder="Enter Last Name"
                    value="<%=DataUtility.getStringData(bean.getLastName())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Login Id <span class="text-danger">*</span></label>
                    <input type="email" class="form-control"  name="login" placeholder="Enter Login Id" 
                    value="<%=DataUtility.getStringData(bean.getLogin())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-6">
                    <label for="c_subject" class="text-black">Password<span class="text-danger">*</span></label>
                    <input type="password" class="form-control" name="password" placeholder="Enter Password"
                    value="<%=DataUtility.getStringData(bean.getPassword())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
                  </div>
              
                
             
                  <div class="col-md-6">
                    <label for="c_subject" class="text-black">Confirm Password<span class="text-danger">*</span></label>
                    <input type="password" class="form-control"  name="confirmPassword" placeholder="ReEnter Password "
                    value="<%=DataUtility.getStringData(bean.getPassword())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                  </div>
                </div>
                
                
               <%--  <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Date Of Birth<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="datepicker"  name="dob" placeholder="Select Date Of Birth"
                    value="<%=DataUtility.getDateString(bean.getDob())%>" >
                    <font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
                  </div>
                </div> --%>
                <%
							HashMap<String,String> map = new HashMap<String,String>();
							map.put("Male", "Male");
							map.put("Female", "Female");
						%> 
                 <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Gender<span class="text-danger">*</span></label>
                   <%=HTMLUtility.getList("gender",String.valueOf(bean.getGender()), map)%>
                   <font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
                  </div>
                </div>
                
                 <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Mobile No<span class="text-danger">*</span></label>
                    <input type="text" class="form-control"  name="mobile" placeholder="Enter 10 Digits mobile No."
                    value="<%=DataUtility.getStringData(bean.getMobileNo())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%></font>
                  </div>
                </div>
                
                  <div class="form-group row">
                  <div class="col-md-12">
                    <label for="c_email" class="text-black">Unique Id<span class="text-danger">*</span></label>
                    <input type="text" class="form-control"  name="uniqueid" placeholder="Enter Unique ID"
                    value="<%=DataUtility.getStringData(bean.getUnique_id())%>" >
                    <font color="red"> <%=ServletUtility.getErrorMessage("uniqueid", request)%></font>
                  </div>
                </div>
                
               

                
                <div class="form-group row">
                  <div class="col-lg-6">
                    <input type="submit" class="btn btn-primary btn-lg btn-block" name="operation" value="<%=UserCtl.OP_SAVE%>">
                  </div>
                  <div class="col-lg-6">
                    <input type="submit" class="btn btn-primary btn-lg btn-block" name="operation" value="<%=UserCtl.OP_RESET%>">
                  </div>
                </div>
              </div>
            </form>
          </div>
 </div>
      </div>
    </div>

<%@ include file="Footer.jsp" %>
</div>
</body>
</html>