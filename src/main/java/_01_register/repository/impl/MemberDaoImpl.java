package _01_register.repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import _01_register.model.MemberBean;
import _01_register.repository.MemberDao;
@Transactional
@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SessionFactory factory;
	

	public MemberDaoImpl() {
		super();
	}

	@Override
	public boolean userLogin(String userAccount, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateMemberDate(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.update(mb);
		n++;
		return n;
	}

	@Override
	public int insertMemberDate(MemberBean mb ) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	@Override
	public byte[] getPortrait(String userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberBean getUserDateNoPortrait(String userAccount) {
		MemberBean mb = null;
		String hql = "FROM MemberBean m WHERE m.userAccount = :userAccount ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query = query.setParameter("userAccount", userAccount);
		try {
			mb = (MemberBean) query.getSingleResult();
		
		} catch(NoResultException ex) {
			mb = null;
		}
		return mb;
	}

	@Override
	public boolean checkAccount(String userAccount) {
		// TODO Auto-generated method stub
		return false;
	}
	private Session getSession() {
        return factory.getCurrentSession();
	}

}
