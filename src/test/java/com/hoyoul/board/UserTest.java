package com.hoyoul.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hoyoul.board.controller.UserController;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.UserService;

public class UserTest {
	
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
	public void testUserCRUDL(){
		UserService userService = context.getBean("userService",UserService.class);
		assertNotNull(userService);
		
		List<User> list = userService.listUser();
		int INIT_CNT = list.size();
		
		User input_user = new User();
		input_user.setEmail("test@test.com");
		input_user.setPassword("test password");
		input_user.setName("test name");
		input_user.setJoinedDate(new Date());
		
		int id = userService.addUser(input_user);
		
		System.out.println("id="+id);
		
		List<User> actual_list = userService.listUser();
		actual_list.get(0).getId();
		assertEquals(INIT_CNT+1,actual_list.size());
		
		User output_user = userService.getUser(id);
		assertEquals(output_user.getEmail(),input_user.getEmail());
		assertEquals(output_user.getPassword(),input_user.getPassword());
		assertEquals(output_user.getName(),input_user.getName());
		//assertEquals(output_user.getJoinedDate(),input_user.getJoinedDate());
		
		input_user.setId(id);
		input_user.setEmail("test1234@test.com");
		input_user.setPassword("test1234 password");
		input_user.setName("test1234 name");
		input_user.setJoinedDate(new Date());
		
		userService.updateUser(input_user);
		output_user = userService.getUser(id);
		assertEquals(output_user.getEmail(),input_user.getEmail());
		assertEquals(output_user.getPassword(),input_user.getPassword());
		assertEquals(output_user.getName(),input_user.getName());
		//assertEquals(output_user.getJoinedDate(),input_user.getJoinedDate());
		
		userService.removeUser(id);
		actual_list = userService.listUser();
		assertEquals(INIT_CNT,actual_list.size());
		
		output_user = null;
		try{
			output_user = userService.getUser(id);
		}catch(ObjectNotFoundException e){}
		assertNull(output_user);
	}
	
	@Test
	public void testFilterUser(){
		UserService userService = context.getBean("userServiceImpl",UserService.class);
		assertNotNull(userService);
		
		User input_user = new User();
		input_user.setEmail("test@test.com");
		input_user.setPassword("testpassword");
		input_user.setName("testname");
		input_user.setJoinedDate(new Date());
		userService.addUser(input_user);
		
		input_user = new User();
		input_user.setEmail("test1@test.com");
		input_user.setPassword("test1password");
		input_user.setName("test1name");
		input_user.setJoinedDate(new Date());
		userService.addUser(input_user);
		
		input_user = new User();
		input_user.setEmail("test2@test.com");
		input_user.setPassword("test2password");
		input_user.setName("test2name");
		input_user.setJoinedDate(new Date());
		userService.addUser(input_user);
		
		List<User> list = userService.listUser();
		assertEquals(3,list.size());
		
		List<User> listEmail = userService.listUserByEmail("test@test.com");
		assertEquals(1,listEmail.size());
		assertEquals("testname",listEmail.get(0).getName());
		
		List<User> listName = userService.listUserByName("test2name");
		assertEquals(1,listName.size());
		assertEquals("test2@test.com",listName.get(0).getEmail());
		
	}
}
