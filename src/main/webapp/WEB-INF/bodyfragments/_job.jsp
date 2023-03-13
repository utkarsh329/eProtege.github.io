<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb primary-color">
      <li class="breadcrumb-item"><a class="white-text" href="#">Home</a></li>
      <li class="breadcrumb-item active">Job</li>
    </ol>
  </nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-8">
    <div class="form-area">  
       <sf:form method="post" action="${pageContext.request.contextPath}/ctl/job"  modelAttribute="form">
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Job</h3>
                    <b><%@ include file="businessMessage.jsp"%></b>
                		<div class="form-row">
    							<div class="form-group col-md-6">
								<s:bind path="name">
								<label >Name :</label> 
								<sf:input path="${status.expression}" placeholder="Enter Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
        					<div class="form-group col-md-6">
								<s:bind path="companyName">
								<label >Company Name :</label> 
								<sf:input path="${status.expression}" placeholder="Enter Company Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							</div>
							<div class="form-group">
								<s:bind path="language">
								<label >Required Language :</label> 
								<sf:input path="${status.expression}" placeholder="Enter Required Language" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							<div class="form-group">
								<s:bind path="skills">
								<label >Skills:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Skills" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-row">
    							<div class="form-group col-md-6">
								<s:bind path="department">
								<label >Department:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Deparment" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
        					<div class="form-group col-md-6">
								<s:bind path="jobType">
								<label >Job Type :</label> 
								<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="Select" />
									<sf:options   items="${type}" />
								</sf:select>
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							</div>
							
							<div class="form-group">
								<s:bind path="postDate">
								<label>Post Date:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Post Date" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-group">
								<s:bind path="role">
								<label >Job Role:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Job Role" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-group">
								<s:bind path="salary">
								<label >Salary:</label> 
								<sf:input path="${status.expression}" placeholder="Enter Salary" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
							<div class="form-row">
    							<div class="form-group col-md-6">
								<s:bind path="reqQualifiation">
								<label >Required Qualification:</label> 
								<sf:textarea rows="4" cols="4" path="${status.expression}" placeholder="Enter Required Qualification" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
        					<div class="form-group col-md-6">
								<s:bind path="description">
								<label >Job Description:</label> 
								<sf:textarea rows="4" cols="4" path="${status.expression}" placeholder="Enter Job Description" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							</div>
							
							<div class="form-group">
								<s:bind path="address">
								<label >Address:</label> 
								<sf:textarea rows="4" cols="4" path="${status.expression}" placeholder="Enter Address" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
							</s:bind>
							</div>
							
        					 <input type="submit" name="operation"
								class="btn btn-primary pull-right" value="Save">or<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="Reset">
        </sf:form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

	