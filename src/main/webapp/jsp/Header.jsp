<%@page import="online.food.donation.controller.LoginCtl"%>
<%@page import="online.food.donation.controller.OFDView"%>
<%@page import="online.food.donation.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
   <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
<link rel="stylesheet" href="/OnlineFoodDonation/fonts/icomoon/style.css">

    <link rel="stylesheet" href="/OnlineFoodDonation/css/bootstrap.min.css">
    <link rel="stylesheet" href="/OnlineFoodDonation/css/magnific-popup.css">
    <link rel="stylesheet" href="/OnlineFoodDonation/css/jquery-ui.css">
    <link rel="stylesheet" href="/OnlineFoodDonation/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/OnlineFoodDonation/css/owl.theme.default.min.css">


    <link rel="stylesheet" href="/OnlineFoodDonation/css/aos.css">

    <link rel="stylesheet" href="/OnlineFoodDonation/css/style.css">
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
  } );
  </script>	
    
</head>
<body>

<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += "("+userBean.getRolename()+")";
    } else {
        welcomeMsg += "Guest";
    }

%>
   <header class="site-navbar" role="banner">
      <div class="site-navbar-top">
        <div class="container">
          <div class="row align-items-center">

            <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
            
            </div>

            <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
              <div class="site-logo">
                <a href="<%=OFDView.WELCOME_CTL%>" class="js-logo-clone">Online Food Donation</a>
              </div>
            </div>

            <div class="col-6 col-md-4 order-3 order-md-3 text-right">
              <div class="site-top-icons">
                <ul>
                            <%if(userBean == null){ %>
                            
                                  <li><a href="<%=OFDView.LOGIN_CTL%>"><b>Login</b></a></li>
                                 <%--  <li><a href="<%=OFDView.USER_REGISTRATION_CTL%>">SignUp</a></li> --%>
                                  
                                  <%}else{ %>
                                  
                
                <%if(userLoggedIn){ %>
                 
               	<%if(userBean.getRoleId()==1){ %>
               	 	<li><a href="<%=OFDView.CHANGE_PASSWORD_CTL%>"><span class="glyphicon glyphicon-log-out"></span>Change Password</a></li>
               	   <li class="nav-item"><a class=""><span class=""></span><%=welcomeMsg%></a></li>
               	<li><a href="<%=OFDView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><span class="glyphicon glyphicon-log-out"></span>LogOut</a></li>
                  <%}else if(userBean.getRoleId()==2) {%>
                     <li><a href="<%=OFDView.CHANGE_PASSWORD_CTL%>"><span class="glyphicon glyphicon-log-out"></span>Change Password</a></li>
                 <li class="nav-item"><a class=""><span class=""></span><%=welcomeMsg%></a></li>
                     	<li><a href="<%=OFDView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><span class="glyphicon glyphicon-log-out"></span>LogOut</a></li>
                  <%}else{%>
                  <li><a href="<%=OFDView.CHANGE_PASSWORD_CTL%>"><span class="glyphicon glyphicon-log-out"></span>Change Password</a></li>
                  <li class="nav-item"><a class=""><span class=""></span><%=welcomeMsg%></a></li>
                                       	<li><a href="<%=OFDView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><span class="glyphicon glyphicon-log-out"></span>LogOut</a></li>
                  <%} }}%>
                  <li class="d-inline-block d-md-none ml-md-0"><a href="#" class="site-menu-toggle js-menu-toggle"><span class="icon-menu"></span></a></li>
                </ul>
              </div> 
            </div>

          </div>
        </div>
      </div> 
      <nav class="site-navigation text-right text-md-center" role="navigation">
        <div class="container">
          <ul class="site-menu js-clone-nav d-none d-md-block">
         
           <%if(userLoggedIn){ %>
           
           <%if(userBean.getRoleId()==1){ %>
            
           	<li><a href="<%=OFDView.WELCOME_CTL%>">Home</a></li>
             <li><a href="<%=OFDView.USER_LIST_CTL%>">User List</a></li>
             <li><a href="<%=OFDView.NGO_LIST_CTL%>">Ngo List</a></li>
                 <li><a href="<%=OFDView.DONATION_LIST_CTL%>">View Donation</a></li>
                   <li><a href="<%=OFDView.VIEW_DONATION_CTL%>">Track Requested Food</a></li>
              
            <%}else if(userBean.getRoleId()==2){ %>
            <li><a href="<%=OFDView.WELCOME_CTL%>">Home</a></li>
            <li><a href="<%=OFDView.REQUEST_FOOD_BY_NGO_CTL%>">View Requested Food</a></li>
            <li><a href="<%=OFDView.DONATION_CTL%>">Voluntary Donate Food</a></li>
             <li><a href="<%=OFDView.DONATION_LIST_CTL%>">Donation History</a></li>
            <%}else{ %>
               <li><a href="<%=OFDView.WELCOME_CTL%>">Home</a></li>
            <li><a href="<%=OFDView.REQUEST_DONATION_CTL%>">Request Food</a></li>
            <li><a href="<%=OFDView.DONATION_LIST_CTL%>">View Donation</a></li>
             <li><a href="<%=OFDView.VIEW_DONATION_CTL%>">Track Requested Food</a></li>
            
            <%}}%>
            
           
          </ul>
        </div>
      </nav>
    </header>
</body>
</html>