package com.hoyoul.board.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoyoul.board.HomeController;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.LoginService;
import com.hoyoul.board.service.UserService;

//@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		model.addAttribute("user", new User() );
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user")
		User user, BindingResult result ,HttpServletRequest request){
		
		//logger.info("Login User's Email : " + user.getEmail());
		//logger.info("Login User's Password : " + user.getPassword());
		
		if(loginService.checkAuthentication(user)){
			
			User user2 = userService.listUserByEmail(user.getEmail()).get(0);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user2);
			
			return "redirect:/main";
		}
		
		user.setEmail("");
		user.setPassword("");
		user.setName("");
		
		return "index";
	}
	
	@RequestMapping(value = "/joinpage")
	public String reqJoinPage(Model model){
		model.addAttribute("user", new User() );
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("user")
		User user, BindingResult result){
		
		//logger.info("Login User's Email : " + user.getEmail());
		//logger.info("Login User's Password : " + user.getPassword());
		
		user.setJoinedDate(new Date());
		userService.addUser(user);
		
		return "index";
	}
}
