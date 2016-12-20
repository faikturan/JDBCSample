package com.faikturan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample01 {
//JDBC driver name and database URL
	static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
	//Database Kimlik
	static final String USER = "root";
	static final String PASS = "123456";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			//2.Aþama JDBC driver kayýt
			Class.forName(JDBC_Driver);
			//3.Aþama Veritabanýna Baglan
			System.out.println("Veritabanýna Baglan...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//4.aþama Sorgu Çalýþtýr
			System.out.println("Sorgu Oluþtur...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
			
			//5.Aþama ResulSet den dönen deðerleri aç
			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				//degerleri göster
				System.out.println("ID: " +id);
				System.out.println(", Age"+age);
				System.out.println(", First"+first);
				System.out.println(", Last"+last);
			}
			//6.Aþama ortamý temizle
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			//JDBC için hatalarý iþlemek
			se.printStackTrace();
		}catch (Exception e) {
			//Class.forName için hatalarý iþlemek
			e.printStackTrace();
		}finally {
			//Kapanmasý gereken kaynaklar için finally blogu
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
				// burda biþey yapmýyoruz
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
			System.out.println("Güle güle yine bekleriz.");
	}

}
