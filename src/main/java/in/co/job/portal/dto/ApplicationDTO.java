package in.co.job.portal.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="APPLICATION")
public class ApplicationDTO extends BaseDTO 
{
	@Column(name="NAME",length = 225)
	private String name;
	
	@Column(name="EMAIL",length = 225)
	private String email;
	
	@Column(name="MOBILE",length = 225)
	private String mobile;
	
	@Column(name="SKILLS",length = 225)
	private String skill;
	
	@Column(name="HOBBIES",length = 225)
	private String hobbies;
	
	@Column(name="P_DETAIL",length = 225)
	private String pDetail;
	
	@Column(name="DECLARATION",length = 225)
	private String declaration;
	
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="PLACE",length = 225)
	private String place;
	
	@Column(name="OBJECTIVE",length = 225)
	private String objective;
	
	@Column(name="PAST_EXPRIENCE",length = 225)
	private String pastExprience;

	@Column(name="USER_ID")
	private long userId;
		
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
