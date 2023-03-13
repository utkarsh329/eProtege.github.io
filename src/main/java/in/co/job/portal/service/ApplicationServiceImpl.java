package in.co.job.portal.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.job.portal.dao.ApplicationDAOInt;
import in.co.job.portal.dto.ApplicationDTO;
import in.co.job.portal.exception.DuplicateRecordException;

@Service
public class ApplicationServiceImpl implements ApplicationServiceInt 
{

	private static Logger log=Logger.getLogger(ApplicationServiceImpl.class.getName());
	
	@Autowired
	private ApplicationDAOInt dao;
		
	@Override
	@Transactional
	public long add(ApplicationDTO dto) throws DuplicateRecordException {
		log.info("ApplicationServiceImpl Add method start");
		long pk=dao.add(dto);
		log.info("ApplicationServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ApplicationDTO dto) {
		log.info("ApplicationServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ApplicationServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public ApplicationDTO findBypk(long pk) {
		log.info("ApplicationServiceImpl findBypk method start");
		ApplicationDTO dto=dao.findBypk(pk);
		log.info("ApplicationServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ApplicationDTO findByName(String name) {
		log.info("ApplicationServiceImpl findByApplicationName method start");
		ApplicationDTO dto=dao.findByName(name);
		log.info("ApplicationServiceImpl findByApplicationName method end");
		return dto;
	}
	
	@Override
	@Transactional
	public ApplicationDTO findByUserId(long id) {
		log.info("ApplicationServiceImpl findByApplicationName method start");
		ApplicationDTO dto=dao.findByUserId(id);
		log.info("ApplicationServiceImpl findByApplicationName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ApplicationDTO dto) throws DuplicateRecordException {
		log.info("ApplicationServiceImpl update method start");
		dao.update(dto);
		log.info("ApplicationServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ApplicationDTO> list() {
		log.info("ApplicationServiceImpl list method start");
		List<ApplicationDTO> list=dao.list();
		log.info("ApplicationServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplicationDTO> list(int pageNo, int pageSize) {
		log.info("ApplicationServiceImpl list method start");
		List<ApplicationDTO> list=dao.list(pageNo, pageSize);
		log.info("ApplicationServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplicationDTO> search(ApplicationDTO dto) {
		log.info("ApplicationServiceImpl search method start");
		List<ApplicationDTO> list=dao.search(dto);
		log.info("ApplicationServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ApplicationDTO> search(ApplicationDTO dto, int pageNo, int pageSize) {
		log.info("ApplicationServiceImpl search method start");
		List<ApplicationDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("ApplicationServiceImpl search method end");
		return list;
	}

	
	
}
