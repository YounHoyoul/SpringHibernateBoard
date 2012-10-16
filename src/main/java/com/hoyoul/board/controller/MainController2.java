package com.hoyoul.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.MainService;
import com.hoyoul.board.service.UserService;

@Controller
public class MainController2 {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/indexpage",method=RequestMethod.GET)
	public String indexPage(Model model){
		
		return "redirect:/loginpage";
	}
	
	@RequestMapping(value="/loginpage",method=RequestMethod.GET)
	public String loginPage(Model model){
		
		model.addAttribute("user", new User());
		
		return "main/login";
	}
	
	@RequestMapping(value="/joinpage",method=RequestMethod.GET)
	public String joinPage(Model model){
		
		model.addAttribute("user", new User());
		
		return "main/join";
	}
	
	@RequestMapping(value="/mainpage",method=RequestMethod.GET)
	public String mainPage(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null){
			return "redirect:/indexpage";
		}
		
		return "redirect:/board/listpage";
	}
	
	@RequestMapping(value="/main/login",method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpServletRequest request){
		
		if(!mainService.checkAuthentication(user)){
			return "redirect:/loginpage";
		}
		
		List<User> list = userService.listUserByEmail(user.getEmail());
		
		User user1 = list.get(0);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user1);
		
		return "redirect:/mainpage";
	}
	
	@RequestMapping(value="/main/logout/{userid}",method=RequestMethod.GET)
	public String logout(@PathVariable("userid") Integer userid, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", null);
		
		return "redirect:/indexpage";
	}
	
	@RequestMapping(value="/main/join",method=RequestMethod.POST)
	public String join(@ModelAttribute("user") User user){
		
		userService.addUser(user);
		
		return "redirect:/loginpage";
	}

}
