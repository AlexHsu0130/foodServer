package repository;

import model.Member;

public interface MemberDao {
	boolean userLogin(String userAccount, String password);
	boolean checkAccount(String userAccount);
	
	int updateMemberDate(Member member, byte[] image);
//	int updateMemberDate(Member member);
	
	int insertMemberDate(Member member, byte[] image);

	byte[] getPortrait(String userAccount);	
	Member getUserDateNoPortrait(String userAccount);
}
