package com.hoyoul.board.service;

import java.util.List;

import com.hoyoul.board.dto.User;

public interface UserService {
	public List<User> listUser();
	public int addUser(User user);
	public User getUser(int id);
	public void updateUser(User user);
	public void removeUser(int id);
	public List<User> listUserByName(String name);
	public List<User> listUserByEmail(String email);
}
