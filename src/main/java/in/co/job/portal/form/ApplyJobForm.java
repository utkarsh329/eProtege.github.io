package in.co.job.portal.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import in.co.job.portal.dto.ApplyJobDTO;
import in.co.job.portal.dto.BaseDTO;
import in.co.job.portal.dto.JobDTO;

public class ApplyJobForm extends BaseForm {

	private long jobId;
	private String jobName;
	private String companyName;
	private long userId;
	private String userName;
	private long recruiterId;
	private String recruiterName;
	private Date apDate;
	
	private MultipartFile file;
	
	
	private String fileName;
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	private JobDTO jobDto;
	
	public JobDTO getJobDto() {
		return jobDto;
	}

	public void setJobDto(JobDTO jobDto) {
		this.jobDto = jobDto;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(long recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public Date getApDate() {
		return apDate;
	}

	public void setApDate(Date apDate) {
		this.apDate = apDate;
	}

	@Override
	public BaseDTO getDto() {
		ApplyJobDTO dto=new ApplyJobDTO();
		dto.setJobId(jobId);
		dto.setJobName(jobName);
		dto.setCompanyName(companyName);
		dto.setUserId(userId);
		dto.setUserName(userName);
		dto.setRecruiterId(recruiterId);
		dto.setRecruiterName(recruiterName);
		dto.setApDate(apDate);
		dto.setFileName(fileName);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		ApplyJobDTO dto=(ApplyJobDTO)bDto;
		jobId=dto.getJobId();
		jobName=dto.getJobName();
		companyName=dto.getCompanyName();
		userId=dto.getUserId();
		userName=dto.getUserName();
		recruiterId=dto.getRecruiterId();
		recruiterName=dto.getRecruiterName();
		fileName=dto.getFileName();
		apDate=dto.getApDate();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();
	}

}
