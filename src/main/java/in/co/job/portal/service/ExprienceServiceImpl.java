package in.co.job.portal.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.co.job.portal.dao.ExprienceDAOInt;
import in.co.job.portal.dto.ExprienceDTO;
import in.co.job.portal.exception.DuplicateRecordException;


@Service
public class ExprienceServiceImpl implements ExprienceServiceInt {

	private static Logger log=Logger.getLogger(ExprienceServiceImpl.class.getName());
	
	@Autowired
	private ExprienceDAOInt dao;
	
	
	
	@Override
	@Transactional
	public long add(ExprienceDTO dto) throws DuplicateRecordException {
		log.info("ExprienceServiceImpl Add method start");
		
		long pk=dao.add(dto);
		log.info("ExprienceServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ExprienceDTO dto) {
		log.info("ExprienceServiceImpl Delete method start");
		dao.delete(dto);
		log.info("ExprienceServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public ExprienceDTO findBypk(long pk) {
		log.info("ExprienceServiceImpl findBypk method start");
		ExprienceDTO dto=dao.findBypk(pk);
		log.info("ExprienceServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public ExprienceDTO findByName(String name) {
		log.info("ExprienceServiceImpl findByExprienceName method start");
		ExprienceDTO dto=dao.findByName(name);
		log.info("ExprienceServiceImpl findByExprienceName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(ExprienceDTO dto) throws DuplicateRecordException {
		log.info("ExprienceServiceImpl update method start");
		dao.update(dto);
		log.info("ExprienceServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<ExprienceDTO> list() {
		log.info("ExprienceServiceImpl list method start");
		List<ExprienceDTO> list=dao.list();
		log.info("ExprienceServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ExprienceDTO> list(int pageNo, int pageSize) {
		log.info("ExprienceServiceImpl list method start");
		List<ExprienceDTO> list=dao.list(pageNo, pageSize);
		log.info("ExprienceServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<ExprienceDTO> search(ExprienceDTO dto) {
		log.info("ExprienceServiceImpl search method start");
		List<ExprienceDTO> list=dao.search(dto);
		log.info("ExprienceServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<ExprienceDTO> search(ExprienceDTO dto, int pageNo, int pageSize) {
		log.info("ExprienceServiceImpl search method start");
		List<ExprienceDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("ExprienceServiceImpl search method end");
		return list;
	}

	
	
}
