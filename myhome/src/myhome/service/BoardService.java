package myhome.service;

import java.util.List;

import myhome.model.BoardDto;
import myhome.model.UserDto;

public interface BoardService {
	public boolean write(UserDto dto, String title, String content);
	public List<BoardDto> getList();
}
