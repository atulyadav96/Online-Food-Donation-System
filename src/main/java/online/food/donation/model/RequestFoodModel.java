package online.food.donation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import online.food.donation.bean.RequestFoodBean;
import online.food.donation.bean.UserBean;
import online.food.donation.exception.ApplicationException;
import online.food.donation.utility.JDBCDataSource;

public class RequestFoodModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM requestfood");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public long add(RequestFoodBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			System.out.println("PK:" + pk);
			conn.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO requestfood VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getNgoName());
			ps.setString(3, bean.getCategory());
			ps.setString(4, bean.getFoodname());
			ps.setLong(5, bean.getQuantity());
			ps.setString(6, bean.getAddress());
			ps.setString(7, bean.getSendername());
			ps.setLong(8, bean.getSendercontactno());
			ps.setString(9, bean.getStatus());
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
	
	public List Donationlist() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from requestfood");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			RequestFoodBean bean = new RequestFoodBean();
			bean.setId(rs.getLong(1));
			bean.setNgoName(rs.getString(2));
			bean.setCategory(rs.getString(3));
			bean.setFoodname(rs.getString(4));
			bean.setQuantity(rs.getLong(5));
			bean.setAddress(rs.getString(6));
			bean.setSendername(rs.getString(7));
			bean.setSendercontactno(rs.getLong(8));
			bean.setStatus(rs.getString(9));
			list.add(bean);
		}
		return list;
	}
	
	public List DonationList(String ngoname) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from requestfood where ngoname=?");
		pstmt.setString(1, ngoname);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			RequestFoodBean bean = new RequestFoodBean();
			bean.setId(rs.getLong(1));
			bean.setNgoName(rs.getString(2));
			bean.setCategory(rs.getString(3));
			bean.setFoodname(rs.getString(4));
			bean.setQuantity(rs.getLong(5));
			bean.setAddress(rs.getString(6));
			bean.setSendername(rs.getString(7));
			bean.setSendercontactno(rs.getLong(8));
			bean.setStatus(rs.getString(9));
			list.add(bean);
		}
		return list;
	}


	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from requestfood where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public RequestFoodBean findByPk(long pk) throws Exception {
		RequestFoodBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM requestfood WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new RequestFoodBean();
				bean.setId(rs.getLong(1));
				bean.setNgoName(rs.getString(2));
				bean.setCategory(rs.getString(3));
				bean.setFoodname(rs.getString(4));
				bean.setQuantity(rs.getLong(5));
				bean.setAddress(rs.getString(6));
				bean.setSendername(rs.getString(7));
				bean.setSendercontactno(rs.getLong(8));
				bean.setStatus(rs.getString(9));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public long Update(RequestFoodBean bean) {
		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update requestfood set  ngoname=?,category=?,food=?,quantity=?,address=?,sendername=?,senderconatctNo=?,status=? where id=?");
			ps.setString(1, bean.getNgoName());
			ps.setString(2, bean.getCategory());
			ps.setString(3, bean.getFoodname());
			ps.setLong(4, bean.getQuantity());
			ps.setString(5, bean.getAddress());
			ps.setString(6, bean.getSendername());
			ps.setLong(7, bean.getSendercontactno());
			ps.setString(8, bean.getStatus());
			ps.setLong(9, bean.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}




}
