<%@page import="online.food.donation.controller.ViewDonationCtl"%>
<%@page import="online.food.donation.bean.RequestFoodBean"%>
<%@page import="online.food.donation.controller.UserListCtl"%>
<%@page import="online.food.donation.utility.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
	<h2 align="center">View Donation for Requested Food</h2>
<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OFDView.VIEW_DONATION_CTL%>" method="post">

	<%-- 	<table width="100%">
			<tr>
				<td align="center"><label>Name :</label> <input type="text"
					name="name" placeholder="Enter Name"
					value="<%=ServletUtility.getParameter("name", request)%>">
					&emsp;&emsp; <input type="submit" name="operation"
					value="<%=ViewDonationCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input
					type="submit" name="operation" value="<%=ViewDonationCtl.OP_RESET%>"></td>
			</tr>
		</table> --%>
		<br>

		<table class="ml-4 mr-4 table table-striped">
			<tr>

				<th scope="col">NGO Name</th>
				<th scope="col">Sender Name</th>
				<th scope="col">Category</th>
				<th scope="col">Food</th>
				<th scope="col">Quantity</th>
				<th scope="col">Sender Contact No</th>
				<th scope="col">Address</th>
				<%if(user.getRoleId()==1){ %>
				<th scope="col">Action</th>
				<%}else{ %>
					<%} %>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					RequestFoodBean bean = (RequestFoodBean) it.next();
			%>
			<tr>

				<td><%=bean.getNgoName()%></td>
				<%if(bean.getSendername()== null) {%>
				<td>-----------</td>
				<%}else{ %>
				<td><%=bean.getSendername()%></td>
				<%} %>
				<td><%=bean.getCategory()%></td>
				<td><%=bean.getFoodname()%></td>
				<td><%=bean.getQuantity()%></td>
				<%if(bean.getSendercontactno()==0) {%>
				<td>-----------</td>
				<%}else{ %>
					<td><%=bean.getSendercontactno()%></td>
					<%} %>
				<td><%=bean.getAddress()%></td>

   <%if(user.getRoleId()==1){ %>
				<td><%-- <a class="btn btn-info"
					href="<%=OFDView.VIEW_DONATION_CTL%>?id=<%=bean.getId()%>">Update</a> --%>

				<a class="btn btn-danger"
					href="<%=OFDView.VIEW_DONATION_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
					<%}else{ %>
					<%} %>
				<%
					}
				%>
			</tbody>
		</table>


	</form>
	<br>
	<hr>
	<%@include file="Footer.jsp"%>
</body>
</html>