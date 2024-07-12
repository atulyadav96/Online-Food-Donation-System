<%@page import="online.food.donation.controller.DonateListCtl"%>
<%@page import="online.food.donation.bean.DonateBean"%>
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
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<h2 align="center">View all Donation</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OFDView.DONATION_LIST_CTL%>" method="post">

		<%-- <table width="100%">
			<tr>
				<td align="center"><label>Name :</label> <input type="text"
					name="name" placeholder="Enter Name"
					value="<%=ServletUtility.getParameter("name", request)%>">
					&emsp;&emsp; <input type="submit" name="operation"
					value="<%=DonateListCtl.OP_SEARCH%>">&nbsp;&nbsp;&nbsp;<input
					type="submit" name="operation" value="<%=DonateListCtl.OP_RESET%>"></td>
			</tr>
		</table> --%>
		<br>

		<table class="ml-4 mr-4 table table-striped">
			<tr>
                <th scope="col">Donar Name</th>
				<th scope="col">Category</th>
				<th scope="col">Food</th>
				<th scope="col">Quantity</th>
				<!-- <th scope="col">NGO Name</th> -->
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
					DonateBean bean = (DonateBean) it.next();
			%>
			<tr>
                <td><%=bean.getDonarname()%></td>
				<td><%=bean.getCategory()%></td>
				<td><%=bean.getFood()%></td>
				<td><%=bean.getQuamtity()%></td>
			<%-- 	<td><%=bean.getNgoname()%></td> --%>

                
                <%if(user.getRoleId()==1){ %>
				<td><%-- <a class="btn btn-info"
					href="<%=OFDView.DONATION_LIST_CTL%>?id=<%=bean.getId()%>">Update</a> --%>

				<a class="btn btn-danger"
					href="<%=OFDView.DONATION_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
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