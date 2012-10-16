package com.hoyoul.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyoul.board.dao.CommentDAO;
import com.hoyoul.board.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public List<Comment> listComment(int boardId) {
		return commentDAO.listComment(boardId);
	}

	public int addComment(Comment comment) {
		return commentDAO.addComment(comment);
	}

	public Comment getComment(int commentId) {
		return commentDAO.getComment(commentId);
	}

	public void updateComment(Comment comment) {
		commentDAO.updateComment(comment);
	}

	public void removeComment(int commentId) {
		commentDAO.removeComment(commentId);
	}

}
