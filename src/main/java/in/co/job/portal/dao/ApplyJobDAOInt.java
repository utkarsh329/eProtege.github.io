package in.co.job.portal.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import in.co.job.portal.dto.ApplyJobDTO;



public interface ApplyJobDAOInt {

	public long add(ApplyJobDTO dto);
	
	public void delete(ApplyJobDTO dto);
	
	public ApplyJobDTO findBypk(long pk);
	
	public ApplyJobDTO findByName(String name);
	
	public void update(ApplyJobDTO dto);
	
	public List<ApplyJobDTO> list();
	
	public List<ApplyJobDTO>list(int pageNo,int pageSize);
	
	public List<ApplyJobDTO> search(ApplyJobDTO dto);
	
	public List<ApplyJobDTO> search(ApplyJobDTO dto,int pageNo,int pageSize);
	
	public Blob getFileById(long id) throws SerialException, SQLException;
	
}
