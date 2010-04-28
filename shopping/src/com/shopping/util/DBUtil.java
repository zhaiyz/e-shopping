/**
 * 数据库连接工具类
 * 
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	/* 数据库驱动 */
	private final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	/* 数据库URL */
	private final String DBURL = "jdbc:mysql://localhost/test";
	/* 登录名 */
	private final String DBUSER = "root";
	/* 密码 */
	private final String DBPASSWORD = "root";

	private Connection conn = null;

	public DBUtil() {
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 返回一个数据库连接
	 * 
	 * @param void
	 * @return conn Connection 数据库连接
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param void
	 * @return void
	 */
	public void close() {
		// 不为空时关闭
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
