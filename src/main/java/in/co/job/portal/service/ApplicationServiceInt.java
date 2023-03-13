package in.co.job.portal.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.job.portal.dto.ApplicationDTO;
import in.co.job.portal.exception.DuplicateRecordException;

public interface ApplicationServiceInt {

	public long add(ApplicationDTO dto) throws DuplicateRecordException;

	public void delete(ApplicationDTO dto);

	public ApplicationDTO findBypk(long pk);

	public ApplicationDTO findByName(String name);
	
	public ApplicationDTO findByUserId(long id);

	public void update(ApplicationDTO dto) throws DuplicateRecordException;

	public List<ApplicationDTO> list();

	public List<ApplicationDTO> list(int pageNo, int pageSize);

	public List<ApplicationDTO> search(ApplicationDTO dto);

	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize);
	




}
