package lab02._00;
/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：eMember, eBook, eBookCompany, Orders, OrderItems
      
(1) eMember欄位說明:
    seqNo       : 會員流水號Pri.Key  整數      AUTO_INCREMENT
    memberId    : 會員編號           字串      20
    name        : 姓名               字串      32
    password    : 密碼               字串      32
    address     : 地址               字串      64
    email       : email              字串      64
    tel         : 電話日             字串      15
    userType    : 會員類別           字串      10
    experience  : 使用經驗           整數   
    register    : 註冊時間           datetime
    totalAmount : 訂購總金額         money
    memberImage : 照片               LongBlob
    fileName    : 封面圖檔檔名       字串      20
    
(2) eBook欄位說明:
    bookId      : 書籍代號Pri.Key    整數      AUTO_INCREMENT
    title       : 書籍名稱           字串      50
    author      : 作者               字串      28
    price       : 價格               money
    discount    : 折扣               money
    companyId   : 出版社代號         整數      
    fileName    : 封面圖檔檔名       字串      20
    bookNo      : 書號               字串      20
    CoverImage  : 封面照片           LongBlob   

(3) eBookCompany欄位說明:
    id 		     : PrimaryKey         整數      AUTO_INCREMENT
    name         : 出版社名稱         字串      60
    address      : 出版社地址         字串      60
    url          : 出版社URL          字串      120
    
(4) Orders欄位說明:
    orderNo        : 訂單編號PrimaryKey 整數      AUTO_INCREMENT
    userId         : 客戶編號           字串      20
    totalAmount    : 總金額             money
    shippingAddress: 出貨地址           字串      64    
    BNO            : 統一編號           字串       8
    invoiceTitle   : 發票抬頭           字串      72
    orderDate      : 訂單日期           datetime 
    ShippingDate   : 出貨日期           datetime
    CancelTag      : 取消               字串       1
    
(5) OrderItems欄位說明:
    seqNo        : PrimaryKey            整數      AUTO_INCREMENT
    orderNo      : 訂單編號              整數
    bookID       : 書籍代號              整數
    Description  : 說明                  字串      72
    amount       : 數量                  int
    unitPrice    : 單價                  money
    Discount     : 折扣                  money
 
*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import lab02.model.MemberBean_HO73;
import lab02.utils.GlobalService;

public class InitailMemberMySql {

   
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		int count = 0;
		PreparedStatement pstmt = null;
		String dropString;
		String createString;
		String line = "";
		String sql = "";
		System.out.println("URL: " + GlobalService.DB_URLMySQL);
		System.out.println("帳號: " + GlobalService.USERIDMySQL);
		System.out.println("密碼: " + GlobalService.PASSWORDMySQL);
		try (
			// 連上後端的資料庫
			Connection con = DriverManager.getConnection(
					GlobalService.DB_URLMySQL, 
					GlobalService.USERIDMySQL,
					GlobalService.PASSWORDMySQL);
			// 建立Statement物件，以便傳送SQL命令到後端的資料庫
			Statement stmt = con.createStatement();) {
			// 定義刪除Member_HO73表格的SQL命令
			dropString = "DROP TABLE IF EXISTS Member_HO73 ";
			// 定義新建Member_HO73表格的SQL命令
			createString = "CREATE TABLE Member_HO73 (" 
					+ " memAccount varchar(50)  PRIMARY KEY, "
					+ " memName varchar(50),"
					+ " memIdcard varchar(20),"
					+ " memEmail varchar(100), " 
					+ " memEmail2 varchar(100),"
					+ " memGender  varchar(2),"
					+ " memBirthday datetime," 
					+ " memTel varchar(20),"
					+ " memMobile  varchar(20),"
					+ " memAddress varchar(225),  "
					+ " memType int,"
					+ " memFoundation int,"
					+ " memWhySupply varchar(225),"
					+ " insertDate datetime, " 
					+ " memStatus int,"
					+ " memPicUrl varchar(225))  "
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE utf8_unicode_ci " ;

			try {
				// 執行刪除Member_HO73表格的SQL命令
				stmt.executeUpdate(dropString);
				// 執行新建Member_HO73表格的SQL命令
				stmt.executeUpdate(createString);
				// 印出執行成功的訊息
				System.out.println("Member_HO73表格產生成功");
			} catch(SQLException ex){
				ex.printStackTrace();
				throw new RuntimeException("新建Member_HO73表格時發生例外: " + ex.getMessage());
			}

			try (
				FileInputStream fis = new FileInputStream("data/Input.txt");
				InputStreamReader isr0 = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr0);
			) {
				while ((line = br.readLine()) != null) {
					String[] sa = line.split("\\|");
					MemberBean_HO73 bean = new MemberBean_HO73();
					bean.setMemAccount(sa[0]);
					bean.setMemName(sa[1]);
					bean.setMemIdcard(sa[2]);
					bean.setMemEmail(sa[3]);
					bean.setMemEmail2(sa[4]);
					bean.setMemGender(sa[5]);
					bean.setMemBirthday(Date.valueOf(sa[6]));
					bean.setMemTel(sa[7]);
					bean.setMemMobile(sa[8]);
					bean.setMemAddress(sa[9]);
					bean.setMemType(Integer.parseInt(sa[10]));
					bean.setMemFoundation(Integer.parseInt(sa[11]));
					bean.setMemWhySupply(sa[12]);
					bean.setInsertDate(Timestamp.valueOf(sa[13]));
					bean.setMemStatus(Integer.parseInt(sa[14]));
					bean.setMemPicUrl(sa[15]);
					
					saveMember(bean, con);
					count++;
					System.out.println("新增" + count + "筆記錄:" + sa[1]);
				}
				// 印出Member_HO73資料新增成功的訊息
				System.out.println("Member_HO73 資料新增成功");
			} catch (Exception ex) {
				System.out.println("讀取文字檔(data/Input.txt)時發生例外");
				ex.printStackTrace();
			}
		  } catch (SQLException e) {
			e.getMessage();
		}
			
		}
	
	static public int saveMember(MemberBean_HO73 mb, Connection con) {
		String sql = "INSERT INTO Member_HO73 "
				+ "VALUES(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?)";
		int n = 0;
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, mb.getMemAccount());
			pstmt.setString(2, mb.getMemName());
			pstmt.setString(3, mb.getMemIdcard());
			pstmt.setString(4, mb.getMemEmail());
			pstmt.setString(5, mb.getMemEmail2());
			pstmt.setString(6, mb.getMemGender());	
			Date bd = new Date(0);
			bd.setTime(mb.getMemBirthday().getTime());
			pstmt.setDate(7, bd);
			pstmt.setString(8, mb.getMemTel());
			pstmt.setString(9, mb.getMemMobile());
			pstmt.setString(10, mb.getMemAddress());
			pstmt.setInt(11, mb.getMemType());
			pstmt.setInt(12, mb.getMemFoundation());
			pstmt.setString(13, mb.getMemWhySupply());
//or		SerialClob clob = new SerialClob(mb.getMemWhySupply());
//			pstmt.setClob(13, clob);
			pstmt.setTimestamp(14, mb.getInsertDate());
			pstmt.setInt(15, mb.getMemStatus());
			pstmt.setString(16, mb.getMemPicUrl());
			n = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}

}