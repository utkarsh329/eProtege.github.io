package in.co.job.portal.service;

import java.util.List;


import in.co.job.portal.dto.ExprienceDTO;
import in.co.job.portal.exception.DuplicateRecordException;

public interface ExprienceServiceInt {

	public long add(ExprienceDTO dto) throws DuplicateRecordException;

	public void delete(ExprienceDTO dto);

	public ExprienceDTO findBypk(long pk);

	public ExprienceDTO findByName(String name);

	public void update(ExprienceDTO dto) throws DuplicateRecordException;

	public List<ExprienceDTO> list();

	public List<ExprienceDTO> list(int pageNo, int pageSize);

	public List<ExprienceDTO> search(ExprienceDTO dto);

	public List<ExprienceDTO> search(ExprienceDTO dto, int pageNo, int pageSize);
	




}
