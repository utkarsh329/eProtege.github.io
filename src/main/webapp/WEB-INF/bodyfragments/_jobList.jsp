<%@page import="in.co.job.portal.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/job" />

<c:url var="addSearch" value="/ctl/job/search" />

<c:url var="editUrl" value="/ctl/job?id=" />

<c:url var="applyUrl" value="/ctl/apply?jId=" />
<c:url var="awUrl" value="/ctl/apwresume?jId=" />

<br>
<nav aria-label="breadcrumb">
	<ol class="breadcrumb primary-color">
		<li class="breadcrumb-item"><a class="white-text" href="#">Home</a></li>
		<li class="breadcrumb-item active">Job List</li>
	</ol>
</nav>

<div class="col-md-5">
	<div class="form-area">
		<h4>Job List</h4>
	</div>
</div>
<b><%@ include file="businessMessage.jsp"%></b>
<hr>
<sf:form action="${addSearch}" method="post" modelAttribute="form">

	<div class="container">
		<div class="row row-cols-4">
			<div class="col">
				<s:bind path="name">
					<div class="input-group">
						<sf:input path="name" type="text" placeholder="Name"
							class="form-control" />
					</div>
				</s:bind>
			</div>
			<div class="col">
				<s:bind path="companyName">
					<div class="input-group">
						<sf:input path="companyName" type="text" placeholder="Company Name"
							class="form-control" />
					</div>
				</s:bind>
			</div>
				<div class="col">
				<s:bind path="language">
					<div class="input-group">
						<sf:input path="language" type="text" placeholder="Language"
							class="form-control" />
					</div>
				</s:bind>
			</div>
		
			<div class="col">
				<div class="input-group">

					<input type="submit" name="operation" class="btn btn-md btn-info"
						value="Search">&nbsp;&nbsp; <input type="submit"
						name="operation" class="btn btn-md btn-info" value="Reset">
				</div>
			</div>
		</div>
	</div>

	<br>

	<sf:input type="hidden" path="pageNo" />
	<sf:input type="hidden" path="pageSize" />

	<sf:input type="hidden" path="listsize" />
	<sf:input type="hidden" path="total" />
	<sf:input type="hidden" path="pagenosize" />

	<table class="table table-striped table-bordered table-sm" width="99%">
		<thead>
			<tr>
				<%UserDTO uDto=(UserDTO)session.getAttribute("user");%>
				
				<%if(uDto.getRoleId()==2)
				{
				%>
				<th class="th-sm"><input type="checkbox" id="selectall">Select All</th>
				<%
					} %>
				<th class="th-sm">Name</th>
				<th class="th-sm">Company Name</th>
				<th class="th-sm">Language</th>
				<th class="th-sm">Post Date</th>
				<th class="th-sm">Skills</th>
				<th class="th-sm">Required Qualification</th>
				<th class="th-sm">Description</th>
				<th class="th-sm">Address</th>
				<%if(uDto.getRoleId()==2){%>
				<th class="th-sm">Edit</th>
				<%}else{ %>
				<th class="th-sm">Apply</th>
				<%} %>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="jb" varStatus="job">
				<tr>
				<%if(uDto.getRoleId()==2){%>
					<td><input type="checkbox" class="case" name="ids"
						value="${jb.id}"></td>
						<%} %>
					<td><c:out value="${jb.name}" /></td>
					<td><c:out value="${jb.companyName}" /></td>
					<td><c:out value="${jb.language}" /></td>
					<td><c:out value="${jb.postDate}" /></td>
					<td><c:out value="${jb.skills}" /></td>
					<td><c:out value="${jb.reqQualifiation}" /></td>
					<td><c:out value="${jb.description}" /></td>
					<td><c:out value="${jb.address}" /></td>
					<%if(uDto.getRoleId()==2){%>
					<td><a href="${editUrl}${jb.id}" class="btn btn-primary">Edit</a>
					</td>
					<%}else{ %>
					<td><a href="${applyUrl}${jb.id}" class="btn btn-primary">Apply</a>
					
						<a href="${awUrl}${jb.id}" class="btn btn-primary">Apply Without Resume</a></td>
					<%} %>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div style="margin: 10px" class="row row-cols-4">
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation"
					<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
					class="btn btn-primary" value="Previous">
			</div>
		</div>
		<%if(uDto.getRoleId()==2)
		{%>
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation"
					<c:if test="${listsize== 0}">disabled="disabled"</c:if>
					class="btn btn-primary" value="Delete">
			</div>
		</div>
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation" class="btn btn-primary"
					value="New">
			</div>
		</div>
		<%} %>
		<div class="col">
			<div class="input-group">

				<input type="submit" name="operation"
					<c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
					class="btn btn-primary" value="Next">
			</div>
		</div>
	</div>
</sf:form>