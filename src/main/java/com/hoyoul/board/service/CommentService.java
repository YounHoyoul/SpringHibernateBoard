package com.hoyoul.board.service;

import java.util.List;

import com.hoyoul.board.dto.Comment;

public interface CommentService {
	public List<Comment> listComment(int boardId);
	public int addComment(Comment comment);
	public Comment getComment(int commentId);
	public void updateComment(Comment comment);
	public void removeComment(int commentId);
}
