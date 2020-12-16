package myhome.service;

import java.util.List;

import myhome.model.UserDto;

public interface AdminService {
	public List<UserDto> getAllUsers();
	public boolean deleteByAdmin(String id);
}
