package in.co.job.portal.dao;

import java.util.List;

import in.co.job.portal.dto.EducationDTO;


public interface EducationDAOInt {

	public long add(EducationDTO dto);
	
	public void delete(EducationDTO dto);
	
	public EducationDTO findBypk(long pk);
	
	public EducationDTO findByName(String name);
	
	public void update(EducationDTO dto);
	
	public List<EducationDTO> list();
	
	public List<EducationDTO>list(int pageNo,int pageSize);
	
	public List<EducationDTO> search(EducationDTO dto);
	
	public List<EducationDTO> search(EducationDTO dto,int pageNo,int pageSize);
	
}
