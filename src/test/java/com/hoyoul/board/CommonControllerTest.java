package com.hoyoul.board;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.hoyoul.board.controller.BoardController;
import com.hoyoul.board.controller.LoginController;
import com.hoyoul.board.dto.Board;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("servlet-context.xml")
public class CommonControllerTest {
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockHttpSession session;
    private BoardController controller;
    private AnnotationMethodHandlerAdapter adapter;

    @Autowired
    private ApplicationContext applicationContext;
    
    @Mock
    BoardService boardService;
    
    private static ApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context = new ClassPathXmlApplicationContext("servlet-context.xml");
	}
    
    @Before
    public void setUp() {
        request    = new MockHttpServletRequest();
        response   = new MockHttpServletResponse();
        //response.setOutputStreamAccessAllowed(true);
        adapter = new AnnotationMethodHandlerAdapter();
        
        controller = context.getBean("boardController",BoardController.class);
        
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listBoard() throws Exception {
        request.setRequestURI("/boardmain");
        request.setMethod("GET");
        
        session = (MockHttpSession) request.getSession();
        session.setAttribute("loginUser", new User());
        
        ModelAndView mav = adapter.handle(request, response, controller);
        
        assertThat(mav.getModelMap().get("boardList"),is(List.class));
        assertThat(mav.getViewName(),is("board/main"));        
    }
    
    @Test
    public void listBoardWithoutSession() throws Exception {
        request.setRequestURI("/boardmain");
        request.setMethod("GET");
        
        //session = (MockHttpSession) request.getSession();
        //session.setAttribute("loginUser", new User());
        
        ModelAndView mav = adapter.handle(request, response, controller);
        
        //assertThat(mav.getModelMap().get("boardList"),is(List.class));
        assertThat(mav.getViewName(),is("redirect:/"));        
    }

}
