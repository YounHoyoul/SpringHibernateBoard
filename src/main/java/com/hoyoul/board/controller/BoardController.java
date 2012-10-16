package com.hoyoul.board.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hoyoul.board.dto.Board;
import com.hoyoul.board.dto.User;
import com.hoyoul.board.service.BoardService;
import com.hoyoul.board.service.CommentService;
import com.hoyoul.board.service.UserService;

//@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/boardmain")
	public String listBoard(Model model,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		if(user == null){
			return "redirect:/";
		}
		
		model.addAttribute("boardList", boardService.listBoard());
		
		return "board/main";
	}
	
	@RequestMapping(value = "/addboard", method = RequestMethod.POST)
	public String addBoard(@ModelAttribute("board") Board board,
			BindingResult result, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		board.setUserId(user.getId());
		board.setWriteDate(new Date());
		boardService.addBoard(board);
		
		return "redirect:/boardmain";
	}
	
	@RequestMapping(value="/detailboard/{boardId}")
	public String viewBoard(@PathVariable("boardId") Integer boardId,
			Model model) {
		
		Board board = boardService.getBoard(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("comments", board.getComments());
		model.addAttribute("user", userService.getUser(board.getUserId()));
		
		board.setHit(board.getHit()+1);
		boardService.updateBoard(board);
		
		return "board/detail";
	}
	
	@RequestMapping(value="/modifyboard/{boardId}")
	public String modifyBoard(@PathVariable("boardId") Integer boardId,
			Model model) {
		
		Board board = boardService.getBoard(boardId);	
		model.addAttribute("board", board);
		model.addAttribute("user", userService.getUser(board.getUserId()));
		
		return "board/modify";
	}
	
	@RequestMapping(value = "/updateboard", method = RequestMethod.POST)
	public String updateBoard(Board board) {
		
		boardService.updateBoard(board);
		
		return "redirect:/boardmain";
	}

	@RequestMapping(value="/deleteboard/{boardId}")
	public String removeBoard(@PathVariable("boardId") Integer boardId) {
		
		boardService.removeBoard(boardId);
		
		return "redirect:/boardmain";
	}
}
