package myhome.service;

import java.util.List;

import myhome.model.UserDao;
import myhome.model.UserDto;

public class UserServiceImpl implements UserService {
	
	private static UserService instance;
	private UserServiceImpl() {};
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}
	
	public UserDto login(String id, String pw) {
		UserDto dto = new UserDto();
		UserDao dao = UserDao.getInstance();
		
		dto.setId(id);
		dto.setPassword(pw);
		
		return dao.verify(dto) ? dto : null;
	}
	
	public boolean join(UserDto dto) {
		UserDao dao = UserDao.getInstance();
		boolean success;
		
		dao = UserDao.getInstance();
		success = dao.insert(dto);
		
		return success;
	}
	
	public UserDto modify1(UserDto dto) {
		UserDao dao = UserDao.getInstance();
		if(null != dto) {
			dto = dao.getUserById(dto.getId());
		}
		return dto;
	}
	
	public boolean modify2(UserDto dto) {
		UserDao dao = UserDao.getInstance();
		
		return dao.updateUser(dto) ? true : false;
	}	
	
	public List<UserDto> getAllUsers(){
		UserDao dao = UserDao.getInstance();
		
		return dao.getAllUsers();
	}
	
	public boolean deleteByAdmin(String id) {
		UserDao dao = UserDao.getInstance();
		return dao.deleteUserByAdmin(id);
	}
}
