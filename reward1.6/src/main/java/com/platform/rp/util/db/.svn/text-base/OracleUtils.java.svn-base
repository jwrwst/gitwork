package com.platform.rp.util.db;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import oracle.jdbc.driver.OracleTypes;

/**
 * DBUtil，数据库访问工具类
 */
public class OracleUtils {

	private static Connection con = null;

	public static Connection openConnection() throws SQLException, ClassNotFoundException, IOException {
		if (null == con || con.isClosed()) {
			Properties p = new Properties();
			p.load(OracleUtils.class.getResourceAsStream("/oracle-jdbc.properties"));
			Class.forName(p.getProperty("driver"));
			con = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"),
					p.getProperty("password"));
		}
		return con;
	}

	public static void closeConnection() throws SQLException {
		try {
			if (null != con)
				con.close();
		} finally {
			con = null;
			System.gc();
		}
	}

	public static List<Map<String, Object>> queryMapList(String sql) throws Exception {
		if (con == null)
			openConnection();

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			Map<String, Object> map = null;
			while (null != rs && rs.next()) {
				map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String name = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(name);
					map.put(name, value);
				}
				lists.add(map);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != pstmt)
				pstmt.close();
		}
		return lists;
	}

	public static List<Map<String, Object>> queryMapList(String sql, Object... params) throws Exception {
		if (con == null)
			openConnection();

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				pstmt.setObject(i + 1, params[i]);// 下标从1开始
			
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			Map<String, Object> map = null;
			while (null != rs && rs.next()) {
				map = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					String name = rsmd.getColumnName(i + 1);
					Object value = rs.getObject(name);
					map.put(name, value);
				}
				lists.add(map);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != pstmt)
				pstmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryList(String sql, Class<T> beanClass) throws Exception {
		if (con == null)
			openConnection();

		List<T> lists = new ArrayList<T>();
		Statement stmt = null;
		ResultSet rs = null;
		Field[] fields = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			fields = beanClass.getDeclaredFields();
			
			T t = null;
			while (null != rs && rs.next()) {
				t = beanClass.newInstance();
				for (Field f : fields) {
					f.setAccessible(true);
					
					String name = f.getName();
					try {
						Object value = rs.getObject(name);
						setValue(t, f, value);
					} catch (Exception e) {
					}
				}
				lists.add(t);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
		}
		return lists;
	}

	public static <T> List<T> queryList(String sql, Class<T> beanClass, Object... params) throws Exception {
		if (con == null)
			openConnection();

		List<T> lists = new ArrayList<T>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Field[] fields = null;
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				pstmt.setObject(i + 1, params[i]);// 下标从1开始
			rs = pstmt.executeQuery();
			fields = beanClass.getDeclaredFields();
			
			T t = null;
			while (null != rs && rs.next()) {
				t = beanClass.newInstance();
				for (Field f : fields) {
					f.setAccessible(true);
					String name = f.getName();
					try {
						Object value = rs.getObject(name);
						setValue(t, f, value);
					} catch (Exception e) {
					}
				}
				lists.add(t);
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != pstmt)
				pstmt.close();
		}
		return lists;
	}

	public static <T> T queryBean(String sql, Class<T> beanClass) throws Exception {
		List<T> lists = queryList(sql, beanClass);
		if (lists.size() != 1)
			throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");

		return lists.get(0);
	}

	public static <T> T queryBean(Connection con, String sql, Class<T> beanClass, Object... params) throws Exception {
		List<T> lists = queryList(sql, beanClass, params);
		if (lists.size() != 1)
			throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");

		return lists.get(0);
	}

	public static int update(String sql) throws Exception {
		if (con == null)
			openConnection();

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			
			return stmt.executeUpdate(sql);
		} finally {
			if (null != stmt)
				stmt.close();
		}
	}

	public static int update(String sql, Object... params) throws Exception {
		if (con == null)
			openConnection();

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++)
				pstmt.setObject(i + 1, params[i]);// 下标从1开始
			
			return pstmt.executeUpdate();
		} finally {
			if (null != pstmt)
				pstmt.close();
		}
	}

	public static int[] executeBatch(List<String> sqlList) throws Exception {
		return executeBatch(sqlList.toArray(new String[] {}));
	}

	public static int[] executeBatch(String[] sqlArray) throws Exception {
		if (con == null)
			openConnection();

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			for (String sql : sqlArray) {
				stmt.addBatch(sql);
			}
			return stmt.executeBatch();
		} finally {
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	private static <T> void setValue(T t, Field f, Object value) throws IllegalAccessException {
		// 以数据库类型为准绳，还是以java数据类型为准绳？还是混合两种方式？
		if (null == value)
			return;
		
		String v = value.toString();
		String n = f.getType().getName();
		if ("java.lang.Byte".equals(n) || "byte".equals(n)) {
			f.set(t, Byte.parseByte(v));
		} else if ("java.lang.Short".equals(n) || "short".equals(n)) {
			f.set(t, Short.parseShort(v));
		} else if ("java.lang.Integer".equals(n) || "int".equals(n)) {
			f.set(t, Integer.parseInt(v));
		} else if ("java.lang.Long".equals(n) || "long".equals(n)) {
			f.set(t, Long.parseLong(v));
		} else if ("java.lang.Float".equals(n) || "float".equals(n)) {
			f.set(t, Float.parseFloat(v));
		} else if ("java.lang.Double".equals(n) || "double".equals(n)) {
			f.set(t, Double.parseDouble(v));
		} else if ("java.lang.String".equals(n)) {
			f.set(t, value.toString());
		} else if ("java.lang.Character".equals(n) || "char".equals(n)) {
			f.set(t, (Character) value);
		} else if ("java.lang.Date".equals(n)) {
			f.set(t, new Date(((java.sql.Date) value).getTime()));
		} else if ("java.lang.Timer".equals(n)) {
			f.set(t, new Time(((java.sql.Time) value).getTime()));
		} else if ("java.sql.Timestamp".equals(n)) {
			f.set(t, (java.sql.Timestamp) value);
		} else {
			System.out.println("SqlError：暂时不支持此数据类型，请使用其他类型代替此类型！");
		}
	}

	public static void executeProcedure(String procedureName, Object... params) throws Exception {
		if (con == null)
			openConnection();

		CallableStatement proc = null;
		try {
			proc = con.prepareCall(procedureName);
			for (int i = 0; i < params.length; i++) {
				proc.setObject(i + 1, params[i]);
			}
			proc.execute();
		} finally {
			if (null != proc)
				proc.close();
		}
	}

	public static boolean executeProcedureReturnErrorMsg(String procedureName, StringBuffer errorMsg, Object... params)
			throws Exception {
		if (con == null)
			openConnection();

		CallableStatement proc = null;
		try {
			proc = con.prepareCall(procedureName);
			proc.registerOutParameter(1, OracleTypes.VARCHAR);
			for (int i = 0; i < params.length; i++) {
				proc.setObject(i + 2, params[i]);
			}
			boolean b = proc.execute();
			errorMsg.append(proc.getString(1));
			return b;
		} finally {
			if (null != proc)
				proc.close();
		}
	}

	public static <T> List<T> executeProcedureReturnCursor(String procedureName, Class<T> beanClass, Object... params)
			throws Exception {
		if (con == null)
			openConnection();

		List<T> lists = new ArrayList<T>();
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			proc = con.prepareCall(procedureName);
			proc.registerOutParameter(1, OracleTypes.CURSOR);
			for (int i = 0; i < params.length; i++) {
				proc.setObject(i + 2, params[i]);
			}
			boolean b = proc.execute();
			if (b) {
				rs = (ResultSet) proc.getObject(1);
				while (null != rs && rs.next()) {
					T t = beanClass.newInstance();
					Field[] fields = beanClass.getDeclaredFields();
					for (Field f : fields) {
						f.setAccessible(true);
						String name = f.getName();
						try {
							Object value = rs.getObject(name);
							setValue(t, f, value);
						} catch (Exception e) {
						}
					}
					lists.add(t);
				}
			}
		} finally {
			if (null != rs)
				rs.close();
			if (null != proc)
				proc.close();
		}
		return lists;
	}

	public static <T> List<List<T>> listLimit(List<T> lists, int pageSize) {
		List<List<T>> llists = new ArrayList<List<T>>();
		for (int i = 0; i < lists.size(); i = i + pageSize) {
			try {
				List<T> list = lists.subList(i, i + pageSize);
				llists.add(list);
			} catch (IndexOutOfBoundsException e) {
				List<T> list = lists.subList(i, i + (lists.size() % pageSize));
				llists.add(list);
			}
		}
		return llists;
	}

}