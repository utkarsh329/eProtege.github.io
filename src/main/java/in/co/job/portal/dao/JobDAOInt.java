package in.co.job.portal.dao;

import java.util.List;

import in.co.job.portal.dto.JobDTO;



public interface JobDAOInt {

	public long add(JobDTO dto);
	
	public void delete(JobDTO dto);
	
	public JobDTO findBypk(long pk);
	
	public JobDTO findByName(String name);
	
	public void update(JobDTO dto);
	
	public List<JobDTO> list();
	
	public List<JobDTO>list(int pageNo,int pageSize);
	
	public List<JobDTO> search(JobDTO dto);
	
	public List<JobDTO> search(JobDTO dto,int pageNo,int pageSize);
	
}
