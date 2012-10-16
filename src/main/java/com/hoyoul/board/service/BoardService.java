package com.hoyoul.board.service;

import java.util.List;

import com.hoyoul.board.dto.Board;

public interface BoardService {
	public List<Board> listBoard();
	public int addBoard(Board board);
	public Board getBoard(int id);
	public void updateBoard(Board board);
	public void removeBoard(int id);
}
