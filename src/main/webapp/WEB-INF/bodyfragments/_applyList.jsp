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
      <li class="breadcrumb-item active">Apply Job List</li>
    </ol>
  </nav>
	
<div class="col-md-5">
    <div class="form-area"> 
<h4>Apply Job List</h4>
</div>
</div>
<b><%@ include file="businessMessage.jsp"%></b>
<hr>

<sf:form action="${addSearch}" method="post" modelAttribute="form">

	<div class="container">
		<div class="row row-cols-4">
			<div class="col">
				<s:bind path="userName">
					<div class="input-group">
						<sf:input path="userName" type="text"
							placeholder="User Name" class="form-control" />
					</div>
				</s:bind>
			</div>
			<div class="col">
				<s:bind path="jobName">
					<div class="input-group">
						<sf:input path="jobName" type="text" placeholder="Email Id"
							class="form-control" />
					</div>
				</s:bind>
			</div>
			
			<div class="col">
				<s:bind path="fileName">
					<div class="input-group">
						<sf:input path="fileName" type="text" placeholder="File Name"
							class="form-control" />
					</div>
				</s:bind>
			</div>
			<%-- <div class="col">
				<s:bind path="city">
					<div class="input-group">
						<sf:input path="City" type="text" placeholder="city"
							class="form-control" />
					</div>
				</s:bind>
			</div> --%>
			<div class="col">
				<div class="input-group">

					<input type="submit" name="operation" class="btn btn-md btn-info"
						value="Search">&nbsp;&nbsp; <input type="submit" name="operation"
						class="btn btn-md btn-info" value="Reset">
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
	
	<table 
		class="table table-striped table-bordered table-sm" 
		width="99%">
		<thead>
			<tr>
				<th class="th-sm">Job Name</th>
				<th class="th-sm">Company Name</th>
				<th class="th-sm">Name</th>
				<th class="th-sm">Apply Date</th>
				<th class="th-sm">Resume</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="ap" varStatus="rp">
			<tr>
				
				<td><c:out value="${ap.jobName}"/></td>
				<td><c:out value="${ap.companyName}"/></td>
				<td><c:out value="${ap.userName}"/></td>
				<td><c:out value="${ap.apDate}"/></td>
				<td><a target="blank" href="/JobPortal/ctl/apply/getFile/<c:out value="${ap.id}"/>">${ap.fileName}</a></td>
			</tr>
	</c:forEach>

		</tbody>
	</table>
	<div style="margin: 10px" class="row row-cols-4">
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation" <c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
								class="btn btn-primary" value="Previous"
								>
			</div>
		</div>
		<div class="col">
			<div class="input-group">
				<input type="submit" name="operation" <c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
								class="btn btn-primary" value="Next">
			</div>
		</div>
	</div>
</sf:form>