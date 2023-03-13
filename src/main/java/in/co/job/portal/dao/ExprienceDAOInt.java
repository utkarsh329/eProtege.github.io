package in.co.job.portal.dao;

import java.util.List;

import in.co.job.portal.dto.ExprienceDTO;


public interface ExprienceDAOInt {

	public long add(ExprienceDTO dto);
	
	public void delete(ExprienceDTO dto);
	
	public ExprienceDTO findBypk(long pk);
	
	public ExprienceDTO findByName(String name);
	
	public void update(ExprienceDTO dto);
	
	public List<ExprienceDTO> list();
	
	public List<ExprienceDTO>list(int pageNo,int pageSize);
	
	public List<ExprienceDTO> search(ExprienceDTO dto);
	
	public List<ExprienceDTO> search(ExprienceDTO dto,int pageNo,int pageSize);
	
}
