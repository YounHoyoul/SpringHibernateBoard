package com.hoyoul.board.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoyoul.board.dto.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Board> listBoard() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();		
		
		@SuppressWarnings("unchecked")
		List<Board> list = session.createQuery("from Board").list();
		
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	public int addBoard(Board board) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		int id = ((Integer) session.save(board)).intValue();
		
		session.getTransaction().commit();
		session.close();
		
		return id;
	}

	public Board getBoard(int id) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Board board = (Board) session.get(Board.class, id);
		
		session.getTransaction().commit();
		session.close();
		
		return board;
	}

	public void updateBoard(Board board) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(board);
		
		session.getTransaction().commit();
		session.close();
	}

	public void removeBoard(int id) {
		Board board = getBoard(id);
		if(null != board){
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.delete(board);
			
			session.getTransaction().commit();
			session.close();
		}
		
	}

}
