package in.co.job.portal.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.job.portal.dao.ApplyJobDAOInt;
import in.co.job.portal.dto.ApplyJobDTO;
import in.co.job.portal.exception.DuplicateRecordException;





@Service
public class ApplyJobServiceImpl implements ApplyJobServiceInt {

	private static Logger log=Logger.getLogger(ApplyJobServiceImpl.class.getName());
	
	@Autowired
	private ApplyJobDAOInt dao;
	
	
	
	@Override
	@Transactional
	public long add(ApplyJobDTO dto) throws DuplicateRecordException {
		log.info("ApplyJobServiceImpl Add method start");
		
		long pk=dao.add(dto);
		log.info("ApplyJobServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ApplyJobDTO dto) {
		log.info("ApplyJobServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ApplyJobServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public ApplyJobDTO findBypk(long pk) {
		log.info("ApplyJobServiceImpl findBypk method start");
		ApplyJobDTO dto=dao.findBypk(pk);
		log.info("ApplyJobServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ApplyJobDTO findByName(String name) {
		log.info("ApplyJobServiceImpl findByApplyJobName method start");
		ApplyJobDTO dto=dao.findByName(name);
		log.info("ApplyJobServiceImpl findByApplyJobName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ApplyJobDTO dto) throws DuplicateRecordException {
		log.info("ApplyJobServiceImpl update method start");
		dao.update(dto);
		log.info("ApplyJobServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ApplyJobDTO> list() {
		log.info("ApplyJobServiceImpl list method start");
		List<ApplyJobDTO> list=dao.list();
		log.info("ApplyJobServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplyJobDTO> list(int pageNo, int pageSize) {
		log.info("ApplyJobServiceImpl list method start");
		List<ApplyJobDTO> list=dao.list(pageNo, pageSize);
		log.info("ApplyJobServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplyJobDTO> search(ApplyJobDTO dto) {
		log.info("ApplyJobServiceImpl search method start");
		List<ApplyJobDTO> list=dao.search(dto);
		log.info("ApplyJobServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplyJobDTO> search(ApplyJobDTO dto, int pageNo, int pageSize) {
		log.info("ApplyJobServiceImpl search method start");
		List<ApplyJobDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("ApplyJobServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public Blob getFileById(long id) throws SerialException, SQLException {
		return dao.getFileById(id);
	}

	
	
	
	
	
}
