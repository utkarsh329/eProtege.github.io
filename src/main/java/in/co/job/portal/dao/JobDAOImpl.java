package in.co.job.portal.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.co.job.portal.dto.JobDTO;

@Repository
public class JobDAOImpl implements JobDAOInt {

	private static Logger log = Logger.getLogger(JobDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;
	

	@Override
	public long add(JobDTO dto) {
		log.info("JobDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("JobDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(JobDTO dto) 
	{
		log.info("JobDAOImpl Delete method Start");
		//System.out.println(dto.toString());
		Session session = entityManager.unwrap(Session.class);
		//ht.delete(dto);
		       session.delete(dto);
		log.info("JobDAOImpl Delete method End");

	}

	@Override
	public JobDTO findBypk(long pk) {
		log.info("JobDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		JobDTO dto = (JobDTO) session.get(JobDTO.class, pk);
		log.info("JobDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public JobDTO findByName(String name) {
		log.info("JobDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(JobDTO.class);
		criteria.add(Restrictions.eq("name", name));
		JobDTO dto = (JobDTO) criteria.uniqueResult();
		log.info("JobDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(JobDTO entity) {
		log.info("JobDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(entity);
		log.info("JobDAOImpl update method End");
	}

	@Override
	public List<JobDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<JobDTO> list(int pageNo, int pageSize) {
		log.info("JobDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<JobDTO> query = session.createQuery("from JobDTO", JobDTO.class);
		List<JobDTO> list = query.getResultList();
		log.info("JobDAOImpl List method End");
		return list;
	}

	@Override
	public List<JobDTO> search(JobDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<JobDTO> search(JobDTO dto, int pageNo, int pageSize) {
		log.info("JobDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql=new StringBuffer("from JobDTO as j where 1=1 ");
		if(dto !=null) {
			if(dto.getId()>0) {
				hql.append("and j.id = "+dto.getId());
			}
			if(dto.getRecruiterId()>0) {
				hql.append("and j.recruiterId = "+dto.getRecruiterId());
			}
			if(dto.getName()!=null && dto.getName().length()>0) {
				hql.append("and j.name like '%"+dto.getName()+"%'");
			}
			if(dto.getCompanyName()!=null && dto.getCompanyName().length()>0) {
				hql.append("and j.companyName like '%"+dto.getCompanyName()+"%'");
			}
			if(dto.getLanguage()!=null && dto.getLanguage().length()>0) {
				hql.append("and j.language like '%"+dto.getLanguage()+"%'");
			}
		}
		Query<JobDTO> query = session.createQuery(hql.toString(), JobDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<JobDTO> list = query.getResultList();
		log.info("JobDAOImpl Search method End");
		return list;
	}

}
