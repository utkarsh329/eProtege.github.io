package in.co.job.portal.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.job.portal.dao.EducationDAOInt;
import in.co.job.portal.dto.EducationDTO;
import in.co.job.portal.exception.DuplicateRecordException;





@Service
public class EducationServiceImpl implements EducationServiceInt {

	private static Logger log=Logger.getLogger(EducationServiceImpl.class.getName());
	
	@Autowired
	private EducationDAOInt dao;
	
	
	
	@Override
	@Transactional
	public long add(EducationDTO dto) throws DuplicateRecordException {
		log.info("EducationServiceImpl Add method start");
		
		long pk=dao.add(dto);
		log.info("EducationServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(EducationDTO dto) {
		log.info("EducationServiceImpl Delete method start");
		dao.delete(dto);
		log.info("EducationServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public EducationDTO findBypk(long pk) {
		log.info("EducationServiceImpl findBypk method start");
		EducationDTO dto=dao.findBypk(pk);
		log.info("EducationServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public EducationDTO findByName(String name) {
		log.info("EducationServiceImpl findByEducationName method start");
		EducationDTO dto=dao.findByName(name);
		log.info("EducationServiceImpl findByEducationName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(EducationDTO dto) throws DuplicateRecordException {
		log.info("EducationServiceImpl update method start");
		dao.update(dto);
		log.info("EducationServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<EducationDTO> list() {
		log.info("EducationServiceImpl list method start");
		List<EducationDTO> list=dao.list();
		log.info("EducationServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<EducationDTO> list(int pageNo, int pageSize) {
		log.info("EducationServiceImpl list method start");
		List<EducationDTO> list=dao.list(pageNo, pageSize);
		log.info("EducationServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<EducationDTO> search(EducationDTO dto) {
		log.info("EducationServiceImpl search method start");
		List<EducationDTO> list=dao.search(dto);
		log.info("EducationServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<EducationDTO> search(EducationDTO dto, int pageNo, int pageSize) {
		log.info("EducationServiceImpl search method start");
		List<EducationDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("EducationServiceImpl search method end");
		return list;
	}

	
	
}
