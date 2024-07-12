<%@page import="online.food.donation.bean.RequestFoodBean"%>
<%@page import="online.food.donation.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp"%>
<%UserBean user = (UserBean)session.getAttribute("user");%>
	<div class="container-fluid mt-2" style="position: relative; min-height: 72vh">
	
	<h2 class="text-center mt-4">Requested Food</h2>
	<form action="" method="post">
	
			
			                  

	
	
<h5 style="color: red;" class="text-center"><%=ServletUtility.getErrorMessage(request)%></h5>
<h5 style="color: green;" class="text-center"><%=ServletUtility.getSuccessMessage(request)%></h5>
<div class="row mt-2 mx-2 mb-4">

		        	<%
						int index = 1;
						List list = ServletUtility.getList(request);
						Iterator it = list.iterator();
						while (it.hasNext()) {
							RequestFoodBean bean = (RequestFoodBean) it.next();
					%>
					
					
					
						<div class="col-md-3 mt-4">
						<div class="text-center">
						<div class="card-header py-3 bg-footer">
						<h4 class="my-0 fw-normal"><%=bean.getNgoName()%></h4>
					</div>
							<div class="card-body">
						<h1 class="card-title pricing-card-title">Quantity:
							<%=bean.getQuantity()%></h1>
						<ul class="list-unstyled mt-3 mb-4">
							<li><%=bean.getFoodname()%></li>
							<li><%=bean.getCategory()%></li>
							<li><%=bean.getAddress()%></li>

						</ul>
						<%if(bean.getStatus().equalsIgnoreCase("Request")) {%>
						<a href="<%=OFDView.UPDATE_REQUEST_FOOD_CTL%>?id=<%=bean.getId()%>" class="w-100 btn btn-lg btn-outline-danger">Click
							Here to Donate</a>
						<%}else{ %>	
							<a href="#" class="w-100 btn btn-lg btn-outline-success">Donated Successfully</a>
						<%} %>
					</div>
							</div>
						</div>
					
			<%
			}
		%>
						</div>
	</form>
	</div>
	<%@include file="Footer.jsp"%>
</body>
</html>