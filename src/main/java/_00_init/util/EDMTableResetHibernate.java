package _00_init.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

import _01_register.model.MemberBean;
import _01_register.model.Store;

public class EDMTableResetHibernate {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		System.out.println("Maven + Hibernate + MySQL 新增多筆資料到JSPDB");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int count = 0;
		try (
				// Member.txt存放要新增的多筆資料
				InputStreamReader isr0 = 
				new InputStreamReader(new FileInputStream("data/Member.txt"), "UTF-8");

				BufferedReader br = new BufferedReader(isr0);)
		// 由檔案("data/Member.txt")讀入Member的資料，然後寫入資料庫
		{
			String line = "";
			while ((line = br.readLine()) != null) {
				// 未處理BOM字元，若有需要，請自行加入
				String[] sa = line.split("\\|");
				try {
					tx = session.beginTransaction();
					MemberBean member = new MemberBean();
					member.setUserAccount(sa[0]);
					member.setUserPassword(GlobalService.getMD5Endocing(GlobalService.encryptString(sa[1])));
					member.setNickName(sa[2]);
//					member.setBirthday(Date.valueOf(sa[3]));
					//MemberBeane的Birthday資料型態Date改成String
					member.setBirthday(sa[3]);
					member.setGender(Integer.valueOf(Integer.parseInt(sa[4])));
					member.setUserRank(Integer.valueOf(Integer.parseInt(sa[5])));
					Blob sb = SystemUtils2018.fileToBlob(sa[6]);
					member.setPortrait(sb);
					member.setPreference(sa[7]);
					Clob clob8 = SystemUtils2018.stringToClob(sa[8].trim());
					member.setCollection(clob8);
					Clob clob9 = SystemUtils2018.stringToClob(sa[9].trim());
					member.setCollection(clob9);
					Clob clob10 = SystemUtils2018.stringToClob(sa[10]);
					member.setCollection(clob10);
					session.save(member);
					session.flush();
					tx.commit();
					count++;
					System.out.println("新增" + count + "筆記錄:" + sa[1]);

				} catch (Exception e) {
					e.printStackTrace();
					if (tx != null) {
						tx.rollback();
						System.out.println("Transaction rollback");
					}
				}
			}
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// ------------- 由檔案(data/Store.dat)讀取Store資料，寫入資料庫-----------------------
		try (InputStreamReader isr0 = new InputStreamReader(new FileInputStream("data/Store.dat"), "UTF-8");
				BufferedReader br = new BufferedReader(isr0);) {
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] sa = line.split("\\|");
				try {
					tx = session.beginTransaction();
					Store store = new Store();
					store.setStoreName(sa[0].trim());
					store.setStoreAddress(sa[1].trim());
					store.setStorePhone(sa[2].trim());
					store.setServiceHours(sa[3].trim());
					store.setStorePicture(sa[4].trim());
					store.setSortNumber(Integer.parseInt(sa[5].trim()));
					store.setStoreRecomCount(Integer.parseInt(sa[6].trim()));
					store.setStoreCommentCount(Integer.parseInt(sa[7].trim()));
					session.save(store);
					session.flush();
					tx.commit();
					count++;
					System.out.println("新增" + count + "筆記錄:" + sa[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (session != null) {
			session.close();
		}
		HibernateUtil.getSessionFactory().close();
	}

	// 計算一個文字檔的字元數
	private static long meteringReader(File f10) {
		long total = 0;
		int len = 0;
		try (FileReader reader = new FileReader(f10);) {
			char[] ca = new char[8192];
			while ((len = reader.read(ca)) != -1) {
				total += len;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total;
	}

}