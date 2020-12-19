package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.DBUtil;

public class UserDao {

	// 싱글톤 패턴
	private static UserDao instance;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null)
			instance = new UserDao();
		return instance;
	}

	public boolean insert(UserDto dto) {
		String query = "INSERT INTO user(id, email, name, password, regdate)" + " VALUES(?, ?, ?, ?, DEFAULT)";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean result = false;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getPassword());

			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtil.close(conn, ps);
		return result;
	}

	public boolean update(UserDto dto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "UPDATE user SET password = ?, email = ?, name = ? " + "WHERE id = ?";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);

			ps.setString(1, dto.getPassword());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getId());
			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps);

		return result;
	}

	public UserDto select(String id) {
		String query = "SELECT id, email, password, name, regdate FROM user " + "WHERE id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserDto dto = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new UserDto();
				dto.setId(rs.getString(1));
				dto.setEmail(rs.getString(2));
				dto.setPassword(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setRegdate(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps, rs);
		return dto;
	}

	public boolean delete(UserDto dto) {
		String query = "DELETE FROM user WHERE id = ? AND password = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPassword());
			result = ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps);
		return result;
	}

	public boolean verify(UserDto dto) {
		// 인자로 들어온 Dto 에는 id, password 가 담겨 있다.
		// SELECT 쿼리 실행하여 true 면 name만 인자 dto에 추가로 담는다.
		// dto에 name을 담으면 true (<== 로그인 성공)
		// 못담으면 false (<== 로그인 실패)
		String query = "SELECT id, email, password, name, regdate FROM user " + "WHERE id = ? AND password = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(4));
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps, rs);
		return result;
	}

	public ArrayList<UserDto> selectAll() {
		String query = "SELECT id, email, name, password, regdate FROM user ORDER BY regdate";
		ArrayList<UserDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDto dto = new UserDto();
				// id, email, name, password
				dto.setId(rs.getString(1));
				dto.setEmail(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setPassword(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps, rs);

		return list.isEmpty() ? null : list;
	}

	public boolean deleteByAdmin(String id) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("DELETE FROM user WHERE id = ?");
			ps.setString(1, id);
			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps);
		return result;
	}
}
