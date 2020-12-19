package service;

import java.util.List;

import model.UserDao;
import model.UserDto;

public class AdminServiceImpl implements AdminService{
	private static AdminServiceImpl instance;
	private AdminServiceImpl() {
		dao = UserDao.getInstance();
	}
	public static AdminServiceImpl getInstance() {
		if(instance == null) {
			instance = new AdminServiceImpl();
		}
		return instance;
	}
	
	private UserDao dao;
	
	@Override
	public List<UserDto> listUser() {
		return dao.selectAll();
	}
	
	@Override
	public boolean delete(String id) {
		return dao.deleteByAdmin(id);
	}
}
