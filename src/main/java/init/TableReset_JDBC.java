package init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Member, Store, StoreComment, Message, Gift
      
(1) Member 欄位說明:
    MemberId      	  : 會員編號			INT				主鍵, AUTO_INCREMENT
    UserAccount		  : 帳號				VARCHAR(40)		NOT NULL, 信箱
    UserPassword	  : 密碼				VARCHAR(12)		NOT NULL
    Nickname		  : 暱稱				VARCHAR(20)		預設   UserAccount "@"前字串
    Birthday		  : 生日				DATE			NOT NULL
    Gender		 	  : 性別				TINYINT			NOT NULL, (0~1), 0=女, 1=男
    UserRank		  : 會員級別			TINYINT			(1~9) 預設 1, root=0
    Portrait		  : 頭像				MEDIUMBLOB
    Preference		  : 喜好種類			VARCHAR(20)		紀錄 SortNumber
    Collection		  : 收藏				TEXT			紀錄 StoreId
    UserGift		  : 禮物				TEXT			紀錄 GiftId
    UserFriends		  : 追蹤				TEXT			紀錄 MemberId
    
==============================================================================
(2) Store 欄位說明:
    StoreId      	  : 店家編號			INT				主鍵, AUTO_INCREMENT
    StoreName         : 店家名稱			VARCHAR(40)		NOT NULL
    StoreAddress      : 店家地址			VARCHAR(100)	NOT NULL
    StorePhone     	  : 店家電話			VARCHAR(15)
    ServiceHours 	  : 營業時間			VARCHAR(40)
    StorePicture  	  : 店家圖片(圖片名稱)	Blob			NOT NULL
    SortNumber   	  : 分類編號       		TINYINT			NOT NULL, (0~9)
    StoreRecomCount   : 推薦數			INT				預設 0
    StoreCommentCount : 評論數			INT				預設 0
      
==============================================================================
(3) StoreComment 欄位說明:
    CommentId    	  : 評論編號			INT				主鍵, AUTO_INCREMENT
    CommentMId        : 會員編號			INT				外來鍵
    CommentSId     	  : 店家編號			INT				外來鍵
    CommentPicture    : 評論圖片			LONGBLOB        
    CommentContent    : 評論內容			TEXT			NOT NULL
    CommentAlterCount : 修改次數			INT				預設 0
    CommentRecomCount : 推薦數			INT				預設 0
    CommentDate       : 評論時間			DATETIME   		NOW()
    
==============================================================================
(4) Message 欄位說明:
    MessageId      	  : 留言編號			INT				主鍵, AUTO_INCREMENT
    MessageDate       : 留言發佈時間        		DATETIME		NOW()
    MsgContent    	  : 留言內容             		VARCHAR(40)		NOT NULL
    MsgCId      	  : 評論編號           		INT				外來鍵
    MsgMId            : 會員編號           		INT				外來鍵
    
==============================================================================    
(5) Gift 欄位說明:
    GiftId			  : 禮物編號			INT				主鍵, AUTO_INCREMENT
	GiftName		  : 禮物名稱			VARCHAR(40)		NOT NULL
	GiftPicture		  : 禮物圖片			MEDIUMBLOB		NOT NULL
	GiftContent		  : 禮物內容			VARCHAR(40)		NOT NULL
	GiftDeadline	  : 截止日期			DATE			NOT NULL
 
*/
import java.io.*;
import java.sql.*;

import init.utils.*;
import model.*;

import init.utils.DBService;

