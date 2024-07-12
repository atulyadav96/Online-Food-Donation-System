<%@page import="online.food.donation.controller.UpdateRequestFoodCtl"%>
<%@page import="online.food.donation.utility.DataUtility"%>
<%@page import="online.food.donation.controller.RequestDonationCtl"%>
<%@page import="online.food.donation.controller.UserRegistrationCtl"%>
<%@page import="online.food.donation.utility.HTMLUtility"%>
<%@page import="online.food.donation.utility.ServletUtility"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request Food</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
<div class="container mt-2"
		style="position: relative; min-height: 72vh">
	<h3 class="text-center mt-5">Request Food</h3>
	<hr>
	<form action="<%=OFDView.UPDATE_REQUEST_FOOD_CTL%>" method="post">
<h4 class="text-center mt-5" style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 class="text-center mt-5" style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
	
		<jsp:useBean id="bean" scope="request"
			class="online.food.donation.bean.RequestFoodBean" />
		<input type="hidden" name="id" value="<%=bean.getId()%>"> 

		<div class="container mt-5">
		  <div class="row h-100 justify-content-center align-items-center">
			<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">NGO
					Name</label> <input type="text" name="ngoname" class="form-control " placeholder="Enter NGO Name here..." readonly="readonly"
					value="<%=DataUtility.getStringData(bean.getNgoName())%>" id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("ngoname", request)%></div>
			</div>
			<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Food Category</label>
				<input type="text" name="category" class="form-control" placeholder="Enter Category here..."
					value="<%=DataUtility.getStringData(bean.getCategory())%>"id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("category", request)%></div>
			</div>
			
				<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Food</label>
				<input type="text" name="food" class="form-control" placeholder="Enter Food here..."
					value="<%=DataUtility.getStringData(bean.getFoodname())%>" id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("food", request)%></div>
			</div>
			
			<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Quantity</label> <input
					type="number" min="10" name="quantity" class="form-control" placeholder="Enter Quantity here..."
					value="<%=DataUtility.getStringData(bean.getQuantity())%>"id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%></div>
			</div>
			
			<%if(user.getRoleId()==2){ %>
			
					<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Sender Name</label>
				<input type="text" name="sendername" class="form-control" placeholder="Enter Food here..."
				value="<%=DataUtility.getStringData(user.getFirstName()) %>"	id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("sendername", request)%></div>
			</div>
			
			<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Sender Contact No</label> <input
					type="number" min="10" name="contactno" class="form-control" placeholder="Enter Conatct No here..."
					value="<%=DataUtility.getStringData(user.getMobileNo()) %>"id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("contactno", request)%></div>
			</div>
			
			
			<%}else{ %>
			
			<%} %>
			 
			<div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Address</label> <textarea cols="4" rows="4"
					type="text" name="address" class="form-control" placeholder="Enter Address here..."
					id="exampleInputEmail1" aria-describedby="emailHelp"><%=DataUtility.getStringData(bean.getAddress())%></textarea>
			</div>
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></div>
			
			
			<div class="container text-center mt-4">
				<input type="submit" class="btn btn-primary" name="operation"
					value="<%=UpdateRequestFoodCtl.OP_SAVE%>">
			</div>
			</div>
		</div>
	</form>
</div>
<%@include file="Footer.jsp"%>

</body>
</html>