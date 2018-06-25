package _01_register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import _01_register.model.MemberBean;
import _01_register.repository.MemberDao;
import _01_register.service.MemberService;



@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao  dao;
	
	public MemberServiceImpl() {
	}

	@Override
	public boolean userLogin(String userAccount, String password) {
		return false;
	}

	@Override
	public boolean checkAccount(String userAccount) {

		return dao.checkAccount(userAccount);
	}

	@Override
	public int updateMemberDate(MemberBean mb) {

		return dao.updateMemberDate(mb);
	}

	@Override
	public int insertMemberDate(MemberBean mb) {

		return dao.insertMemberDate(mb);
	}

	@Override
	public byte[] getPortrait(String userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberBean getUserDateNoPortrait(String userAccount) {
		
		return dao.getUserDateNoPortrait(userAccount);
	}

	@Override
	public MemberBean checkACPassword(String userAccount, String userPassword) {
		
		return dao.checkACPassword(userAccount ,userPassword);
	}
	
	

}
