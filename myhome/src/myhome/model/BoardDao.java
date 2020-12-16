package myhome.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myhome.util.DBUtil;

public class BoardDao {
	private static BoardDao instance;
	private BoardDao() {}
	public static BoardDao getInstance() {
		if(null == instance) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	
	public boolean insert(BoardDto dto) {
		String sql = "INSERT INTO board(title, content, writer_id) VALUES(?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		
		boolean res = false;
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getWriter_id());
			
			res = ps.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<BoardDto> getAllBoards(){
		String sql = "SELECT no, title, writer_id, regdate, content FROM board";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BoardDto dto = null;
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				dto = new BoardDto();
				dto.setNo(Integer.parseInt(rs.getString(1)));
				dto.setTitle(rs.getString(2));
				dto.setWriter_id(rs.getString(3));
				dto.setRegdate(rs.getString(4));
				dto.setContent(rs.getString(5));
				list.add(dto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
