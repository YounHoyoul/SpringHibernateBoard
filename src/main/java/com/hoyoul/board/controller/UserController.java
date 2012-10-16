package com.hoyoul.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	public List<User> listUser() {
		return userService.listUser();
	}

	public int addUser(User user) {
		return userService.addUser(user);
	}

	public User getUser(int id) {
		return userService.getUser(id);
	}

	public void updateUser(User user) {
		userService.updateUser(user);
	}

	public void removeUser(int id) {
		userService.removeUser(id);
	}

	public List<User> listUserByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
