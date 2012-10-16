package com.hoyoul.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hoyoul.board.dao.UserDAO;
import com.hoyoul.board.dto.User;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserDAO userDAO;
	
	public boolean checkAuthentication(User user){
		
		List<User> listUser = userDAO.listUserByEmail(user.getEmail());
		
		if(listUser.size() == 1){
			User returnUser = listUser.get(0);
			return returnUser.getPassword().equals(user.getPassword());
		}
		
		return false;
	}
	
}
