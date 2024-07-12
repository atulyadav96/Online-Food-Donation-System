<%@page import="online.food.donation.model.NGOModel"%>
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
	<h2 align="center">NGO List</h2>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>


		<table class="table table-striped">
			<tr>
				<th scope="col">NGO Name</th>
				<th scope="col">Owner First Name</th>
				<th scope="col">Owner Last Name</th>
				<th scope="col">Email</th>
				<th scope="col">PhoneNo</th>
				<th scope="col">Gender</th>
				<th scope="col">Unique ID Proof</th>
				<th scope="col">Action</th>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					UserBean bean = (UserBean) it.next();
			%>
			<%
				NGOModel nmodel = new NGOModel();
					int index1 = 1;
					List list1 = nmodel.NGONamelist(bean.getId());
					Iterator it1 = list1.iterator();
					while (it1.hasNext()) {
						UserBean bean1 = (UserBean) it1.next();
			%>
			<tr>
				<td><%=bean1.getNgoname()%></td>
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getLogin()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getUnique_id()%></td>

				<%-- <td><a class="btn btn-info"
					href="<%=OFDView.USER_CTL%>?id=<%=bean.getId()%>">Update</a></td> --%>

				<td><a class="btn btn-danger"
					href="<%=OFDView.NGO_LIST_CTL%>?id=<%=bean.getId()%>&userid=<%=bean1.getUserid()%>">Delete</a></td>
				<%
					}
					}
				%>
			
			</tbody>
		</table>

	<%@include file="Footer.jsp"%>
</body>
</html>