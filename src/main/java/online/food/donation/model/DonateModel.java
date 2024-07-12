package online.food.donation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.food.donation.bean.DonateBean;
import online.food.donation.bean.UserBean;
import online.food.donation.exception.ApplicationException;
import online.food.donation.utility.JDBCDataSource;

public class DonateModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM donation");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public long add(DonateBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		
		NGOModel ngmodel = new NGOModel();
		System.out.println("NGOID:"+bean.getNgoid());
		UserBean ubean = ngmodel.findByPk(bean.getNgoid());
		System.out.println("NGO:"+ubean.getNgoname());
		String ngoname = ubean.getNgoname();
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			System.out.println("PK:" + pk);
			conn.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO donation VALUES(?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getCategory());
			ps.setString(3, bean.getFood());
			ps.setLong(4, bean.getQuamtity());
			ps.setLong(5, bean.getNgoid());
			ps.setString(6, ngoname);
			ps.setString(7, bean.getDonarname());
			ps.setLong(8, bean.getUserid());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + e.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}
	
	public List DonateFoodlist() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from donation");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DonateBean bean = new DonateBean();
			bean.setId(rs.getLong(1));
			bean.setCategory(rs.getString(2));
			bean.setFood(rs.getString(3));
			bean.setQuamtity(rs.getLong(4));
			bean.setNgoid(rs.getLong(5));
			bean.setNgoname(rs.getString(6));
			bean.setDonarname(rs.getString(7));
			bean.setUserid(rs.getLong(8));
			list.add(bean);
		}
		return list;
	}
	
	public List DonateFoodList(long userid) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from donation WHERE userid=?");
		pstmt.setLong(1, userid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			DonateBean bean = new DonateBean();
			bean.setId(rs.getLong(1));
			bean.setCategory(rs.getString(2));
			bean.setFood(rs.getString(3));
			bean.setQuamtity(rs.getLong(4));
			bean.setNgoid(rs.getLong(5));
			bean.setNgoname(rs.getString(6));
			bean.setDonarname(rs.getString(7));
			bean.setUserid(rs.getLong(8));
			list.add(bean);
		}
		return list;
	}

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from donation where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

}
