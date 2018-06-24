package _01_register.repository.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import _01_register.model.MemberBean;
import _01_register.repository.MemberDao;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	SessionFactory factory;
	

	public MemberDaoImpl() {
		super();
	}
	@Override
	public boolean checkAccount(String userAccount) {
		boolean exist = false;
		MemberBean mb = null;
		String hql = "FROM MemberBean m WHERE m.userAccount = :userAccount ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query = query.setParameter("userAccount", userAccount);
		try {
			mb = (MemberBean) query.getSingleResult();
			exist = true;
		} catch(NoResultException ex) {
			exist = false;
		}
		return exist;
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
	
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
		// 否則傳回 null。
		@Override
		public MemberBean checkACPassword(String userPassword) {
			MemberBean mb = null;
			String hql = "FROM MemberBean m WHERE m.userPassword = :pswd";
			Session session = getSession();
			try {
				mb = (MemberBean) session.createQuery(hql)
				             .setParameter("pswd", userPassword)
				             .getSingleResult();
			} catch(NoResultException ex) {
				mb = null;
			}
			return mb;
		}

	private Session getSession() {
        return factory.getCurrentSession();
	}
	

}
