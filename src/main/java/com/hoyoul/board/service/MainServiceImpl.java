package com.hoyoul.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyoul.board.dao.UserDAO;
import com.hoyoul.board.dto.User;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private UserDAO userDAO;
	
	public boolean checkAuthentication(User user) {
		List<User> list = userDAO.listUserByEmail(user.getEmail());
		if(list.size() == 0 ) return false;
		
		User user1 = list.get(0);
		
		return user1.getPassword().equals(user.getPassword());
	}

}
