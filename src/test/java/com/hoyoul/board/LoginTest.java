package com.hoyoul.board;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hoyoul.board.controller.UserController;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.LoginService;
import com.hoyoul.board.service.UserService;

public class LoginTest {
	
	private static ApplicationContext context = null;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("servlet-context.xml");
	}
	
	@Test
	public void testSessionFactory(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		assertNotNull(sessionFactory);
	}
	
	@Test
	public void testLogin(){
		UserService userService = context.getBean("userServiceImpl",UserService.class);
		assertNotNull(userService);
		
		User input_user = new User();
		input_user.setEmail("test@test.com");
		input_user.setPassword("testpassword");
		input_user.setName("testname");
		input_user.setJoinedDate(new Date());
		int id1 = userService.addUser(input_user);
		
		input_user = new User();
		input_user.setEmail("test1@test.com");
		input_user.setPassword("test1password");
		input_user.setName("test1name");
		input_user.setJoinedDate(new Date());
		int id2 = userService.addUser(input_user);
		
		input_user = new User();
		input_user.setEmail("test2@test.com");
		input_user.setPassword("test2password");
		input_user.setName("test2name");
		input_user.setJoinedDate(new Date());
		int id3 = userService.addUser(input_user);
		
		List<User> list = userService.listUser();
		assertEquals(3,list.size());
		//---------------------------------------------------------------------
		
		LoginService loginService = context.getBean("loginServiceImpl",LoginService.class);
		assertTrue(loginService.checkAuthentication(input_user));
		
		input_user.setPassword("1234");
		assertFalse(loginService.checkAuthentication(input_user));
		
		//---------------------------------------------------------------------
		userService.removeUser(id3);
		userService.removeUser(id2);
		userService.removeUser(id1);
	}
	
}
