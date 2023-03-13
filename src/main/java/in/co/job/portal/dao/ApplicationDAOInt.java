package in.co.job.portal.dao;

import java.util.List;

import in.co.job.portal.dto.ApplicationDTO;


public interface ApplicationDAOInt {

	public long add(ApplicationDTO dto);
	
	public void delete(ApplicationDTO dto);
	
	public ApplicationDTO findBypk(long pk);
	
	public ApplicationDTO findByUserId(long pk);
	
	public ApplicationDTO findByName(String name);
	
	public void update(ApplicationDTO dto);
	
	public List<ApplicationDTO> list();
	
	public List<ApplicationDTO>list(int pageNo,int pageSize);
	
	public List<ApplicationDTO> search(ApplicationDTO dto);
	
	public List<ApplicationDTO> search(ApplicationDTO dto,int pageNo,int pageSize);
	
}
