package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import jdbc.bean.User;
import jdbc.common.ConnectionUtil;

public class UserDao {
	public User login(String username, String password) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("real_name"), rs.getDate("birthday"), rs.getString("phone"),
			rs.getString("address"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.release(rs, pstmt, conn);
		}
		return user;
	}
	
	public User getUserById(long id) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT * FROM user WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("real_name"), rs.getDate("birthday"), rs.getString("phone"),
			rs.getString("address"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.release(rs, pstmt, conn);
		}
		return user;		
	}
	
	public boolean updateUser(User user) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE user SET username=?, password=?, real_name=?, birthday=?, phone=?, address=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getReal_name());
			pstmt.setDate(4,  new java.sql.Date(user.getBirthday().getTime()));
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getAddress());
			pstmt.setLong(7, user.getId());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ConnectionUtil.release(null, pstmt, conn);
		}
	    return true;	
	}
	
	public boolean registerUser(User user) {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM user WHERE username=?";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return false;
			}
			sql = "INSERT INTO user (username,password) VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ConnectionUtil.release(null, pstmt, conn);
		}
		return true;
	}
}
