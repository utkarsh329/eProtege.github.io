<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<nav aria-label="breadcrumb">
	<ol class="breadcrumb primary-color">
		<li class="breadcrumb-item"><a class="white-text" href="#">Home</a></li>
		<li class="breadcrumb-item active">Application</li>
	</ol>
</nav>

<div col-md-5img-thumbnail">
	<div id="feedback">
		<div class="container">
			<div class="col-md-8">
				<div class="form-area">
					<sf:form method="post"
						action="${pageContext.request.contextPath}/ctl/apwresume"
						modelAttribute="form">
						<br style="clear: both">
						<h3 style="margin-bottom: 15px; text-align: left:;">Application</h3>
						<b><%@ include file="businessMessage.jsp"%></b>
						<sf:hidden path="id" />
						<div class="form-group">
							<s:bind path="name">
								<label>Name :</label>
								<sf:input path="${status.expression}" placeholder="Enter Name"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<div class="form-group">
							<s:bind path="email">
								<label>Email:</label>
								<sf:input path="${status.expression}"
									placeholder="Enter Email Id" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<div class="form-group">
							<s:bind path="mobile">
								<label>Mobile No:</label>
								<sf:input path="${status.expression}"
									placeholder="Enter Mobile No" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>


						<div class="form-group">
							<s:bind path="objective">
								<label>Objective:</label>
								<sf:textarea rows="4" cols="4" path="${status.expression}"
									placeholder="Enter Objective" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<h5>Education Detail</h5>
						<hr>
						<sf:hidden path="eduForm"/>
			 			<c:forEach items="${form.eduForm}" varStatus="vs">
						<div class="form-row">
							<div class="form-group col-md-4">
								<s:bind path="eduForm[${vs.index}].courseName">
									<label>Course Name :</label>
									<sf:input path="${status.expression}" placeholder="Enter Course Name"
										class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
							<div class="form-group col-md-4">
								<s:bind path="eduForm[${vs.index}].insName">
									<label>Institute Name :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Institute Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>

							<div class="form-group col-md-4">
								<s:bind path="eduForm[${vs.index}].percentage">
									<label>Percentage :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Percentage Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
						</div>
						</c:forEach>
						
				<hr>
						<div class="form-group">
							<s:bind path="skill">
								<label>Skills:</label>
								<sf:input path="${status.expression}" placeholder="Enter Skills"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<div class="form-group">
							<s:bind path="pastExprience">
								<label>Past Experience:</label>
								<sf:input path="${status.expression}"
									placeholder="Enter Past Experience" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<div class="form-group">
							<s:bind path="pDetail">
								<label>Personal Detail:</label>
								<sf:textarea rows="4" cols="4" path="${status.expression}"
									placeholder="Enter Personal Detail" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>
						<h5>Experience Detail</h5>
						<hr>
						<sf:hidden path="expForm"/>
			 			<c:forEach items="${form.expForm}" varStatus="vs">
						<div class="form-row">
							<div class="form-group col-md-4">
								<s:bind path="expForm[${vs.index}].companyName">
									<label>Company Name :</label>
									<sf:input path="${status.expression}" placeholder="Enter Company Name"
										class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
							<div class="form-group col-md-4">
								<s:bind path="expForm[${vs.index}].duration">
									<label>Duration :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Duration" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>

							<div class="form-group col-md-4">
								<s:bind path="expForm[${vs.index}].technology">
									<label>Technology :</label>
									<sf:input path="${status.expression}"
										placeholder="Enter Technology Name" class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
						</div>
						</c:forEach>
						<hr>
						<div class="form-group">
							<s:bind path="hobbies">
								<label>Hobbies:</label>
								<sf:input path="${status.expression}"
									placeholder="Enter Hobbies" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>




						<div class="form-group">
							<s:bind path="declaration">
								<label>Declaration:</label>
								<sf:textarea rows="4" cols="4" path="${status.expression}"
									placeholder="Enter Declaration" class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>

						<div class="form-group">
							<s:bind path="date">
								<label>Date:</label>
								<sf:input path="${status.expression}" placeholder="Enter Date"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>

						<div class="form-group">
							<s:bind path="place">
								<label>Place:</label>
								<sf:input path="${status.expression}" placeholder="Enter Place"
									class="form-control" />
								<font color="red" style="font-size: 13px"><sf:errors
										path="${status.expression}" /></font>
							</s:bind>
						</div>

						<input type="submit" name="operation"
							class="btn btn-primary pull-right" value="Save">or<input
							type="submit" name="operation" class="btn btn-primary pull-right"
							value="Reset">
					</sf:form>
				</div>
			</div>
		</div>
	</div>
	<!--feedback-->
	<br>