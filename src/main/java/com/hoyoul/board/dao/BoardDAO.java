package com.hoyoul.board.dao;

import java.util.List;

import com.hoyoul.board.dto.Board;

public interface BoardDAO {
	public List<Board> listBoard();
	public int addBoard(Board board);
	public Board getBoard(int id);
	public void updateBoard(Board board);
	public void removeBoard(int id);
}
