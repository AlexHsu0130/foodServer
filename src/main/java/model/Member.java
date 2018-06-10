package model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer memberId;
	private String userAccount;
	private String userPassword;
	private String nickName;
    private Date birthday;
    private Integer gender;
    private Integer userRank;
    private Blob portrait;
    private String preference;
    private Clob collection;
    private Clob userGift;
    private Clob userFriends;
    
	public Member() {
		super();
	}

	public Member(String userAccount) {
		super();
		this.userAccount = userAccount;
	}

	public Member(String userAccount, String userPassword) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
	}
	
	public Member(String userAccount, String userPassword, String nickName, String birthday,
			Integer gender) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.nickName = nickName;
		this.birthday = Date.valueOf(birthday);
		this.gender = gender;
	}
	
	public Member(Integer memberId, String userPassword, String nickName, String birthday, Integer gender,
			Integer userRank, String preference, Clob collection, Clob userGift, Clob userFriends) {
		super();
		this.userPassword = userPassword;
		this.nickName = nickName;
		this.birthday = Date.valueOf(birthday);
		this.gender = gender;
		this.memberId = memberId;
		this.userRank = userRank;
		this.preference = preference;
		this.collection = collection;
		this.userGift = userGift;
		this.userFriends = userFriends;
	}

	public Member(String userAccount, String userPassword, String nickName, String birthday,
			int gender, int memberId, int userRank, Blob portrait, String preference,
			Clob collection, Clob userGift, Clob userFriends) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.nickName = nickName;
		this.birthday = Date.valueOf(birthday);
		this.gender = gender;
		this.memberId = memberId;
		this.userRank = userRank;
		this.portrait = portrait;
		this.preference = preference;
		this.collection = collection;
		this.userGift = userGift;
		this.userFriends = userFriends;
	}

//---------------getter--setter-------------------------------------
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = Date.valueOf(birthday);
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getUserId() {
		return memberId;
	}

	public void setUserId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getUserRank() {
		return userRank;
	}

	public void setUserRank(Integer userRank) {
		this.userRank = userRank;
	}
	
	public Blob getPortrait() {
		return portrait;
	}

	public void setPortrait(Blob portrait) {
		this.portrait = portrait;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public Clob getCollection() {
		return collection;
	}

	public void setCollection(Clob collection) {
		this.collection = collection;
	}

	public Clob getUserGift() {
		return userGift;
	}

	public void setUserGift(Clob userGift) {
		this.userGift = userGift;
	}

	public Clob getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(Clob userFriends) {
		this.userFriends = userFriends;
	}
}
