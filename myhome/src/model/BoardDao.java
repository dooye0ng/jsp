package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {
	}
	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();
		return instance;
	}
	
	public boolean insert(BoardDto dto) {
		String query = "INSERT INTO board(no, title, content, writer_id, regdate) VALUES(NULL, ?, ?, ?, DEFAULT)";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean result = false;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getWriter_id());

			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtil.close(conn, ps);
		return result;
	}
	
	
	public List<BoardDtoAndName> selectAll(){
		String query = "SELECT no, title, writer_id, hit, board.regdate, name " + 
						"FROM board " + 
						"LEFT JOIN user ON board.writer_id = user.id " + 
						"ORDER BY board.regdate DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<BoardDtoAndName> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDtoAndName dto = new BoardDtoAndName();
				dto.setNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setWriter_id(rs.getString(3));
				dto.setHit_count(rs.getInt(4));
				dto.setRegdate(rs.getString(5));
				dto.setWriter_name(rs.getString(6));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtil.close(conn, ps);
		return list.isEmpty() ? null : list;
	}
	
	public BoardDtoAndName select(int no){
		String query = "SELECT no, title, writer_id, hit, board.regdate, name, content " + 
						"FROM board " + 
						"LEFT JOIN user ON board.writer_id = user.id " + 
						"WHERE no = ? " +
						"ORDER BY board.regdate DESC";
		System.out.println(query);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		BoardDtoAndName dto = null;
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDtoAndName();
				dto.setNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setWriter_id(rs.getString(3));
				dto.setHit_count(rs.getInt(4));
				dto.setRegdate(rs.getString(5));
				dto.setWriter_name(rs.getString(6));
				dto.setContent(rs.getString(7));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtil.close(conn, ps);
		return dto;
	}
	
	public void updateHit(int no) {
		String sql = "UPDATE board SET hit = hit + 1 WHERE no = ?"; 
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps);
	}
	
	public boolean delete(int no) {
		String sql = "DELETE FROM board WHERE no = ?"; 
		Connection conn = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps);
		return result;
	}
	
	public boolean update(BoardDto dto) {
		String sql = "UPDATE board SET title = ?, content = ? WHERE no = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setInt(3, dto.getNo());
			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DBUtil.close(conn, ps);
		return result;
	}
	
	public List<BoardDtoAndName> selectAllPerPage(int page){
		// 1 page : 0 ~ 10개 (0~9)
		// 2 page : 10 ~ 10개 (10~19)
		
		int start = (page-1) * 10;
		String query = "SELECT brd.*, wr.name " + 
				"FROM (SELECT b.no, b.title, b.writer_id, b.regdate, b.hit_count " + 
				"   FROM board AS b " + 
				"   JOIN (SELECT no FROM board ORDER BY no DESC LIMIT ?, 10) AS tmp " + 
				"   ON tmp.no = b.no) AS brd " + 
				"JOIN (SELECT id, name FROM user) AS wr " + 
				"ON brd.writer_id = wr.id"; 
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<BoardDtoAndName> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, start);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDtoAndName dto = new BoardDtoAndName();
				dto.setNo(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setWriter_id(rs.getString(3));
				dto.setHit_count(rs.getInt(5));
				dto.setRegdate(rs.getString(4));
				dto.setWriter_name(rs.getString(6));
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtil.close(conn, ps);
		return list.isEmpty() ? null : list;
	}
	
	public int getCount() {
		int count = 0;
		
		String sql = "SELECT COUNT(*) FROM board";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			
			rs.next();
			count = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DBUtil.close(conn, ps, rs);
		return count;
	}
}




