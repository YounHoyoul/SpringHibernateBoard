package com.hoyoul.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoyoul.board.dao.BoardDAO;
import com.hoyoul.board.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> listBoard() {
		return boardDAO.listBoard();
	}

	public int addBoard(Board board) {
		return boardDAO.addBoard(board);
	}

	public Board getBoard(int id) {
		return boardDAO.getBoard(id);
	}

	public void updateBoard(Board board) {
		boardDAO.updateBoard(board);
	}

	public void removeBoard(int id) {
		boardDAO.removeBoard(id);
	}

}
