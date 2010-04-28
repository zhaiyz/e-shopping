/**
 * ���ݿ����ӹ�����
 * 
 * @author hanxue_lang@foxmail.com
 */
package com.shopping.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	/* ���ݿ����� */
	private final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	/* ���ݿ�URL */
	private final String DBURL = "jdbc:mysql://localhost/test";
	/* ��¼�� */
	private final String DBUSER = "root";
	/* ���� */
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
	 * ����һ�����ݿ�����
	 * 
	 * @param void
	 * @return conn Connection ���ݿ�����
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * �ر����ݿ�����
	 * 
	 * @param void
	 * @return void
	 */
	public void close() {
		// ��Ϊ��ʱ�ر�
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
