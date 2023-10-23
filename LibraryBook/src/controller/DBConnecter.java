package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnecter {
	public static Connection getConnection() {
		Properties properties = new Properties();
		Connection connection = null;
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream("src/config/db.properties");
			properties.load(fileInputStream);

			String driver = properties.getProperty("driver"); // 드라이버 클래스 경로
			String url = properties.getProperty("url"); // 아이피 포트번호
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");

			Class.forName(driver); // JDBC 드라이버 로드
			connection = DriverManager.getConnection(url, username, password); // DB 연결

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FileNotFoundException 오류");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException 오류");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ClassNotFoundException 오류");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException 오류");
		}
		System.out.println("DB 연결성공");
		return connection;
	}

	public static void main(String[] args) {
		getConnection();
	}
}
