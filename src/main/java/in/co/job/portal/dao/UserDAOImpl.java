package in.co.job.portal.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.job.portal.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAOInt {

	private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(UserDTO dto) {
		log.info("UserDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("UserDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(UserDTO dto) {
		log.info("UserDAOImpl Delete method Start");
		Session session = entityManager.unwrap(Session.class);
		session.delete(dto);
		log.info("UserDAOImpl Delete method End");

	}

	@Override
	public UserDTO findBypk(long pk) {
		log.info("UserDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		UserDTO dto = (UserDTO) session.get(UserDTO.class, pk);
		log.info("UserDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public UserDTO findByLogin(String login) {
		log.info("UserDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("login", login));
		UserDTO dto = (UserDTO) criteria.uniqueResult();
		log.info("UserDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(UserDTO entity) {
		log.info("UserDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(entity);
		log.info("UserDAOImpl update method End");
	}

	@Override
	public List<UserDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<UserDTO> list(int pageNo, int pageSize) {
		log.info("UserDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session.createQuery("from UserDTO", UserDTO.class);
		List<UserDTO> list = query.getResultList();
		log.info("UserDAOImpl List method End");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		log.info("UserDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from UserDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getRoleId() > 0) {
				hql.append("and u.roleId = " + dto.getRoleId());
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				hql.append("and u.firstName like '%" + dto.getFirstName() + "%'");
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				hql.append("and u.email like '%" + dto.getEmail() + "%'");
			}
		}
		Query<UserDTO> query = session.createQuery(hql.toString(), UserDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<UserDTO> list = query.getResultList();
		log.info("UserDAOImpl Search method End");
		return list;
	}

	@Override
	public UserDTO authentication(UserDTO dto) {
		log.info("UserDAOImpl Authentication method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session.createQuery("from UserDTO where login=:login and password=:password",
				UserDTO.class);
		query.setParameter("login", dto.getLogin());
		query.setParameter("password", dto.getPassword());
		dto = null;
		try {
			dto = query.getSingleResult();
		} catch (NoResultException nre) {
		}
		log.info("UserDAOImpl Authentication method End");
		return dto;
	}

	/*
	 * @Override public UserDTO authentication(UserDTO dto) {
	 * log.info("UserDAOImpl Authentication method Start"); Session session =
	 * entityManager.unwrap(Session.class); String
	 * hql="from UserDTO as u where u.login='"+dto.getLogin()+"' and u.password='"
	 * +dto.getPassword()+"'"; System.out.println(hql); Query<UserDTO> query =
	 * session .createQuery(hql, UserDTO.class); dto = null; try { dto =
	 * query.getSingleResult(); } catch (NoResultException nre) { }
	 * log.info("UserDAOImpl Authentication method End"); return dto; }
	 */

	@Override
	public Blob getImageById(long id) throws SerialException, SQLException {

		Session session = entityManager.unwrap(Session.class);
		UserDTO user = (UserDTO) session.get(UserDTO.class, id);
		byte[] blob = user.getProfilePic();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
