package com.hoyoul.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoyoul.board.HomeController;
import com.hoyoul.board.dto.Board;
import com.hoyoul.board.dto.Comment;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.BoardService;
import com.hoyoul.board.service.CommentService;

@Controller
public class BoardController2 {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentService commentService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public boolean checkAliveSession(HttpServletRequest request){
		if(request.getSession().getAttribute("loginUser") == null){
			return false;
		}
		
		return true;
	}
	
	@RequestMapping(value="board/listpage")
	public String listPage(Model model,HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		List<Board> list = boardService.listBoard();
		model.addAttribute("boardList", list);
		
		return "board/list";
	}
	
	@RequestMapping(value="board/detailpage/{boardId}")
	public String detailPage(@PathVariable("boardId") Integer boardId, 
			Model model,HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		//logger.info("boardId = "+ boardId);
		//logger.info("boardService.getBoard(boardId) = "+ boardService.getBoard(boardId));
		
		model.addAttribute("board",boardService.getBoard(boardId));
		
		return "board/detail";
	}
	
	@RequestMapping(value="board/modifypage/{boardId}")
	public String modifyPage(@PathVariable("boardId") Integer boardId, 
			Model model,HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		//logger.info("boardId = "+ boardId);
		//logger.info("boardService.getBoard(boardId) = "+ boardService.getBoard(boardId));
		
		model.addAttribute("board",boardService.getBoard(boardId));
		
		return "board/modify";
	}
	
	@RequestMapping(value="board/add",method = RequestMethod.POST)
	public String addBoard(@ModelAttribute("board") Board board, HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		//logger.info("board.getTitle() = "+board.getTitle());
		//logger.info("board.getContent() = "+board.getContent());
		
		User user = (User)(request.getSession()).getAttribute("loginUser");
		board.setUserId(user.getId());
		boardService.addBoard(board);
		
		return "redirect:/board/listpage";
	}
	
	@RequestMapping(value="board/modify/{boardId}",method = RequestMethod.POST)
	public String modifyBoard(@ModelAttribute("board") Board board, HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		//logger.info("board.getId() = "+board.getId());
		//logger.info("board.getTitle() = "+board.getTitle());
		//logger.info("board.getContent() = "+board.getContent());
		
		boardService.updateBoard(board);
		
		return "redirect:/board/modifypage/"+board.getId();
	}
	
	@RequestMapping(value="board/delete/{boardId}")
	public String deleteBoard(@PathVariable("boardId") Integer boardId, HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		boardService.removeBoard(boardId);
		
		return "redirect:/board/listpage";
	}
	
	@RequestMapping(value="/board/addcomment/{boardId}")
	public String addComment(@ModelAttribute("comment") Comment comment,
			@PathVariable("boardId") Integer boardId,
			HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		Board board = boardService.getBoard(boardId);
		
		board.getComments().add(comment);
		comment.setBoard(board);
		
		boardService.updateBoard(board);
		commentService.addComment(comment);
		
		return "redirect:/board/detailpage";
	}
	
	@RequestMapping(value="/board/deletecomment/{commentId}")
	public String deleteComment(@PathVariable("commentId") Integer commentId,
			HttpServletRequest request){
		
		if(!checkAliveSession(request)) return "redirect:/indexpage";
		
		commentService.removeComment(commentId);
		
		return "redirect:/board/detailpage";
	}
}
