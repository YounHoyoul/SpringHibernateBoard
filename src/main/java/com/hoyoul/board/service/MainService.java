package com.hoyoul.board.service;

import org.springframework.stereotype.Service;

import com.hoyoul.board.dto.User;

public interface MainService {

	public boolean checkAuthentication(User user);
	
}
