package lab02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import lab02.model.MemberBean_HO73;
import lab02.utils.GlobalService;

public class MemberDao {
	int pageNo;
	int NUMBER_PER_PAGE = 2;
    InitialContext ctx ;
	public List<MemberBean_HO73> getAllMembers()  {
		Connection conn = null;
		List<MemberBean_HO73> allMembers = new ArrayList<MemberBean_HO73>();
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_NAME_MySQL);
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * from  member_HO73");
			ResultSet rs = stmt.executeQuery();
			MemberBean_HO73 mem = null;
			while (rs.next()) {
				mem = new MemberBean_HO73(rs.getString(1), rs.getString(2), rs
						.getString(4) ,rs.getString(16));
				allMembers.add(mem);
			}
			return allMembers;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public List<MemberBean_HO73> getPageMembers()  {
		Connection conn = null;
		List<MemberBean_HO73> allMembers = new ArrayList<MemberBean_HO73>();
		String sql = "SELECT * from  member_HO73 limit ?, ?";
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(GlobalService.JNDI_NAME_MySQL);
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			int startNo = (pageNo-1)* NUMBER_PER_PAGE;
			stmt.setInt(1, startNo);
			stmt.setInt(2, NUMBER_PER_PAGE);
			//從0開始兩筆資料,從2開始兩筆資料
			ResultSet rs = stmt.executeQuery();
			MemberBean_HO73 mem = null;
			while (rs.next()) {
				mem = new MemberBean_HO73(rs.getString(1), rs.getString(2), rs
						.getString(4) ,rs.getString(16));
				allMembers.add(mem);
			}
			return allMembers;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
