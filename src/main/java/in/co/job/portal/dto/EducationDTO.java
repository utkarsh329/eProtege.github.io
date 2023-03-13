package in.co.job.portal.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EDUCATION")
public class EducationDTO extends BaseDTO 
{

	@Column(name="APPLICATION_ID")
	private long applicationId;
	@Column(name="COURSE_NAME",length = 225)
	private String courseName;
	@Column(name="INS_NAME",length = 225)
	private String insName;
	@Column(name="PERCENTAGE",length = 225)
	private String percentage;
	
	
	public EducationDTO() 
	{
	}
	
	
	
	public EducationDTO(String courseName, String insName, String percentage) {
		this.courseName = courseName;
		this.insName = insName;
		this.percentage = percentage;
	}



	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
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
