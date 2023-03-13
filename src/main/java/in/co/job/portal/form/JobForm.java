package in.co.job.portal.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import in.co.job.portal.dto.BaseDTO;
import in.co.job.portal.dto.JobDTO;
import in.co.job.portal.util.DataUtility;

public class JobForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "Company Name is required")
	private String companyName;
	@NotEmpty(message = "Language is required")
	private String language;
	@NotEmpty(message = "Description is required")
	private String description;
	@NotEmpty(message = "Post Date is required")
	private String postDate;
	@NotEmpty(message = "Address is required")
	private String address;
	@NotEmpty(message = "Department is required")
	private String department;
	@NotEmpty(message = "Skills is required")
	private String skills;
	@NotEmpty(message = "JobType is required")
	private String jobType;
	@NotEmpty(message = "Role is required")
	private String role;
	@NotEmpty(message = "Salary is required")
	private String salary;
	@NotEmpty(message = "Required Qualification is required")
	private String reqQualifiation;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getReqQualifiation() {
		return reqQualifiation;
	}

	public void setReqQualifiation(String reqQualifiation) {
		this.reqQualifiation = reqQualifiation;
	}

	@Override
	public BaseDTO getDto() {
		JobDTO dto = new JobDTO();
		dto.setName(name);
		dto.setCompanyName(companyName);
		dto.setLanguage(language);
		dto.setDescription(description);
		dto.setPostDate(DataUtility.getDate(postDate));
		dto.setAddress(address);
		dto.setDepartment(department);
		dto.setSkills(skills);
		dto.setJobType(jobType);
		dto.setRole(role);
		dto.setSalary(salary);
		dto.setReqQualifiation(reqQualifiation);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		JobDTO dto = (JobDTO) bDto;
		name=dto.getName();
		companyName=dto.getCompanyName();
		language=dto.getLanguage();
		department=dto.getDepartment();
		description=dto.getDescription();
		postDate=DataUtility.getDateString(dto.getPostDate());
		address=dto.getAddress();
		skills=dto.getSkills();
		jobType=dto.getJobType();
		role=dto.getRole();
		salary=dto.getSalary();
		reqQualifiation=dto.getReqQualifiation();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
	}

}
