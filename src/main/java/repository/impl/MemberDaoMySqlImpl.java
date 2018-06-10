package repository.impl;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import init.utils.Common;
import model.Member;
import repository.MemberDao;



public class MemberDaoMySqlImpl implements MemberDao {

	public MemberDaoMySqlImpl() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean userLogin(String userAccount, String userPassword) {
		String sql = "SELECT UserAccount FROM member WHERE UserAccount = ? AND UserPassword = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUser = false;
		try {
			conn = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userAccount);
			ps.setString(2, userPassword);
			ResultSet rs = ps.executeQuery();
			isUser = rs.next();
			return isUser;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUser;
	}
	
	
	@Override
	public boolean checkAccount(String userAccount) {
		String sql = "SELECT UserAccount FROM member WHERE UserAccount = ?;";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean usable = false;
		try {
			conn = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setString(1, userAccount);
			ResultSet rs = ps.executeQuery();
			usable = rs.next();
			return usable;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usable;
	}
	
	@Override
	public int insertMemberDate(Member member, byte[] portrait) {
		int count = 0;
		String sql = "INSERT INTO member"
				+ "(UserAccount, UserPassword, Nickname, Birthday, Gender, portrait) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, member.getUserAccount());
			ps.setString(2, member.getUserPassword());
			ps.setString(3, member.getNickName());
			ps.setDate(4, member.getBirthday());
			ps.setInt(5, member.getGender());
			ps.setBytes(6, portrait);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	@Override
	public int updateMemberDate(Member member, byte[] portrait) {
//	public int updateMemberDate(Member member) {
		int count = 0;
		String sql = "UPDATE member SET UserPassword = ?, Nickname = ?, Birthday = ?, Gender = ? , Portrait = ? WHERE UserAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, member.getUserPassword());
			ps.setString(2, member.getNickName());
			ps.setDate(3, member.getBirthday());
			ps.setInt(4, member.getGender());
			ps.setBytes(5, portrait);
			ps.setString(6, member.getUserAccount());
			
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public byte[] getPortrait(String userAccount) {
		String sql = "SELECT portrait FROM Member WHERE userAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		byte[] portrait = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, userAccount);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				portrait = rs.getBytes(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					// When a Statement object is closed,
					// its current ResultSet object is also closed
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return portrait;
	}
	
	@Override
	public Member getUserDateNoPortrait(String userAccount1) {
		String sql = "SELECT userAccount, UserPassword, Nickname, Birthday, Gender, UserRank,"
				+ " Preference, Collection, UserGift, UserFriends "
				+ " FROM member WHERE UserAccount = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		Member member = null;
		try {
			connection = DriverManager.getConnection(Common.URL, Common.USER,
					Common.PASSWORD);
			ps = connection.prepareStatement(sql);
			ps.setString(1, userAccount1);
			ResultSet rs = ps.executeQuery();
//			Blob portrait = null;
			while (rs.next()) {
				String userAccount = rs.getString(1);
				String userPassword = rs.getString(2);
				String nickName = rs.getString(3);
				String birthday = rs.getDate(4).toString();							
				Integer gender = rs.getInt(5);
				Integer userRank = rs.getInt(6);
				String preference = rs.getString(7);
				Clob collection = rs.getClob(8);
				Clob userGift = rs.getClob(9);
				Clob userFriends = rs.getClob(10);
				
				member = new Member(userAccount, userPassword, nickName, birthday, gender);
			}
			return member;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

}
