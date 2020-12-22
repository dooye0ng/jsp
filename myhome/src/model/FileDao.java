package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUtil;

public class FileDao {
	private static FileDao instance;
	
	private FileDao() {
		
	}
	
	public static FileDao getInstance() {
		if(instance == null)
			instance = new FileDao();
		
		return instance;
	}
	
	public FileDto select(int no) {
		String sql = "SELECT f.filename, f.filepath FROM file f WHERE no = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		FileDto dto = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new FileDto();
				dto.setFileName(rs.getString(1));
				dto.setFilePath(rs.getString(2));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		DBUtil.close(conn, ps, rs);
		return dto;
	}
	
	public ArrayList<FileDto> selectAll(){
		String sql = "SELECT f.no, f.filename, f.filepath, f.uploader_id, f.regdate "
				+ "FROM file f ORDER BY f.regdate DESC";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<FileDto> list = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FileDto dto = new FileDto();
				dto.setNo(rs.getInt(1));
				dto.setFileName(rs.getString(2));
				dto.setFilePath(rs.getString(3));
				dto.setUploader_id(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		DBUtil.close(conn, ps, rs);
		return list;
	}
	
	public boolean insert(FileDto dto) {
		String sql = "INSERT INTO file (no, filename, filepath, uploader_id, regdate)"
				+ "VALUES(null, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean res = false;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getFileName());
			ps.setString(2, dto.getFilePath());
			ps.setString(3, dto.getUploader_id());
			ps.setString(4, dto.getRegdate());
			res = ps.executeUpdate() > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		DBUtil.close(conn, ps);
		return res;
	}
}
