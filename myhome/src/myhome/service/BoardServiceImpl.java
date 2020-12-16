package myhome.service;

import java.util.List;

import myhome.model.BoardDao;
import myhome.model.BoardDto;
import myhome.model.UserDto;

public class BoardServiceImpl implements BoardService {
	private static BoardService instance;
	private BoardServiceImpl() {}
	public static BoardService getInstance() {
		if(instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}
	private BoardDao dao;
	
	public boolean write(UserDto dto, String title, String content) {
		if(dto == null) {
			return false;
		}
		boolean res = false;
		dao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto();
		
		boardDto.setTitle(title);
		boardDto.setContent(content);
		boardDto.setWriter_id(dto.getId());
		
		return dao.insert(boardDto);
	}
	
	public List<BoardDto> getList(){
		List<BoardDto> boardList;
		dao = BoardDao.getInstance();
		boardList = dao.getAllBoards();
		
		return boardList;
	}
}
