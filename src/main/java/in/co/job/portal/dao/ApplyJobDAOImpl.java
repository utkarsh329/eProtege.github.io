package in.co.job.portal.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.job.portal.dto.ApplyJobDTO;
import in.co.job.portal.dto.UserDTO;


@Repository
public class ApplyJobDAOImpl implements ApplyJobDAOInt 
{

	private static Logger log = Logger.getLogger(ApplyJobDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(ApplyJobDTO dto) {
		log.info("ApplyJobDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("ApplyJobDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(ApplyJobDTO dto) {
		log.info("ApplyJobDAOImpl Delete method Start");
		Session session = entityManager.unwrap(Session.class);
		session.delete(dto);
		log.info("ApplyJobDAOImpl Delete method End");

	}

	@Override
	public ApplyJobDTO findBypk(long pk) {
		log.info("ApplyJobDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ApplyJobDTO dto = (ApplyJobDTO) session.get(ApplyJobDTO.class, pk);
		log.info("ApplyJobDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ApplyJobDTO findByName(String name) {
		log.info("ApplyJobDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ApplyJobDTO.class);
		criteria.add(Restrictions.eq("name", name));
		ApplyJobDTO dto = (ApplyJobDTO) criteria.uniqueResult();
		log.info("ApplyJobDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(ApplyJobDTO entity) {
		log.info("ApplyJobDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		//session.saveOrUpdate(entity);
		session.merge(entity);
		//session.update(entity);
		log.info("ApplyJobDAOImpl update method End");
	}

	@Override
	public List<ApplyJobDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<ApplyJobDTO> list(int pageNo, int pageSize) {
		log.info("ApplyJobDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ApplyJobDTO> query = session.createQuery("from ApplyJobDTO", ApplyJobDTO.class);
		List<ApplyJobDTO> list = query.getResultList();
		log.info("ApplyJobDAOImpl List method End");
		return list;
	}

	@Override
	public List<ApplyJobDTO> search(ApplyJobDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<ApplyJobDTO> search(ApplyJobDTO dto, int pageNo, int pageSize) {
		log.info("ApplyJobDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		
		StringBuffer hql=new StringBuffer("from ApplyJobDTO as a where 1=1 ");
		if(dto !=null) {
			if(dto.getId()>0) {
				hql.append("and a.id = "+dto.getId());
			}
			if(dto.getRecruiterId()>0) {
				hql.append("and a.recruiterId = "+dto.getRecruiterId());
			}
			if(dto.getUserId()>0) {
				hql.append("and a.userId = "+dto.getUserId());
			}
			if(dto.getUserName()!=null && dto.getUserName().length()>0) {
				hql.append("and a.userName like '%"+dto.getUserName()+"%'");
			}
			if(dto.getFileName()!=null && dto.getFileName().length()>0) {
				hql.append("and a.fileName like '%"+dto.getFileName()+"%'");
			}
		}
		Query<ApplyJobDTO> query = session.createQuery(hql.toString(),ApplyJobDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<ApplyJobDTO> list = query.getResultList();
		log.info("ApplyJobDAOImpl Search method End");
		return list;
	}
	
	@Override
	public Blob getFileById(long id) throws SerialException, SQLException {
		Session session = entityManager.unwrap(Session.class);
		ApplyJobDTO user = (ApplyJobDTO) session.get(ApplyJobDTO.class, id);
        byte[] blob = user.getResumeFile();
        Blob bBlob= new SerialBlob(blob);
		return bBlob;
	}

}
