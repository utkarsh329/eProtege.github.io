package in.co.job.portal.service;

import java.util.List;

import in.co.job.portal.dto.JobDTO;
import in.co.job.portal.exception.DuplicateRecordException;

public interface JobServiceInt 
{
	public long add(JobDTO dto) throws DuplicateRecordException;

	public void delete(JobDTO dto);

	public JobDTO findBypk(long pk);

	public JobDTO findByName(String name);

	public void update(JobDTO dto) throws DuplicateRecordException;

	public List<JobDTO> list();

	public List<JobDTO> list(int pageNo, int pageSize);

	public List<JobDTO> search(JobDTO dto);

	public List<JobDTO> search(JobDTO dto, int pageNo, int pageSize);
}
