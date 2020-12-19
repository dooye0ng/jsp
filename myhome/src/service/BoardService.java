package service;

import java.util.List;

import model.BoardDtoAndName;

public interface BoardService {
	public boolean write(String title, String content, String writer_id);
	public List<BoardDtoAndName> getList();
	public BoardDtoAndName fetchContent(int no, boolean hasRead);
	public boolean delete(int no);
}
