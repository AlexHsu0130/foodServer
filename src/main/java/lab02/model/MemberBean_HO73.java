package lab02.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class MemberBean_HO73 implements Serializable {
	String memAccount;			//會員帳號
	String memName;				//會員名稱
	String memIdcard;			//身分證字號
	String memEmail;			//會員 E-mail
	String memEmail2;			//備用 E-mail
	String memGender;			//會員性別		"M:男	F:女"
	Date memBirthday;		    //會員生日
	String memTel;				//連絡電話
	String memMobile;			//行動電話
	String memAddress;			//地址
	Integer memType;			//會員類別		"0:系統管理員	1:一般會員	 2:基金會會員"
	Integer memFoundation;		//基金會代碼	"memType=2	foundation.funUid"
	String memWhySupply;		//為什麼加入公益
	Timestamp insertDate;		//新增日期
	Integer memStatus;			//狀態		"0:無效		1:有效"
	String memPicUrl;			//會員頭像的URL
	
	
	public MemberBean_HO73() {
		super();
	}
	public MemberBean_HO73(String memAccount, String memName, String memEmail, String memPicUrl) {
		super();
		this.memAccount = memAccount;
		this.memName = memName;
		this.memEmail = memEmail;
		this.memPicUrl = memPicUrl;
	}
	public MemberBean_HO73(String memAccount, String memName, String memIdcard, String memEmail, String memEmail2,
			String memGender, Date memBirthday, String memTel, String memMobile, String memAddress,
			Integer memType, Integer memFoundation, String memWhySupply, Timestamp insertDate, Integer memStatus,
			String memPicUrl) {
		super();
		this.memAccount = memAccount;
		this.memName = memName;
		this.memIdcard = memIdcard;
		this.memEmail = memEmail;
		this.memEmail2 = memEmail2;
		this.memGender = memGender;
		this.memBirthday = memBirthday;
		this.memTel = memTel;
		this.memMobile = memMobile;
		this.memAddress = memAddress;
		this.memType = memType;
		this.memFoundation = memFoundation;
		this.memWhySupply = memWhySupply;
		this.insertDate = insertDate;
		this.memStatus = memStatus;
		this.memPicUrl = memPicUrl;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemIdcard() {
		return memIdcard;
	}
	public void setMemIdcard(String memIdcard) {
		this.memIdcard = memIdcard;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemEmail2() {
		return memEmail2;
	}
	public void setMemEmail2(String memEmail2) {
		this.memEmail2 = memEmail2;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public Date getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(Date memBirthday) {
		this.memBirthday = memBirthday;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemMobile() {
		return memMobile;
	}
	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public Integer getMemType() {
		return memType;
	}
	public void setMemType(Integer memType) {
		this.memType = memType;
	}
	public Integer getMemFoundation() {
		return memFoundation;
	}
	public void setMemFoundation(Integer memFoundation) {
		this.memFoundation = memFoundation;
	}
	public String getMemWhySupply() {
		return memWhySupply;
	}
	public void setMemWhySupply(String memWhySupply) {
		this.memWhySupply = memWhySupply;
	}
	public Timestamp getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public String getMemPicUrl() {
		return memPicUrl;
	}
	public void setMemPicUrl(String memPicUrl) {
		this.memPicUrl = memPicUrl;
	}
	
	
	
}
