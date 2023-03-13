package in.co.job.portal.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.job.portal.dto.EducationDTO;


@Repository
public class EducationDAOImpl implements EducationDAOInt {

	private static Logger log = Logger.getLogger(EducationDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(EducationDTO dto) {
		log.info("EducationDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("EducationDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(EducationDTO dto) {
		log.info("EducationDAOImpl Delete method Start");
		Session session = entityManager.unwrap(Session.class);
		session.delete(dto);
		log.info("EducationDAOImpl Delete method End");

	}

	@Override
	public EducationDTO findBypk(long pk) {
		log.info("EducationDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		EducationDTO dto = (EducationDTO) session.get(EducationDTO.class, pk);
		log.info("EducationDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public EducationDTO findByName(String name) {
		log.info("EducationDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(EducationDTO.class);
		criteria.add(Restrictions.eq("name", name));
		EducationDTO dto = (EducationDTO) criteria.uniqueResult();
		log.info("EducationDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(EducationDTO entity) {
		log.info("EducationDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(entity);
		log.info("EducationDAOImpl update method End");
	}

	@Override
	public List<EducationDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<EducationDTO> list(int pageNo, int pageSize) {
		log.info("EducationDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<EducationDTO> query = session.createQuery("from EducationDTO", EducationDTO.class);
		List<EducationDTO> list = query.getResultList();
		log.info("EducationDAOImpl List method End");
		return list;
	}

	@Override
	public List<EducationDTO> search(EducationDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<EducationDTO> search(EducationDTO dto, int pageNo, int pageSize) {
		log.info("EducationDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql=new StringBuffer("from EducationDTO as e where 1=1 ");
		if(dto !=null) {
			if(dto.getId()>0) {
				hql.append("and e.id = "+dto.getId());
			}
			if(dto.getApplicationId()>0) {
				hql.append("and e.applicationId = "+dto.getApplicationId());
			}
		}
		System.out.println(hql.toString());
		Query<EducationDTO> query = session.createQuery(hql.toString(), EducationDTO.class);
	
		List<EducationDTO> list = query.getResultList();
		log.info("EducationDAOImpl Search method End");
		return list;
	}
	
	

}
