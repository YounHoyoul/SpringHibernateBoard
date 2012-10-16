package com.hoyoul.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyoul.board.dao.UserDAO;
import com.hoyoul.board.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public List<User> listUser() {
		return userDAO.listUser();
	}

	public int addUser(User user) {
		return userDAO.addUser(user);
	}

	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	public void removeUser(int id) {
		userDAO.removeUser(id);
	}

	public List<User> listUserByName(String name) {
		return userDAO.listUserByName(name);
	}
	
	public List<User> listUserByEmail(String email){
		return userDAO.listUserByEmail(email);
	}

	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
