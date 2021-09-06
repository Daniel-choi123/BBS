package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	public Connection conn;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public UserDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?useUnicode=true&serverTimezone=Asia/Seoul", "root", "jumi0821");
			
		} catch(Exception e) {
			System.out.println("ERROR1");
			System.out.println(e.getMessage());
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword from user where userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 0;
				}
				else {
					return -3; // password error
				}
			}
			return -1; // no id
		} catch(Exception e) {
			System.out.println("SQL: ERROR2");
			System.out.println(e.getMessage());
		}
		return -2; // DB error
	}
	
	public int join(User user) {
		String SQL = "Insert into USER values (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2;
	}
	
	
	
	
	
	
}
