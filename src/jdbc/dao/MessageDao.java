package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.bean.Message;
import jdbc.bean.User;
import jdbc.common.ConnectionUtil;

public class MessageDao {
	public List<Message> getMessages(int page, int pagesize){
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM message ORDER BY create_time DESC LIMIT ?,? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message>result = new ArrayList<>();
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, (page-1) * pagesize);
			 pstmt.setInt(2, pagesize);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 result.add(new Message(rs.getLong("id"), rs.getLong("user_id"), rs.getString("username"), rs.getString("title"),
						 rs.getString("content"), rs.getTimestamp("create_time")));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.release(rs, pstmt, conn);
		}
		return result;
	}
	
	public int countMessages() {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) total FROM message";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message>result = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.release(rs, pstmt, conn);
		}
		
		return 0;
	}
	
	public boolean save(Message message){
		Connection conn = ConnectionUtil.getConnection();
		String sql = "INSERT INTO message (user_id , username, title, content, create_time) VALUES(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, message.getUserid());
			pstmt.setString(2, message.getUsername());
			pstmt.setString(3, message.getTitle());
			pstmt.setString(4, message.getContent());
			pstmt.setTimestamp(5, new Timestamp(message.getCreatetime().getTime()));
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("saving message failed");
			e.printStackTrace();
			return false;
		} finally {
			ConnectionUtil.release(null, pstmt, conn);
		}
		return true;
	}
	
	public List<Message> getMyMessages(User user, int page, int pagesize){
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM message WHERE username = ? ORDER BY create_time DESC LIMIT ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message>result = new ArrayList<>();
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, user.getUsername());
			 pstmt.setInt(2, (page-1) * pagesize);
			 pstmt.setInt(3, pagesize);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 result.add(new Message(rs.getLong("id"), rs.getLong("user_id"), rs.getString("username"), rs.getString("title"),
						 rs.getString("content"), rs.getTimestamp("create_time")));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.release(rs, pstmt, conn);
		}
		return result;
	}
	
	public boolean deleteMessage(Long id) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "DELETE FROM message WHERE id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			int deleteNums = pstmt.executeUpdate();
			if(deleteNums > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ConnectionUtil.release(null, pstmt, conn);
		}
		return false;
	}
	
	public Message queryMessage(Long id) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM message WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Message message = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				message = new Message(rs.getLong("id"), rs.getLong("user_id"), rs.getString("username"), rs.getString("title"), rs.getString("content"), rs.getTimestamp("create_time"));
				return message;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtil.release(rs, pstmt, conn);
		}
		return message;
	}
	
	public boolean updateMessage(Long id, String title, String content) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE message SET title=?, content=?, create_time=? WHERE id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
			pstmt.setLong(4, id);
			int num = pstmt.executeUpdate();
			if(num > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ConnectionUtil.release(null, pstmt, conn);
		}
		return false;
	}
}
