<%@page import="java.util.List"%>
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
<title>Donation Food</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
			List ngolist =(List)request.getAttribute("ngoName");
		%> 
<div class="container mt-2"
		style="position: relative; min-height: 72vh">
	<h3 class="text-center mt-5">Donation Food</h3>
	<hr>
	<form action="<%=OFDView.DONATION_CTL%>" method="post">
<h4 class="text-center mt-5" style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 class="text-center mt-5" style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
	
		<jsp:useBean id="bean" scope="request"
			class="online.food.donation.bean.DonateBean" />
		<input type="hidden" name="id" value="<%=bean.getId()%>"> 

		<div class="container mt-5">
		  <div class="row h-100 justify-content-center align-items-center">
			<%-- <div class="col-6">
				<label for="exampleInputEmail1" class="form-label">NGO
					Name</label> <input type="text" name="ngoname" class="form-control " placeholder="Enter NGO Name here..." readonly="readonly"
					value="" id="exampleInputEmail1" aria-describedby="emailHelp">
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("ngoname", request)%></div> --%>
			<div class="col-6">
				<label for="" class="form-label">Food Category</label>
				<input type="text" name="category" class="form-control" placeholder="Enter Category here..."
					>
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("category", request)%></div>
			</div>
			
				<div class="col-6">
				<label for="" class="form-label">Food</label>
				<input type="text" name="food" class="form-control" placeholder="Enter Food here..."
			>
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("food", request)%></div>
			</div>
			
			<div class="col-6">
				<label for="" class="form-label">Quantity</label> <input
					type="number" min="10" name="quantity" class="form-control" placeholder="Enter Quantity here..."
					>
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("quantity", request)%></div>
			</div>
			
			<div class="col-6">
				<label for="" class="form-label">NGO
					Name</label> <select name="ngoid" class="form-control">
						<%=HTMLUtility.getList("ngoid", String.valueOf(bean.getNgoid()), ngolist)%>
						 </select>
			
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("ngoid", request)%></div>
			 
			<%-- <div class="col-6">
				<label for="exampleInputEmail1" class="form-label">Address</label> <textarea cols="4" rows="4"
					type="text" name="address" class="form-control" placeholder="Enter Address here..."
					id="exampleInputEmail1" aria-describedby="emailHelp"></textarea>
			</div>
			<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></div>
			 --%>
			</div>
			<div class="container text-center mt-4">
				<input type="submit" class="btn btn-primary" name="operation"
					value="<%=RequestDonationCtl.OP_SAVE%>">
			</div>
			</div>
		</div>
	</form>
</div>
<%@include file="Footer.jsp"%>

</body>
</html>