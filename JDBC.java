package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Statement;

public class JDBC {
	public static String sql;
	public static Connection conn = getConnection();
	public static PreparedStatement prstm = null;
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/qlct";
		String username = "hoaqin";
		String password = "Hung0511?";
		
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				
		}
		return null;
	}
	public static void closeConnection(Connection conn , Statement st , ResultSet rs) {
		
			try {
				while(conn!=null) {
					conn.close();}
				while(st!=null) {
					((Connection) st).close();}
				while(rs!=null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public ResultSet view(String table, String [] cols) {
		ResultSet resultSet = null;
		try {
		PreparedStatement prstm = (PreparedStatement) JDBC.getConnection();
		String sql = "SELECT";
		if (cols == null || cols.length == 0) {	
			sql += " * FROM ";
		} else {
			for (int i = 0; i <cols.length; i++) {
				sql += "`" + cols[i] + "`, ";
			}
			sql +=";";
			sql = sql.replace("`, ;", "` FROM");
		}
		sql += " " + table;
		resultSet = prstm.executeQuery(sql);
		} catch (SQLException e){
			return null;
		}
		return resultSet;
		
	}
	
	public static void main(String[] args) throws SQLException {
			Connection conn = getConnection();
			PreparedStatement prstm = null;
		try {
			 sql = "SELECT stt, the_loai_chi_tieu , ngay, so_tien, tai_khoan, the_loai, ghi_chu FROM thuchi1";
			prstm = conn.prepareStatement(sql);
			ResultSet rs = prstm.executeQuery();
			while (rs.next()) {
				int stt = rs.getInt(1);
				String theloaichitieu = rs.getString(2);
				String ngay = rs.getString(3);
				int sotien = rs.getInt(4);
				String taikhoan = rs.getString(5);
				String theloai = rs.getString(6);
				String ghichu = rs.getString(7);
			
				
				
				
		} 
			rs.close();
			prstm.close();
			conn.close();
		}catch(SQLException se){
            se.printStackTrace();
        }
		catch (Exception e) {
		e.printStackTrace();
	}	

}
}