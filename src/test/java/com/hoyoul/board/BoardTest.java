package com.hoyoul.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.runners.MockitoJUnit44Runner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hoyoul.board.controller.BoardController;
import com.hoyoul.board.controller.UserController;
import com.hoyoul.board.dto.Board;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.BoardService;

public class BoardTest {
	private static ApplicationContext context;
	
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
		BoardService boardService = context.getBean("boardService",BoardService.class);
		assertNotNull(boardService);
		
		List<Board> list = boardService.listBoard();
		int INIT_CNT = list.size();
		
		Board input_board = new Board();
		input_board.setTitle("test title");
		input_board.setContent("test content....");
		input_board.setWriteDate(new Date());
		input_board.setUserId(1);
			
		int id = boardService.addBoard(input_board);
		
		List<Board> actual_list = boardService.listBoard();
		actual_list.get(0).getId();
		assertEquals(INIT_CNT+1,actual_list.size());
		
		Board output_board = boardService.getBoard(id);
		assertEquals(output_board.getTitle(),input_board.getTitle());
		assertEquals(output_board.getContent(),input_board.getContent());
		assertEquals(output_board.getUserId(),input_board.getUserId());
		
		input_board.setId(id);
		input_board.setTitle("test title 1234");
		input_board.setContent("test content 1234....");
		
		boardService.updateBoard(input_board);
		output_board = boardService.getBoard(id);
		assertEquals(output_board.getTitle(),input_board.getTitle());
		assertEquals(output_board.getContent(),input_board.getContent());
		
		boardService.removeBoard(id);
		actual_list = boardService.listBoard();
		assertEquals(INIT_CNT,actual_list.size());
		
		output_board = null;
		try{
			output_board = boardService.getBoard(id);
		}catch(ObjectNotFoundException e){}
		assertNull(output_board);
	}
	
}
