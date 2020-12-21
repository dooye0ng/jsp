package service;

import java.util.List;

import model.BoardDtoAndName;

public interface BoardService {
	public boolean write(String title, String content, String writer_id);
	public List<BoardDtoAndName> getList(int page);
	public BoardDtoAndName fetchContent(int no, boolean hasRead);
	public boolean deleteContent(int no);
	public boolean modifyContent(int no, String title, String content);
	public int getLastPage();
}