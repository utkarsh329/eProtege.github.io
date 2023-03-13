<%@page import="in.co.job.portal.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<nav class="navbar navbar-expand-lg navbar-dark primary-color">
	<a class="navbar-brand" href="#">Job Portal</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">

			<li class="nav-item active"><a class="nav-link"
				href="<c:url value="/welcome"/>">Home <span class="sr-only">(current)</span></a>
			</li>

			<%
				UserDTO userBean = (UserDTO) session.getAttribute("user");
			boolean userLoggedIn = userBean != null;
			%>
			<%
				if (userLoggedIn) {
			%>
			<%
				if (userBean.getRoleId() == 1) {
			%>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Recruiter </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/ctl/recruiter">Add  Recruiter</a> <a
						class="dropdown-item" href="${pageContext.request.contextPath}/ctl/recruiter/search"> Recruiter List</a>
				</div></li>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/user/search"/>">User List</a></li>
			<%
				} else if (userBean.getRoleId() == 2) {
			%>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Job </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/ctl/job">Add  Job</a> <a
						class="dropdown-item" href="${pageContext.request.contextPath}/ctl/job/search">Job List</a>
				</div></li>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/apply/search"/>">Apply Jobs</a></li>
			<%
				} else if (userBean.getRoleId() == 3) {
			%>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/job/search"/>">Jobs</a></li>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/apply/search"/>">Apply Jobs</a></li>
			</li>
			<li class="nav-item "><a class="nav-link"
				href="<c:url value="/ctl/application"/>">Application</a></li>
			<%
				}
			%>

		
			<%
				}
			%>
		</ul>


	</div>


	<ul class="nav justify-content-end">


		<%
			if (userLoggedIn) {
		%>
		
	
			<li class="nav-item dropdown"><a style="color: #000000"
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">Hi, <img width="30px" height="30px" src="${pageContext.request.contextPath}/getImage/<c:out value="<%=userBean.getId()%>"/>"> <%=userBean.getFirstName()%></a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
			
				<a class="dropdown-item" href="<c:url value="/profile"/>">My Profile</a> 
					<a class="dropdown-item" href="<c:url value="/changepassword"/>">Change Password</a> 
					<a class="dropdown-item" href="<c:url value="/login"/>">Logout</a> 
				</div></li>
	
		
		<%
			} else {
		%>
		<li class="nav-item"><a class="nav-link" style="color: #000000"
			href="<c:url value="/login"/>">Sign In</a></li>
		<li class="nav-item "><a class="nav-link" style="color: #000000"
			href="<c:url value="/signUp"/>">Sign Up</a></li>
		<%
			}
		%>

	</ul>

</nav>

