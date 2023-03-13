package in.co.job.portal.form;

import in.co.job.portal.dto.BaseDTO;
import in.co.job.portal.dto.ExprienceDTO;

public class ExprienceForm extends BaseForm{
	
	private String companyName;
	private String duration;
	private String technology;

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
	public BaseDTO getDto() {
		ExprienceDTO dto=new ExprienceDTO();
		dto.setCompanyName(companyName);
		dto.setDuration(duration);
		dto.setTechnology(technology);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		ExprienceDTO dto=(ExprienceDTO)bDto;
		companyName=dto.getCompanyName();
		duration=dto.getDuration();
		technology=dto.getTechnology();
	}

}
