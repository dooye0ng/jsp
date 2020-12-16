package myhome.service;

import java.util.List;

import myhome.model.UserDao;
import myhome.model.UserDto;

public class AdminServiceImpl implements AdminService {
	
	private static AdminService instance;
	private AdminServiceImpl() {};
	public static AdminService getInstance() {
		if(instance == null) {
			instance = new AdminServiceImpl();
		}
		return instance;
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
