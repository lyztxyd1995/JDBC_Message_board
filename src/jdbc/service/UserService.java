package jdbc.service;

import jdbc.bean.User;
import jdbc.dao.UserDao;

public class UserService {
	private UserDao userDao;
	public UserService() {
		this.userDao = new UserDao();
	}
	public User login(String username, String password) {
		return userDao.login(username, password);
	}
	
	
	public User getUserById(long id) {
		return userDao.getUserById(id);
	}
	
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
	
	public boolean registerUser(User user) {
		return userDao.registerUser(user);
	}
}

