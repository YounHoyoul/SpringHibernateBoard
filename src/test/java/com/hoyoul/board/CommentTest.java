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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hoyoul.board.controller.BoardController;
import com.hoyoul.board.controller.CommentController;
import com.hoyoul.board.dto.Board;
import com.hoyoul.board.dto.Comment;
import com.hoyoul.board.service.BoardService;
import com.hoyoul.board.service.CommentService;

public class CommentTest {
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
	public void testCommentCRUDL(){
		//make parent board
		BoardService boardService = context.getBean("boardService",BoardService.class);
		Board inputBoard = new Board();
		inputBoard.setTitle("test title");
		inputBoard.setContent("test content....");
		inputBoard.setWriteDate(new Date());
		inputBoard.setUserId(1);
		
		int boardId = boardService.addBoard(inputBoard);
		//---------------------------------------------------------------------
		
		CommentService commentService = context.getBean("commentService",CommentService.class);
		assertNotNull(commentService);
		
		List<Comment> list = commentService.listComment(boardId);
		int INIT_CNT = list.size();
		
		Comment inputComment = new Comment();
		inputComment.setComment("test comment");
		inputComment.setPassword("test password");
		inputComment.setBoard(inputBoard);
		
		int commentId = commentService.addComment(inputComment);
		
		List<Comment> actualList = commentService.listComment(boardId);
		actualList.get(0).getId();
		assertEquals(INIT_CNT+1,actualList.size());
		
		Comment outputComment = commentService.getComment(commentId);
		assertEquals(outputComment.getComment(),inputComment.getComment());
		assertEquals(outputComment.getPassword(),inputComment.getPassword());
		
		inputComment.setId(commentId);
		inputComment.setComment("test comment 1234");
		inputComment.setPassword("test password 1234....");
		
		commentService.updateComment(inputComment);
		outputComment = commentService.getComment(commentId);
		assertEquals(outputComment.getComment(),inputComment.getComment());
		assertEquals(outputComment.getPassword(),inputComment.getPassword());
		
		commentService.removeComment(commentId);
		actualList = commentService.listComment(boardId);
		assertEquals(INIT_CNT,actualList.size());
		
		outputComment = null;
		try{
			outputComment = commentService.getComment(commentId);
		}catch(ObjectNotFoundException e){}
		assertNull(outputComment);
		
		//remove parent board -------------------------------------------------
		boardService.removeBoard(boardId);
	}
}
