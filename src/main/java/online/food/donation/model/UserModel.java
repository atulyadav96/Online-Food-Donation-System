package online.food.donation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import online.food.donation.bean.UserBean;
import online.food.donation.exception.ApplicationException;
import online.food.donation.exception.DatabaseException;
import online.food.donation.exception.DuplicateRecordException;
import online.food.donation.exception.RecordNotFoundException;

import online.food.donation.utility.JDBCDataSource;


/**
 * JDBC Implementation of UserModel
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */
public class UserModel {
	private static Logger log = Logger.getLogger(UserModel.class);
	

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM user");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a User
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(UserBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		UserBean existbean = findByLogin(bean.getLogin());

		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		
		UserBean uniquebean = findByUniqueID(bean.getUnique_id());

		if (uniquebean != null) {
			throw new DuplicateRecordException("Unique Id already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getGender());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setString(9, bean.getRolename());
			pstmt.setString(10, bean.getUnique_id());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	/**
	 * Delete a User
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete(UserBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM F_USER WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public UserBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE email=?");
		UserBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setGender(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setRolename(rs.getString(9));
				bean.setUnique_id(rs.getString(10));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	public UserBean findByUniqueID(String uniqueId) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE uniqueid=?");
		UserBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, uniqueId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setGender(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setRolename(rs.getString(9));
				bean.setUnique_id(rs.getString(10));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}


	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public UserBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE ID=?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setGender(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setRolename(rs.getString(9));
				bean.setUnique_id(rs.getString(10));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a user
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public long Update(UserBean bean) {

		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update user set firstName=?,lastName=?, email=?, password=?,mobileno=?,gender=?,roleid=?,rolename=?,uniqueid=? where id=?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getGender());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setString(8, bean.getRolename());
			pstmt.setString(9, bean.getUnique_id());
			pstmt.setLong(10, bean.getId());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	/**
	 * Search User
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(UserBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(UserBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRSTNAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LASTNAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO = " + bean.getMobileNo());
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLEID = " + bean.getRoleId());
			}
			
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("user model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setGender(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setRolename(rs.getString(9));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of User
	 * 
	 * @return list : List of User
	 * @throws DatabaseException
	 */

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list() throws Exception {
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from user");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBean bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setGender(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setRolename(rs.getString(9));
			bean.setUnique_id(rs.getString(10));
			list.add(bean);
		}
		return list;
	}
	/**
	 * @param id
	 *            : long id
	 * @param old
	 *            password : String oldPassword
	 * @param new
	 *            password : String newPassword
	 * @throws DatabaseException
	 */

	public UserBean authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM user WHERE email = ? AND PASSWORD = ?");
		UserBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setGender(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setRolename(rs.getString(9));
				bean.setUnique_id(rs.getString(10));
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return bean;
	}

	

	/**
	 * Get User Roles
	 * 
	 * @return List : User Role List
	 * @param bean
	 * @throws ApplicationException
	 */

	public List getRoles(UserBean bean) throws ApplicationException {
		log.debug("Model get roles Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM F_USER WHERE roleId=?");
		Connection conn = null;
		List list = new ArrayList();
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, bean.getRoleId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				bean.setRolename(rs.getString(14));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model get roles End");
		return list;
	}

	/**
	 * @param id
	 *            : long id
	 * @param old
	 *            password : String oldPassword
	 * @param newpassword
	 *            : String newPassword
	 * @throws org.omg.CORBA.portable.ApplicationException
	 * @throws DatabaseException
	 */

	public boolean changePassword(Long id, String oldpassword, String newpassword)
			throws RecordNotFoundException,ApplicationException{
		System.out.println("in user model");
		boolean flag = false;
		UserBean beanExist = null;
		beanExist = findByPK(id);
		System.out.println("password"+beanExist.getPassword());
		System.out.println("new"+newpassword);
		System.out.println("old"+oldpassword);
		
			if (beanExist != null && beanExist.getPassword().equals(oldpassword)) {
				beanExist.setPassword(newpassword);
				Update(beanExist);
				flag = true;
			} else {
				throw new RecordNotFoundException("Old password is invalid");
			}
		return flag;

	}
	public UserBean updateAccess(UserBean bean) throws ApplicationException {
		return null;
	}

	/**
	 * Register a user
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 *             : throws when user already exists
	 * @throws org.omg.CORBA.portable.ApplicationException
	 */

	public long registerUser(UserBean bean)
			throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");

		long pk = add(bean);

		
		return pk;
	}

	/**
	 * Reset Password of User with auto generated Password
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws org.omg.CORBA.portable.ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */

	/*public boolean resetPassword(UserBean bean)
			throws ApplicationException, org.omg.CORBA.portable.ApplicationException {

		String newPassword = String.valueOf(new Date().getTime()).substring(0, 4);
		UserBean userData = findByPK(bean.getId());
		userData.setPassword(newPassword);

		try {
			update(userData);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirstName());
		map.put("lastName", bean.getLastName());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
	}*/

	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 * 
	 */

	public boolean forgetPassword(String login)
			throws ApplicationException, RecordNotFoundException, ApplicationException {
		UserBean userData = findByLogin(login);
		
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");

		}

		

		return flag;
	}
	
	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from USER where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public List userlist() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from user where roleid=2");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBean bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setGender(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setRolename(rs.getString(9));
			bean.setUnique_id(rs.getString(10));
			list.add(bean);
		}
		return list;
	}

}
