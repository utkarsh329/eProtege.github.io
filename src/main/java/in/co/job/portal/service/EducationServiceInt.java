package in.co.job.portal.service;

import java.util.List;


import in.co.job.portal.dto.EducationDTO;
import in.co.job.portal.exception.DuplicateRecordException;

public interface EducationServiceInt {

	public long add(EducationDTO dto) throws DuplicateRecordException;

	public void delete(EducationDTO dto);

	public EducationDTO findBypk(long pk);

	public EducationDTO findByName(String name);

	public void update(EducationDTO dto) throws DuplicateRecordException;

	public List<EducationDTO> list();

	public List<EducationDTO> list(int pageNo, int pageSize);

	public List<EducationDTO> search(EducationDTO dto);

	public List<EducationDTO> search(EducationDTO dto, int pageNo, int pageSize);
	




}
