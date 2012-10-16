package com.hoyoul.board.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hoyoul.board.dto.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Comment> listComment(int boardId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Comment as e where e.board.id = :boardId");
		query.setParameter("boardId", boardId);
		@SuppressWarnings("unchecked")
		List<Comment> list = query.list();
		
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

	public int addComment(Comment comment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		int id = ((Integer) session.save(comment)).intValue();
		
		session.getTransaction().commit();
		session.close();
		return id;
	}

	public Comment getComment(int commentId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Comment comment = (Comment) session.get(Comment.class, commentId);
		
		session.getTransaction().commit();
		session.close();
		
		return comment;
	}

	public void updateComment(Comment comment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(comment);
		
		session.getTransaction().commit();
		session.close();

	}

	public void removeComment(int commentId) {
		Comment comment = getComment(commentId);
		if(null != comment){
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.delete(comment);
			
			session.getTransaction().commit();
			session.close();
		}
	}

}
