package _01_register.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
    private Integer memberId;
	private String userAccount;
	private String userPassword;
	private String nickName;
    private String birthday;
    private Integer gender;
    private Integer userRank;
    private Blob portrait;
    private String preference;
    private Clob collection;
    private Clob userGift;
    private Clob userFriends;
	
    public MemberBean() {
		super();
	}

	public MemberBean(Integer memberId, String userAccount, String userPassword, String nickName,
			String birthday, Integer gender, Integer userRank, Blob portrait, String preference, Clob collection,
			Clob userGift, Clob userFriends) {
		super();
		this.memberId = memberId;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.nickName = nickName;
		this.birthday = birthday;
		this.gender = gender;
		this.userRank = userRank;
		this.portrait = portrait;
		this.preference = preference;
		this.collection = collection;
		this.userGift = userGift;
		this.userFriends = userFriends;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
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
