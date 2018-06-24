package _01_register.service;

import _01_register.model.MemberBean;

public interface MemberService {

	boolean userLogin(String userAccount, String password);

	boolean checkAccount(String userAccount);

	int updateMemberDate(MemberBean mb);

	int insertMemberDate(MemberBean mb);

	byte[] getPortrait(String userAccount);

	MemberBean getUserDateNoPortrait(String userAccount);
	
	MemberBean checkACPassword(String userPassword);
}
