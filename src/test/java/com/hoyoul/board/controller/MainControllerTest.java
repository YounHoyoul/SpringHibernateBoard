package com.hoyoul.board.controller;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.MainService;
import com.hoyoul.board.service.UserService;


public class MainControllerTest {
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private AnnotationMethodHandlerAdapter adapter;
    
    private static ApplicationContext context;
	
    //@InjectMocks
    //private MainController2 controller;
    
    //@Mock
    //MainServie mainService;
    
    @InjectMocks
    private MainController2 controller;
    @Mock
    private MainService mainService;
    @Mock
    private UserService userService;
    
	@BeforeClass
	public static void init(){
		
	}
    
	@Before
	public void setUp(){
		context = new ClassPathXmlApplicationContext("servlet-context.xml");
		
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		session = (MockHttpSession) request.getSession();
		adapter = new AnnotationMethodHandlerAdapter();
		
		//Integration Test
		controller = context.getBean("mainController2",MainController2.class);
		mainService = context.getBean("mainServiceImpl",MainService.class);
		userService = context.getBean("userServiceImpl",UserService.class);
		
		User user = new User();
		user.setEmail("yhy0215@gmail.com");
		user.setPassword("1234");
		user.setName("Hoyoul Youn");
		userService.addUser(user);
		
	}
	
	@Test
	public void indexPage() throws Exception{
		request.setRequestURI("/indexpage");
		request.setMethod("GET");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("redirect:/loginpage"));
	}
	
	@Test
	public void loginPage() throws Exception{
		request.setRequestURI("/loginpage");
		request.setMethod("GET");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("main/login"));
	}
	
	@Test
	public void joinPage() throws Exception{
		request.setRequestURI("/joinpage");
		request.setMethod("GET");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("main/join"));
	}
	
	@Test
	public void mainPage() throws Exception{
		request.setRequestURI("/mainpage");
		request.setMethod("GET");
		
		session.setAttribute("loginUser", new User());
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("redirect:/board/listpage"));
	}
	
	@Test
	public void mainPageWithoutSession() throws Exception{
		request.setRequestURI("/mainpage");
		request.setMethod("GET");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("redirect:/indexpage"));
	}
	
	@Test
	public void loginSuccess() throws Exception{
		request.setRequestURI("/main/login");
		request.setMethod("POST");
		request.setParameter("email", "yhy0215@gmail.com");
		request.setParameter("password", "1234");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(session.getAttribute("loginUser"),is(User.class));
		assertThat(mav.getViewName(),is("redirect:/mainpage"));
	}
	
	@Test
	public void loginFail() throws Exception{
		request.setRequestURI("/main/login");
		request.setMethod("POST");
		request.setParameter("email", "yhy0215@gmail.com");
		request.setParameter("password", "1234XXXX");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("redirect:/loginpage"));
	}
	
	@Test
	public void logout() throws Exception{
		request.setRequestURI("/main/logout/1");
		request.setMethod("GET");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertEquals(session.getAttribute("loginUser"),null);
		assertThat(mav.getViewName(),is("redirect:/indexpage"));
	}
	
	@Test
	public void join() throws Exception{
		request.setRequestURI("/main/join");
		request.setMethod("POST");
		request.setParameter("email", "yhy1234@gmail.com");
		request.setParameter("password", "1234");
		request.setParameter("name", "Hoyoul Youn");
		
		ModelAndView mav = adapter.handle(request, response, controller);
		
		assertThat(mav.getViewName(),is("redirect:/loginpage"));
	}
}
