package in.co.job.portal.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EXPRIENCE")
public class ExprienceDTO extends BaseDTO {

	@Column(name = "APPLICATION_ID")
	private long applicationId;
	@Column(name = "COMPANY_NAME", length = 225)
	private String companyName;
	@Column(name = "DURATION", length = 225)
	private String duration;
	@Column(name = "TECHNOLOGY", length = 225)
	private String technology;
	
	public ExprienceDTO() {
	}
	
	

	public ExprienceDTO(String companyName, String duration, String technology) {
		this.companyName = companyName;
		this.duration = duration;
		this.technology = technology;
	}



	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
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
