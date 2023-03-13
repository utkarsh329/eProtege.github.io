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

<c:url var="addUrl" value="/ctl/apply" />

<c:url var="addSearch" value="/ctl/apply/search" />

<c:url var="editUrl" value="/ctl/apply?id=" />

<br>
<nav aria-label="breadcrumb">
	<ol class="breadcrumb primary-color">
		<li class="breadcrumb-item"><a class="white-text" href="#">Home</a></li>
		<li class="breadcrumb-item active">Apply</li>
	</ol>
</nav>

<div class="col-md-5">
	<div class="form-area">
		<h4>Apply</h4>
	</div>
</div>

<b><%@ include file="businessMessage.jsp"%></b>
<hr>
<sf:form action="${addUrl}" method="post" modelAttribute="form" enctype="multipart/form-data">

	<br>
	<table class="table table-striped table-bordered table-sm" width="50%" >
		<thead>
			<tr>
				<th class="th-sm">Name</th>
				<td>${form.jobDto.name}</td>
				<th class="th-sm">Company Name</th>	
				<td>${form.jobDto.companyName}</td>		
			</tr>
			<tr>
			<th class="th-sm">Language</th>
			<td>${form.jobDto.language}</td>
			<th class="th-sm">Post Date</th>
			<td>${form.jobDto.postDate}</td>
			<tr>
				<th class="th-sm">Skills</th>
				<td>${form.jobDto.skills}</td>
				<th class="th-sm">Required Qualification</th>
				<td>${form.jobDto.reqQualifiation}</td>
			</tr>
			
			<tr>
				<th class="th-sm">Job Type</th>
				<td>${form.jobDto.jobType}</td>
				<th class="th-sm">Department</th>
				<td>${form.jobDto.department}</td>
			</tr>
			
			<tr>
				<th class="th-sm">Description</th>
				<td>${form.jobDto.description}</td>
				<th class="th-sm">Address</th>
				<td>${form.jobDto.address}</td>
			</tr>
		</thead>

		</tbody>
	</table>
	<s:bind path="file">
							<div class="form-group">
							<label >Upload Resume:</label> 
								<sf:input type="file" path="${status.expression}" placeholder="Select Resume File"
									class="form-control" />
								<font color="red"><sf:errors path="${status.expression}" /></font>
							</div>
						</s:bind>
	<div style="margin: 10px" class="row row-cols-4">
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation"
					class="btn btn-primary" value="Submit">
			</div>
		</div>
	</div>
</sf:form>