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

import in.co.job.portal.dto.ExprienceDTO;


@Repository
public class ExprienceDAOImpl implements ExprienceDAOInt {

	private static Logger log = Logger.getLogger(ExprienceDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ExprienceDTO dto) {
		log.info("ExprienceDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ExprienceDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ExprienceDTO dto) {
		log.info("ExprienceDAOImpl Delete method Start");
		Session session = entityManager.unwrap(Session.class);
		session.delete(dto);
		log.info("ExprienceDAOImpl Delete method End");

	}

	@Override
	public ExprienceDTO findBypk(long pk) {
		log.info("ExprienceDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ExprienceDTO dto = (ExprienceDTO) session.get(ExprienceDTO.class, pk);
		log.info("ExprienceDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ExprienceDTO findByName(String name) {
		log.info("ExprienceDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ExprienceDTO.class);
		criteria.add(Restrictions.eq("name", name));
		ExprienceDTO dto = (ExprienceDTO) criteria.uniqueResult();
		log.info("ExprienceDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ExprienceDTO entity) {
		log.info("ExprienceDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(entity);
		log.info("ExprienceDAOImpl update method End");
	}

	@Override
	public List<ExprienceDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ExprienceDTO> list(int pageNo, int pageSize) {
		log.info("ExprienceDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ExprienceDTO> query = session.createQuery("from ExprienceDTO", ExprienceDTO.class);
		List<ExprienceDTO> list = query.getResultList();
		log.info("ExprienceDAOImpl List method End");
		return list;
	}

	@Override
	public List<ExprienceDTO> search(ExprienceDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ExprienceDTO> search(ExprienceDTO dto, int pageNo, int pageSize) {
		log.info("ExprienceDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql=new StringBuffer("from ExprienceDTO as e where 1=1 ");
		if(dto !=null) {
			if(dto.getId()>0) {
				hql.append("and e.id = "+dto.getId());
			}
			if(dto.getApplicationId()>0) {
				hql.append("and e.applicationId = "+dto.getApplicationId());
			}
		}
		System.out.println(hql.toString());
		Query<ExprienceDTO> query = session.createQuery(hql.toString(), ExprienceDTO.class);
		List<ExprienceDTO> list = query.getResultList();
		log.info("ExprienceDAOImpl Search method End");
		return list;
	}
	
	

}
