package in.co.job.portal.dto;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="JOB")
public class JobDTO extends BaseDTO 
{

	@Column(name="COMPANY_NAME",length = 225)
	private String companyName;
	@Column(name="NAME",length = 225)
	private String name;
	@Column(name="LANGUAGE",length = 225)
	private String language;
	@Column(name="DESCRIPTION",length = 225)
	private String description;
	@Column(name = "POST_DATE")
	@Temporal(TemporalType.DATE)
	private Date postDate;
	@Column(name="RECRUITER_ID")
	private long recruiterId;
	@Column(name="ADDRESS",length = 225)
	private String address;
	@Column(name="DEPARTMENT",length = 225)
	private String department;
	@Column(name="SKILLS",length = 225)
	private String skills;
	@Column(name="JOB_TYPE",length = 225)
	private String jobType;
	@Column(name="ROLE",length = 225)
	private String role;
	@Column(name="SALARY",length = 225)
	private String salary;
	@Column(name="REQ_QUALIFICATION",length = 225)
	private String reqQualifiation;
	
	public JobDTO() {
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
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
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

	public JobDTO(long id,String companyName, String name, String language, String description, 
			String address, String department, String skills, String jobType, String role, String salary,
			String reqQualifiation) {
		this.id=id;
		this.companyName = companyName;
		this.name = name;
		this.language = language;
		this.description = description;
		this.address = address;
		this.department = department;
		this.skills = skills;
		this.jobType = jobType;
		this.role = role;
		this.salary = salary;
		this.reqQualifiation = reqQualifiation;
	}
	

	@Override
	public String toString() {
		return "JobDTO [companyName=" + companyName + ", name=" + name + ", language=" + language + ", description="
				+ description + ", postDate=" + postDate + ", recruiterId=" + recruiterId + ", address=" + address
				+ ", department=" + department + ", skills=" + skills + ", jobType=" + jobType + ", role=" + role
				+ ", salary=" + salary + ", reqQualifiation=" + reqQualifiation + "]";
	}

	
}
