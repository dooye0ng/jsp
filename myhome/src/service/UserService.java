package service;

import model.UserDto;

public interface UserService {
	public UserDto login(String id, String password);
	public boolean signout(String id, String password);
	public boolean signup(String id, String password, String email, String name);
	public UserDto getUser(String id);
	public boolean modifyUser(String id, String password, String email, String name);
}
