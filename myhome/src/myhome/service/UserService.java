package myhome.service;

import java.util.List;

import myhome.model.UserDto;

public interface UserService {
	public UserDto login(String id, String pw);
	public boolean join(UserDto dto);
	public UserDto modify1(UserDto dto);
	public boolean modify2(UserDto dto);
	public List<UserDto> getAllUsers();
	public boolean deleteByAdmin(String id);
}
