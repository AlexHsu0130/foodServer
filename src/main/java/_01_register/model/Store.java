package _01_register.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String storeName;
	private String storeAddress;
	private String storePhone;
	private String serviceHours;
	private String storePicture;
	private Integer sortNumber;
	private Integer storeRecomCount;
	private Integer storeCommentCount;

	public Store() {
		super();
	}

	

	public Store(Integer storeId, String storeName, String storeAddress, String storePhone, String serviceHours,
			String storePicture, Integer sortNumber, Integer storeRecomCount, Integer storeCommentCount) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.storePhone = storePhone;
		this.serviceHours = serviceHours;
		this.storePicture = storePicture;
		this.sortNumber = sortNumber;
		this.storeRecomCount = storeRecomCount;
		this.storeCommentCount = storeCommentCount;
	}



	// ---------------getter--setter-------------------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}

	public String getStorePicture() {
		return storePicture;
	}

	public void setStorePicture(String storePicture) {
		this.storePicture = storePicture;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public Integer getStoreRecomCount() {
		return storeRecomCount;
	}

	public void setStoreRecomCount(Integer storeRecomCount) {
		this.storeRecomCount = storeRecomCount;
	}

	public Integer getStoreCommentCount() {
		return storeCommentCount;
	}

	public void setStoreCommentCount(Integer storeCommentCount) {
		this.storeCommentCount = storeCommentCount;
	}
}