public class TableReset_JDBC {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		String tableName = "";
		try (
			Connection con = DriverManager.getConnection(
								DBService.getDbUrl(), 
								DBService.getMySQLUser(),
								DBService.getMySQLPassword()); 
			Statement stmt = con.createStatement();
		) {
			// 因 FOREIGN KEY 規則 
			//需先刪除 Message表格--> StoreComment --> *
			
			//1. 刪除表格
			tableName = "Gift";
			stmt.executeUpdate(DBService.getDropGift());
			System.out.println("Gift表格刪除成功");
			tableName = "Message";
			stmt.executeUpdate(DBService.getDropMessage());
			System.out.println("Message表格刪除成功");
			tableName = "Comment";
			stmt.executeUpdate(DBService.getDropStoreComment());
			System.out.println("StoreComment表格刪除成功");
			tableName = "Store";
			stmt.executeUpdate(DBService.getDropStore());
			System.out.println("Store表格刪除成功");
			tableName = "Member";
			stmt.executeUpdate(DBService.getDropMember());
			System.out.println("Member表格刪除成功");
			
			//創建表格				
			stmt.executeUpdate(DBService.getCreateMember());
			System.out.println("Member表格產生成功");
			tableName = "Store";
			stmt.executeUpdate(DBService.getCreateStore());
			System.out.println("Store表格產生成功");
			tableName = "Comment";
			stmt.executeUpdate(DBService.getCreateStoreComment());
			System.out.println("StoreComment表格產生成功");
			tableName = "Message";
			stmt.executeUpdate(DBService.getCreateMessage());
			System.out.println("Message表格產生成功");	
			tableName = "Gift";
			stmt.executeUpdate(DBService.getCreateGift());
			System.out.println("Gift表格產生成功");	
			tableName = "";
			System.out.println("============================================");
			
			// Member資料
			System.out.println("新增  Member資料");
			//	由"data/Member.dat"逐筆讀入Member表格內的初始資料，然後依序新增到Member表格中
			String line = "";
			int count = 0;
			try (
				FileInputStream fisMember = new FileInputStream("data/Member.dat");
				InputStreamReader isrMember = new InputStreamReader(fisMember, "UTF8");
				BufferedReader brMember = new BufferedReader(isrMember);
			) {
				while ((line = brMember.readLine()) != null) {
					// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] memberString = line.split("\\|");
					Member member = new Member();
					member.setUserAccount(memberString[0].trim());
					member.setUserPassword(memberString[1].trim());
					member.setNickName(memberString[2].trim());
					member.setBirthday(memberString[3].trim());
					member.setGender(Integer.parseInt(memberString[4].trim()));
					member.setUserRank(Integer.parseInt(memberString[5].trim()));
					// 讀取圖片檔
					Blob portrait = SystemUtils.fileToBlob(memberString[6].trim());
					member.setPortrait(portrait);
					member.setPreference(memberString[7].trim());					
					Clob collection = SystemUtils.stringToClob(memberString[8].trim());	
					member.setCollection(collection);
					Clob userGift = SystemUtils.stringToClob(memberString[9].trim());
					member.setUserGift(userGift);
					Clob userFriends = SystemUtils.stringToClob(memberString[10]);
					member.setUserFriends(userFriends);
					
					int n = saveMember(member, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println(memberString[0] + s);
				}
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("Member資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}
			
			// Store資料
			System.out.println("新增  Store資料");
			//	由"data/Store.dat"逐筆讀入Store表格內的初始資料，
			//  然後依序新增到Store表格中	
			try (
			FileInputStream fisStore = new FileInputStream("data/Store.dat");
			InputStreamReader isrStore = new InputStreamReader(fisStore, "UTF8");
			BufferedReader brStore = new BufferedReader(isrStore);
			) {
				while ((line = brStore.readLine()) != null) {
				// 去除 UTF8_BOM
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] storeString = line.split("\\|");
					Store store = new Store();
					store.setStoreName(storeString[0].trim());
					store.setStoreAddress(storeString[1].trim());
					store.setStorePhone(storeString[2].trim());
					store.setServiceHours(storeString[3].trim());
					store.setStorePicture(storeString[4].trim());
					store.setSortNumber(Integer.parseInt(storeString[5].trim()));
					store.setStoreRecomCount(Integer.parseInt(storeString[6].trim()));
					store.setStoreCommentCount(Integer.parseInt(storeString[7].trim()));
					
					int n = saveStore(store, con);
					String s = seccessOrFail(n);
					count++;
					System.out.print("新增第 " + count + "筆記錄 : ");
					System.out.println(storeString[0] + s);
				}
			} catch (Exception ex) {
				count++;
				System.err.println("新增第 " + count + "筆記錄時發生例外: " + ex.getMessage());
				ex.printStackTrace();
			} finally {
				// 印出資料新增成功的訊息
				System.out.println("Store資料新增成功，共 " + count + "筆記錄");
				count = 0;
				System.out.println("============================================");
			}
			
		} catch (SQLException e) {
			System.err.println("新建 " + tableName + " 表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("新建 " + tableName + " 表格時發生IO例外: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	static public int saveMember(Member member, Connection con) {
		String sql = "INSERT INTO Member "
//				+ " ( UserAccount, UserPassword, Nickname, Birthday, Gender, UserRank, "
//				+ " Portrait, Preference, Collection, UserGift, UserFriends) "
				+ "	VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		int n = 0;
		try(
			PreparedStatement ps = 	con.prepareStatement(sql);
		) {
			ps.setString(1, member.getUserAccount());
			ps.setString(2, member.getUserPassword());
			ps.setString(3, member.getNickName());
			ps.setDate(4, member.getBirthday());
			ps.setInt(5, member.getGender());
			ps.setInt(6, member.getUserRank());
			ps.setBlob(7, member.getPortrait());
			ps.setString(8, member.getPreference());
			ps.setClob(9, member.getCollection());
			ps.setClob(10, member.getUserGift());
			ps.setClob(11, member.getUserFriends());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		return n;
	}
	
	static public int saveStore(Store Store, Connection con) {
		String sql = "INSERT INTO Store "
//				+ " ( StoreName, StoreAddress, StorePhone, ServiceHours, StorePicture, "
//				+ " SortNumber, StoreRecomCount, StoreCommentCount) "
				+ "	VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		int n = 0;
		try(
				PreparedStatement ps = 	con.prepareStatement(sql);
				) {
			ps.setString(1, Store.getStoreName());
			ps.setString(2, Store.getStoreAddress());
			ps.setString(3, Store.getStorePhone());
			ps.setString(4, Store.getServiceHours());
			ps.setString(5, Store.getStorePicture());
			ps.setInt(6, Store.getSortNumber());
			ps.setInt(7, Store.getStoreRecomCount());
			ps.setInt(8, Store.getStoreCommentCount());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		return n;
	}
	
	static public String seccessOrFail(int con) {
		String s = " --> ";
		if (con > 0) {
			s += "Seccess";
			return s;
		} else {			
			s += "Fail";
			return s;
		}
		
	}
}