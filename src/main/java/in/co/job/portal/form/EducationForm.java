package in.co.job.portal.form;

import javax.validation.constraints.NotEmpty;

import in.co.job.portal.dto.BaseDTO;
import in.co.job.portal.dto.EducationDTO;

public class EducationForm extends BaseForm {

	private String courseName;
	private String insName;
	private String percentage;
	
	public EducationForm()
	{
	}
	
	public EducationForm(String courseName, String insName, String percentage) {
		this.courseName = courseName;
		this.insName = insName;
		this.percentage = percentage;
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
	public BaseDTO getDto() {
		EducationDTO dto=new EducationDTO();
		dto.setCourseName(courseName);
		dto.setInsName(insName);
		dto.setPercentage(percentage);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		EducationDTO dto=(EducationDTO)bDto;
		courseName=dto.getCourseName();
		insName=dto.getInsName();
		percentage=dto.getPercentage();
	}

}
