package com.hoyoul.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hoyoul.board.dto.Comment;
import com.hoyoul.board.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	public List<Comment> listComment(int boardId) {
		return commentService.listComment(boardId);
	}

	public int addComment(Comment comment) {
		return commentService.addComment(comment);
	}

	public Comment getComment(int commentId) {
		return commentService.getComment(commentId);
	}

	public void updateComment(Comment comment) {
		commentService.updateComment(comment);
	}

	public void removeComment(int commentId) {
		commentService.removeComment(commentId);
	}
	

}
