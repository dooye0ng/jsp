package service;

import model.UserDao;
import model.UserDto;

public class UserServiceImpl implements UserService {
	private static UserService instance;
	private UserServiceImpl() {
		dao = UserDao.getInstance();
	}
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}
	
	private UserDao dao;
	
	public UserDto login(String id, String password) {
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPassword(password);
		UserDao dao = UserDao.getInstance();
		return dao.verify(dto) ? dto : null;
	}
	
	public boolean signout(String id, String password) {
		// 조회(dao.verify())한 후 
		//  true : dao.delete() 후 return
		//  false : return 
		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPassword(password);
		if(!dao.verify(dto)) {
			return false;
		}
		return dao.delete(dto);
	}
	public boolean signup(String id, String password, String email, String name) {
		
		UserDao dao = null;
		UserDto dto = new UserDto();

		dto.setId(id);

		dto.setPassword(password);

		dto.setEmail(email);

		dto.setName(name);

		dao = UserDao.getInstance();
		
		return dao.insert(dto);
	}
	public UserDto getUser(String id) {
		return dao.select(id);
	}
	@Override
	public boolean modifyUser(String id, String password, String email, String name) {
		UserDto dto = new UserDto();
		dto.setPassword(password);
		dto.setEmail(email);
		dto.setPassword(password);
		return dao.update(dto);
	}
}





