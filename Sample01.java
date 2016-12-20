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
			//2.A�ama JDBC driver kay�t
			Class.forName(JDBC_Driver);
			//3.A�ama Veritaban�na Baglan
			System.out.println("Veritaban�na Baglan...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//4.a�ama Sorgu �al��t�r
			System.out.println("Sorgu Olu�tur...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
			
			//5.A�ama ResulSet den d�nen de�erleri a�
			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				//degerleri g�ster
				System.out.println("ID: " +id);
				System.out.println(", Age"+age);
				System.out.println(", First"+first);
				System.out.println(", Last"+last);
			}
			//6.A�ama ortam� temizle
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			//JDBC i�in hatalar� i�lemek
			se.printStackTrace();
		}catch (Exception e) {
			//Class.forName i�in hatalar� i�lemek
			e.printStackTrace();
		}finally {
			//Kapanmas� gereken kaynaklar i�in finally blogu
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
				// burda bi�ey yapm�yoruz
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}
			System.out.println("G�le g�le yine bekleriz.");
	}

}
