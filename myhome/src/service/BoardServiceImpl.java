package service;

import java.util.List;


import model.BoardDao;
import model.BoardDto;
import model.BoardDtoAndName;

public class BoardServiceImpl implements BoardService {

	private static BoardService instance;

	private BoardServiceImpl() {
		dao = BoardDao.getInstance();
	}

	public static BoardService getInstance() {
		if (instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	private BoardDao dao;

	@Override
	public boolean write(String title, String content, String writer_id) {
		content = content.replace("\n", "<br/>");
		BoardDto dto = new BoardDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter_id(writer_id);
		return dao.insert(dto);
	}
	
	@Override
	public List<BoardDtoAndName> getList() {
		List<BoardDtoAndName> list = dao.selectAll();
		return list;
	}

	@Override
	public BoardDtoAndName fetchContent(int no, boolean hasRead) {
		if(!hasRead)
			dao.updatehit_count(no); // 조회수 증가
		BoardDtoAndName vo = dao.select(no);
		return vo;
	}
	@Override
	public boolean delete(int no) {
		return dao.deleteBoardByNo(no);
	}
}







