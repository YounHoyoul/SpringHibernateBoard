package com.hoyoul.board.service;

import com.hoyoul.board.dto.User;

public interface LoginService {
	public boolean checkAuthentication(User user);
}
