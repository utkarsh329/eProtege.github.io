package in.co.job.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.co.job.portal.dao.JobDAOInt;
import in.co.job.portal.dto.JobDTO;
import in.co.job.portal.exception.DuplicateRecordException;
import in.co.job.portal.util.EmailBuilder;

@Service
public class JobServiceImpl implements JobServiceInt 
{

	private static Logger log=Logger.getLogger(JobServiceImpl.class.getName());
	
	@Autowired
	private JobDAOInt dao;
	
	@Override
	@Transactional
	public long add(JobDTO dto) throws DuplicateRecordException
	{
		log.info("JobServiceImpl Add method start");
		JobDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null)
			throw new DuplicateRecordException("Job Already Exist");
		long pk=dao.add(dto);
		log.info("JobServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(JobDTO dto) {
		log.info("JobServiceImpl Delete method start");
		dao.delete(dto);
		log.info("JobServiceImpl Delete method end");
		
	}

	@Override
	@Transactional
	public JobDTO findBypk(long pk) {
		log.info("JobServiceImpl findBypk method start");
		JobDTO dto=dao.findBypk(pk);
		log.info("JobServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public JobDTO findByName(String name) {
		log.info("JobServiceImpl findByJobName method start");
		JobDTO dto=dao.findByName(name);
		log.info("JobServiceImpl findByJobName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(JobDTO dto) throws DuplicateRecordException {
		log.info("JobServiceImpl update method start");
		JobDTO existDTO=dao.findByName(dto.getName());
		if(existDTO !=null && dto.getId()!=existDTO.getId())
			throw new DuplicateRecordException("Name Already Exist");
		dao.update(dto);
		log.info("JobServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<JobDTO> list()
	{
		log.info("JobServiceImpl list method start");
		List<JobDTO> list=dao.list();
		log.info("JobServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<JobDTO> list(int pageNo, int pageSize) 
	{
		log.info("JobServiceImpl list method start");
		List<JobDTO> list=dao.list(pageNo, pageSize);
		log.info("JobServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<JobDTO> search(JobDTO dto) 
	{
		log.info("JobServiceImpl search method start");
		List<JobDTO> list=dao.search(dto);
		log.info("JobServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<JobDTO> search(JobDTO dto, int pageNo, int pageSize) 
	{
		log.info("JobServiceImpl search method start");
		List<JobDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("JobServiceImpl search method end");
		return list;
	}
}
