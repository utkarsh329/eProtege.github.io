package in.co.job.portal.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "APPLY_JOB")
public class ApplyJobDTO extends BaseDTO {

	@Column(name = "JOB_ID")
	private long jobId;
	@Column(name = "JOB_NAME", length = 225)
	private String jobName;
	@Column(name = "COMPANY_NAME", length = 225)
	private String companyName;
	@Column(name = "USER_ID")
	private long userId;
	@Column(name = "USER_NAME", length = 225)
	private String userName;
	@Column(name = "RECRUITER_ID")
	private long recruiterId;
	@Column(name = "RECRUITER_NAME", length = 225)
	private String recruiterName;
	@Column(name = "AP_DATE")
	@Temporal(TemporalType.DATE)
	private Date apDate;
	
	@Lob
	@Column(name = "RESUME_FILE", columnDefinition = "LONGBLOB")
	private byte[] resumeFile; 
	
	@Column(name = "FILE_NAME", length = 225)
	private String fileName;

	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getResumeFile() {
		return resumeFile;
	}

	public void setResumeFile(byte[] resumeFile) {
		this.resumeFile = resumeFile;
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
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
