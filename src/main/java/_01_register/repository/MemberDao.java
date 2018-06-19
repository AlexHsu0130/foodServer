package _01_register.repository;

import _01_register.model.MemberBean;

public interface MemberDao {
	
	boolean userLogin(String userAccount, String password);
	
	boolean checkAccount(String userAccount);
	
	int updateMemberDate(MemberBean mb);
	
	int insertMemberDate(MemberBean mb);
	
	byte[] getPortrait(String userAccount);	
	
	MemberBean getUserDateNoPortrait(String userAccount);
}