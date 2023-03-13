package in.co.job.portal.form;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import in.co.job.portal.dto.ApplicationDTO;
import in.co.job.portal.dto.BaseDTO;
import in.co.job.portal.dto.EducationDTO;
import in.co.job.portal.dto.ExprienceDTO;
import in.co.job.portal.util.DataUtility;

public class ApplicationForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Email is required")
	private String email;

	@NotEmpty(message = "Mobile No is required")
	private String mobile;
	
	@NotEmpty(message = "Skill is required")
	private String skill;

	@NotEmpty(message = "Hobbies is required")
	private String hobbies;

	@NotEmpty(message = "Personal Detail is required")
	private String pDetail;

	@NotEmpty(message = "Declaration is required")
	private String declaration;

	@NotEmpty(message = "Date is required")
	private String date;

	@NotEmpty(message = "Place is required")
	private String place;

	@NotEmpty(message = "Objective is required")
	private String objective;

	@NotEmpty(message = "Past Experience is required")
	private String pastExprience;
	
	private List<EducationDTO> eduForm;
	
	private List<ExprienceDTO> expForm;
	
	
	
	

	public List<ExprienceDTO> getExpForm() {
		return expForm;
	}

	public void setExpForm(List<ExprienceDTO> expForm) {
		this.expForm = expForm;
	}

	public List<EducationDTO> getEduForm() {
		return eduForm;
	}

	public void setEduForm(List<EducationDTO> eduForm) {
		this.eduForm = eduForm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getpDetail() {
		return pDetail;
	}

	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getPastExprience() {
		return pastExprience;
	}

	public void setPastExprience(String pastExprience) {
		this.pastExprience = pastExprience;
	}

	@Override
	public BaseDTO getDto() {
		ApplicationDTO dto = new ApplicationDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setEmail(email);
		dto.setMobile(mobile);
		dto.setSkill(skill);
		dto.setHobbies(hobbies);
		dto.setpDetail(pDetail);
		dto.setDeclaration(declaration);
		dto.setDate(DataUtility.getDate(date));
		dto.setPlace(place);
		dto.setObjective(objective);
		dto.setPastExprience(pastExprience);
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);
		dto.setCreatedDatetime(createdDateTime);
		dto.setModifiedDatetime(modifiedDateTime);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		ApplicationDTO dto = (ApplicationDTO) bDto;
		id=dto.getId();
		name = dto.getName();
		email = dto.getEmail();
		mobile = dto.getMobile();
		skill = dto.getEmail();
		hobbies = dto.getHobbies();
		pDetail = dto.getpDetail();
		declaration = dto.getDeclaration();
		date = DataUtility.getDateString(dto.getDate());
		place = dto.getPlace();
		objective = dto.getObjective();
		pastExprience = dto.getPastExprience();
		createdBy = dto.getCreatedBy();
		modifiedBy = dto.getModifiedBy();
		createdDateTime = dto.getCreatedDatetime();
		modifiedDateTime = dto.getModifiedDatetime();

	}

}
