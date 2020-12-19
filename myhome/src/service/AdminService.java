package service;

import java.util.List;

import model.UserDto;

public interface AdminService {
	public List<UserDto> listUser();
	public boolean delete(String id);
}
